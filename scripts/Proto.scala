package besom.scripts

import org.apache.commons.lang3.SystemUtils
import os.*

object Proto:
  def main(args: String*): Unit =
    val cwd = besomDir
    val protoPath = cwd / "proto"
    args match
      case "fetch" :: Nil => fetchProto(cwd, protoPath)
      case "compile" :: Nil => compileProto(cwd, protoPath)
      case "all" :: Nil =>
        fetchProto(cwd, protoPath)
        compileProto(cwd, protoPath)
        println("fetched & compiled")
      case "check" :: Nil =>
        if !isProtocInstalled then
          println("You need `protoc` protobuffer compiler installed for this to work!")
          sys.exit(1)
        if !isGitInstalled then
          println("You need `git` installed for this to work!")
          sys.exit(1)
        if !isUnzipAvailable then
          println("You need `unzip` installed for this to work!")
          sys.exit(1)
      case other =>
        println(s"Unknown command: $other")
        println(
          """Usage: proto <command>
            |
            |Commands:
            |  fetch   - fetches protobuf declaration files from pulumi repo(s)
            |  compile - compiles protobuf declaration files to scala code (codegen)
            |  all     - fetches & compiles
            |""".stripMargin)
        sys.exit(1)

private def fetchProto(cwd: os.Path, targetPath: os.Path): Unit =
  val pulumiRepoPath = cwd / "target" / "pulumi-proto"
  val pulumiRepo = sparseCheckout(
    pulumiRepoPath,
    "github.com/pulumi/pulumi.git",
    List(os.rel / "proto")
  )

  os.remove.all(targetPath)
  copyProto(pulumiRepo / "proto", targetPath)
  println("fetched protobuf declaration files")

private def copyProto(sourcePath: os.Path, targetPath: os.Path): Unit =
  println(s"copying from $sourcePath to $targetPath")

  val allowDirList = List()

  val allowFileList = List(
    "alias.proto",
    "engine.proto",
    "plugin.proto",
    "provider.proto",
    "resource.proto",
    "source.proto",
    "status.proto"
  )

  val allowExtensions = List("proto")

  copyFilteredFiles(sourcePath, targetPath, allowDirList, allowFileList, allowExtensions)

private def compileProto(cwd: os.Path, protoPath: os.Path): Unit =
  if !isProtocInstalled then
    println("You need protoc protobuffer compiler installed for this to work!")
    sys.exit(1)

  if !isUnzipAvailable then
    println("You need unzip installed for this to work!")
    sys.exit(1)

  if !os.exists(protoPath) then println("run `scala-cli run ./scripts -M proto -- fetch` first!")

  val pluginPath = cwd / "target" / "protoc-gen-scala"
  if !os.exists(pluginPath) then fetchScalaProtocPlugin(cwd)

  val files = os.walk(protoPath).filter(_.ext == "proto")

  val scalaOut = cwd / "core" / "src" / "main" / "scala" / "besom" / "rpc"
  os.remove.all(scalaOut)
  os.makeDir.all(scalaOut)

  // this generates GRPC netty-based impl for Scala in ./src/main/scala/besom/rpc, notice grpc: in --scala_out
  val protoc = os.proc(
    "protoc",
    s"--plugin=$pluginPath",
    s"--scala_out=grpc:$scalaOut",
    files.map(_.relativeTo(protoPath))
  )
  println(s"running [$protoPath]: ${protoc.commandChunks.mkString(" ")}")
  protoc.call(
    protoPath,
    stdin = os.Inherit,
    stdout = os.Inherit,
    stderr = os.Inherit
  )

  println(s"generated scala code from protobuf declaration files to $scalaOut")

private def fetchScalaProtocPlugin(cwd: os.Path): Unit =
  val releasesJsonBodyStr = requests.get("https://api.github.com/repos/scalapb/ScalaPB/releases")
  val json                = ujson.read(releasesJsonBodyStr)
  val latestVersion = json.arr.headOption.getOrElse(
    throw Exception("No releases of ScalaPB found, check https://github.com/scalapb/ScalaPB/releases")
  )
  val assets    = latestVersion.obj("assets")
  val currentOS = if SystemUtils.IS_OS_MAC then "osx" else "linux"
  val releases  = assets.arr.map(_.obj("name").str).mkString(", ")
  println(s"found these latest protoc-gen-scala plugin releases: $releases")
  val downloadUrl =
    assets.arr
      .find(_.obj("name").str.contains(currentOS))
      .map(_.obj("browser_download_url").str)
      .getOrElse {
        val releases = assets.arr.map(_.obj("name").str).mkString(", ")
        throw Exception(
          s"Unable to find scalapbc plugin for this OS, current os: $currentOS, found releases: $releases!"
        )
      }

  println(s"fetching $downloadUrl")

  // unzip the plugin
  os.write.over(cwd / "target" / "protoc-gen-scala.zip", requests.get.stream(downloadUrl))
  os.proc("unzip", "-o", cwd / "target" / "protoc-gen-scala.zip").call(cwd = cwd / "target")
  os.remove(cwd / "target" / "protoc-gen-scala.zip")
