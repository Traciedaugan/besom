/*
 * Copyright (C) 2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package besom.json

import org.specs2.mutable.*

import scala.util.control.NonFatal

class JsonParserSpec extends Specification {

  "The JsonParser" should {
    "parse 'null' to JsNull" in {
      JsonParser("null") === JsNull
    }
    "parse 'true' to JsTrue" in {
      JsonParser("true") === JsTrue
    }
    "parse 'false' to JsFalse" in {
      JsonParser("false") === JsFalse
    }
    "parse '0' to JsNumber" in {
      JsonParser("0") === JsNumber(0)
    }
    "parse '1.23' to JsNumber" in {
      JsonParser("1.23") === JsNumber(1.23)
    }
    "parse '-1E10' to JsNumber" in {
      JsonParser("-1E10") === JsNumber("-1E+10")
    }
    "parse '12.34e-10' to JsNumber" in {
      JsonParser("12.34e-10") === JsNumber("1.234E-9")
    }
    "parse \"xyz\" to JsString" in {
      JsonParser("\"xyz\"") === JsString("xyz")
    }
    "parse escapes in a JsString" in {
      JsonParser(""""\"\\/\b\f\n\r\t"""") === JsString("\"\\/\b\f\n\r\t")
      JsonParser("\"L\\" + "u00e4nder\"") === JsString("Länder")
    }
    "parse all representations of the slash (SOLIDUS) character in a JsString" in {
      JsonParser( "\"" + "/\\/\\u002f" + "\"") === JsString("///")
    }
    "parse a simple JsObject" in (
      JsonParser(""" { "key" :42, "key2": "value" }""") ===
              JsObject("key" -> JsNumber(42), "key2" -> JsString("value"))
    )
    "parse a simple JsArray" in (
      JsonParser("""[null, 1.23 ,{"key":true } ] """) ===
              JsArray(JsNull, JsNumber(1.23), JsObject("key" -> JsTrue))
    )
    "parse directly from UTF-8 encoded bytes" in {
      val json = JsObject(
        "7-bit" -> JsString("This is regular 7-bit ASCII text."),
        "2-bytes" -> JsString("2-byte UTF-8 chars like £, æ or Ö"),
        "3-bytes" -> JsString("3-byte UTF-8 chars like ﾖ, ᄅ or ᐁ."),
        "4-bytes" -> JsString("4-byte UTF-8 chars like \uD801\uDC37, \uD852\uDF62 or \uD83D\uDE01."))
      JsonParser(json.prettyPrint.getBytes("UTF-8")) === json
    }
    "parse directly from UTF-8 encoded bytes when string starts with a multi-byte character" in {
      val json = JsString("£0.99")
      JsonParser(json.prettyPrint.getBytes("UTF-8")) === json
    }
    "be reentrant" in {
      import scala.concurrent.ExecutionContext.Implicits.global
      import scala.concurrent.duration.*
      import scala.concurrent.{Await, Future}

      val largeJsonSource = scala.io.Source.fromInputStream(getClass.getResourceAsStream("/test.json")).mkString
      val list = Await.result(
        Future.traverse(List.fill(20)(largeJsonSource))(src => Future(JsonParser(src))),
        5.seconds
      )
      list.map(_.asInstanceOf[JsObject].fields("questions").asInstanceOf[JsArray].elements.size) === List.fill(20)(100)
    }
    "not show bad performance characteristics when object keys' hashCodes collide" in {
      val numKeys = 10000
      val value = "null"

      val regularKeys = Iterator.from(1).map(i => s"key_$i").take(numKeys)
      val collidingKeys = HashCodeCollider.zeroHashCodeIterator().take(numKeys)

      def createJson(keys: Iterator[String]): String = keys.mkString("""{"""", s"""":$value,"""", s"""":$value}""")

      def nanoBench(block: => Unit): Long = {
        // great microbenchmark (the comment must be kept, otherwise it's not true)
        val f = () => block

        // warmup
        (1 to 10).foreach(_ => f())

        val start = System.nanoTime()
        f()
        val end_ = System.nanoTime()
        end_ - start
      }

      val regularJson = createJson(regularKeys)
      val collidingJson = createJson(collidingKeys)

      val regularTime = nanoBench { JsonParser(regularJson) }
      val collidingTime = nanoBench { JsonParser(collidingJson) }

      collidingTime / regularTime must be < 5L // speed must be in same order of magnitude
    }

    "produce proper error messages" in {
      def errorMessage(input: String, settings: JsonParserSettings = JsonParserSettings.default) =
        try JsonParser(input, settings) catch { case e: JsonParser.ParsingException => e.getMessage }

      errorMessage("""[null, 1.23 {"key":true } ]""") ===
        """Unexpected character '{' at input index 12 (line 1, position 13), expected ']':
          |[null, 1.23 {"key":true } ]
          |            ^
          |""".stripMargin

      errorMessage("""[null, 1.23, {  key":true } ]""") ===
        """Unexpected character 'k' at input index 16 (line 1, position 17), expected '"':
          |[null, 1.23, {  key":true } ]
          |                ^
          |""".stripMargin

      errorMessage("""{"a}""") ===
        """Unexpected end-of-input at input index 4 (line 1, position 5), expected '"':
          |{"a}
          |    ^
          |""".stripMargin

      errorMessage("""{}x""") ===
        """Unexpected character 'x' at input index 2 (line 1, position 3), expected end-of-input:
          |{}x
          |  ^
          |""".stripMargin

      "reject numbers which are too big / have too high precision" in {
        val settings = JsonParserSettings.default.withMaxNumberCharacters(5)
        errorMessage("123.4567890", settings) ===
          "Number too long:The number starting with '123.4567890' had 11 characters which is more than the allowed limit " +
          "maxNumberCharacters = 5. If this is legit input consider increasing the limit."
      }
    }

    "fail gracefully for deeply nested structures" in {
      val stackSize = 128000

      def probe(depth: Int, maxDepth: Int): String = {
        val queue = new java.util.ArrayDeque[String]()

        val runnable = new Runnable {
          override def run(): Unit =
            try {
              val nested = "[{\"key\":" * (depth / 2)
              val settings = JsonParserSettings.default.withMaxDepth(maxDepth)
              nested.parseJson(settings)
              queue.push("didn't fail")
            } catch {
              case _: StackOverflowError => queue.push("stackoverflow")
              case NonFatal(e) =>
                queue.push(s"nonfatal: ${e.getMessage}")
            }
        }

        val thread = new Thread(null, runnable, "parser-test", stackSize)
        thread.start()
        thread.join()
        queue.peek
      }

      // Explicit type needed to compile on Scala 2.10, can be inlined later
      def inc(i: Int): Int = 1 + i
      val i: Int = Iterator.iterate(1)(inc).indexWhere(depth => probe(depth, maxDepth = 1000) contains "stackoverflow")
      println(s"Overflowing stack at $i which means we need about ${stackSize / i} bytes per recursive call")

      val maxDepth = i / 4 // should give lots of room
      probe(1500, maxDepth) ===
      s"nonfatal: JSON input nested too deeply:JSON input was nested more deeply than the configured limit of maxNesting = $maxDepth"
    }

    "parse multiple values when allowTrailingInput" in {
      val parser = new JsonParser("""{"key":1}{"key":2}""")
      parser.parseJsValue(true) === JsObject("key" -> JsNumber(1))
      parser.parseJsValue(true) === JsObject("key" -> JsNumber(2))
    }
    "reject trailing input when !allowTrailingInput" in {
      def parser = JsonParser("""{"key":1}x""")
      parser must throwA[JsonParser.ParsingException].like {
        case e: JsonParser.ParsingException => e.getMessage must contain("expected end-of-input")
      }
    }

  }
}
