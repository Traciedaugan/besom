// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package pulumirpc.analyzer

object AnalyzerProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq(
    pulumirpc.plugin.PluginProto,
    com.google.protobuf.empty.EmptyProto,
    com.google.protobuf.struct.StructProto
  )
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      pulumirpc.analyzer.AnalyzeRequest,
      pulumirpc.analyzer.AnalyzerResource,
      pulumirpc.analyzer.AnalyzerResourceOptions,
      pulumirpc.analyzer.AnalyzerProviderResource,
      pulumirpc.analyzer.AnalyzerPropertyDependencies,
      pulumirpc.analyzer.AnalyzeStackRequest,
      pulumirpc.analyzer.AnalyzeResponse,
      pulumirpc.analyzer.AnalyzeDiagnostic,
      pulumirpc.analyzer.AnalyzerInfo,
      pulumirpc.analyzer.PolicyInfo,
      pulumirpc.analyzer.PolicyConfigSchema,
      pulumirpc.analyzer.PolicyConfig,
      pulumirpc.analyzer.ConfigureAnalyzerRequest
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Cg5hbmFseXplci5wcm90bxIJcHVsdW1pcnBjGgxwbHVnaW4ucHJvdG8aG2dvb2dsZS9wcm90b2J1Zi9lbXB0eS5wcm90bxocZ
  29vZ2xlL3Byb3RvYnVmL3N0cnVjdC5wcm90byLQAgoOQW5hbHl6ZVJlcXVlc3QSHQoEdHlwZRgBIAEoCUIJ4j8GEgR0eXBlUgR0e
  XBlEkgKCnByb3BlcnRpZXMYAiABKAsyFy5nb29nbGUucHJvdG9idWYuU3RydWN0Qg/iPwwSCnByb3BlcnRpZXNSCnByb3BlcnRpZ
  XMSGgoDdXJuGAMgASgJQgjiPwUSA3VyblIDdXJuEh0KBG5hbWUYBCABKAlCCeI/BhIEbmFtZVIEbmFtZRJKCgdvcHRpb25zGAUgA
  SgLMiIucHVsdW1pcnBjLkFuYWx5emVyUmVzb3VyY2VPcHRpb25zQgziPwkSB29wdGlvbnNSB29wdGlvbnMSTgoIcHJvdmlkZXIYB
  iABKAsyIy5wdWx1bWlycGMuQW5hbHl6ZXJQcm92aWRlclJlc291cmNlQg3iPwoSCHByb3ZpZGVyUghwcm92aWRlciK+BQoQQW5hb
  Hl6ZXJSZXNvdXJjZRIdCgR0eXBlGAEgASgJQgniPwYSBHR5cGVSBHR5cGUSSAoKcHJvcGVydGllcxgCIAEoCzIXLmdvb2dsZS5wc
  m90b2J1Zi5TdHJ1Y3RCD+I/DBIKcHJvcGVydGllc1IKcHJvcGVydGllcxIaCgN1cm4YAyABKAlCCOI/BRIDdXJuUgN1cm4SHQoEb
  mFtZRgEIAEoCUIJ4j8GEgRuYW1lUgRuYW1lEkoKB29wdGlvbnMYBSABKAsyIi5wdWx1bWlycGMuQW5hbHl6ZXJSZXNvdXJjZU9wd
  GlvbnNCDOI/CRIHb3B0aW9uc1IHb3B0aW9ucxJOCghwcm92aWRlchgGIAEoCzIjLnB1bHVtaXJwYy5BbmFseXplclByb3ZpZGVyU
  mVzb3VyY2VCDeI/ChIIcHJvdmlkZXJSCHByb3ZpZGVyEiMKBnBhcmVudBgHIAEoCUIL4j8IEgZwYXJlbnRSBnBhcmVudBI1CgxkZ
  XBlbmRlbmNpZXMYCCADKAlCEeI/DhIMZGVwZW5kZW5jaWVzUgxkZXBlbmRlbmNpZXMShAEKFHByb3BlcnR5RGVwZW5kZW5jaWVzG
  AkgAygLMjUucHVsdW1pcnBjLkFuYWx5emVyUmVzb3VyY2UuUHJvcGVydHlEZXBlbmRlbmNpZXNFbnRyeUIZ4j8WEhRwcm9wZXJ0e
  URlcGVuZGVuY2llc1IUcHJvcGVydHlEZXBlbmRlbmNpZXMahgEKGVByb3BlcnR5RGVwZW5kZW5jaWVzRW50cnkSGgoDa2V5GAEgA
  SgJQgjiPwUSA2tleVIDa2V5EkkKBXZhbHVlGAIgASgLMicucHVsdW1pcnBjLkFuYWx5emVyUHJvcGVydHlEZXBlbmRlbmNpZXNCC
  uI/BxIFdmFsdWVSBXZhbHVlOgI4ASKZBQoXQW5hbHl6ZXJSZXNvdXJjZU9wdGlvbnMSJgoHcHJvdGVjdBgBIAEoCEIM4j8JEgdwc
  m90ZWN0Ugdwcm90ZWN0EjgKDWlnbm9yZUNoYW5nZXMYAiADKAlCEuI/DxINaWdub3JlQ2hhbmdlc1INaWdub3JlQ2hhbmdlcxJKC
  hNkZWxldGVCZWZvcmVSZXBsYWNlGAMgASgIQhjiPxUSE2RlbGV0ZUJlZm9yZVJlcGxhY2VSE2RlbGV0ZUJlZm9yZVJlcGxhY2USX
  woaZGVsZXRlQmVmb3JlUmVwbGFjZURlZmluZWQYBCABKAhCH+I/HBIaZGVsZXRlQmVmb3JlUmVwbGFjZURlZmluZWRSGmRlbGV0Z
  UJlZm9yZVJlcGxhY2VEZWZpbmVkElYKF2FkZGl0aW9uYWxTZWNyZXRPdXRwdXRzGAUgAygJQhziPxkSF2FkZGl0aW9uYWxTZWNyZ
  XRPdXRwdXRzUhdhZGRpdGlvbmFsU2VjcmV0T3V0cHV0cxImCgdhbGlhc2VzGAYgAygJQgziPwkSB2FsaWFzZXNSB2FsaWFzZXMSb
  goOY3VzdG9tVGltZW91dHMYByABKAsyMS5wdWx1bWlycGMuQW5hbHl6ZXJSZXNvdXJjZU9wdGlvbnMuQ3VzdG9tVGltZW91dHNCE
  +I/EBIOY3VzdG9tVGltZW91dHNSDmN1c3RvbVRpbWVvdXRzGn8KDkN1c3RvbVRpbWVvdXRzEiMKBmNyZWF0ZRgBIAEoAUIL4j8IE
  gZjcmVhdGVSBmNyZWF0ZRIjCgZ1cGRhdGUYAiABKAFCC+I/CBIGdXBkYXRlUgZ1cGRhdGUSIwoGZGVsZXRlGAMgASgBQgviPwgSB
  mRlbGV0ZVIGZGVsZXRlIr4BChhBbmFseXplclByb3ZpZGVyUmVzb3VyY2USHQoEdHlwZRgBIAEoCUIJ4j8GEgR0eXBlUgR0eXBlE
  kgKCnByb3BlcnRpZXMYAiABKAsyFy5nb29nbGUucHJvdG9idWYuU3RydWN0Qg/iPwwSCnByb3BlcnRpZXNSCnByb3BlcnRpZXMSG
  goDdXJuGAMgASgJQgjiPwUSA3VyblIDdXJuEh0KBG5hbWUYBCABKAlCCeI/BhIEbmFtZVIEbmFtZSI9ChxBbmFseXplclByb3Blc
  nR5RGVwZW5kZW5jaWVzEh0KBHVybnMYASADKAlCCeI/BhIEdXJuc1IEdXJucyJgChNBbmFseXplU3RhY2tSZXF1ZXN0EkkKCXJlc
  291cmNlcxgBIAMoCzIbLnB1bHVtaXJwYy5BbmFseXplclJlc291cmNlQg7iPwsSCXJlc291cmNlc1IJcmVzb3VyY2VzImMKD0FuY
  Wx5emVSZXNwb25zZRJQCgtkaWFnbm9zdGljcxgCIAMoCzIcLnB1bHVtaXJwYy5BbmFseXplRGlhZ25vc3RpY0IQ4j8NEgtkaWFnb
  m9zdGljc1ILZGlhZ25vc3RpY3MivgMKEUFuYWx5emVEaWFnbm9zdGljEi8KCnBvbGljeU5hbWUYASABKAlCD+I/DBIKcG9saWN5T
  mFtZVIKcG9saWN5TmFtZRI7Cg5wb2xpY3lQYWNrTmFtZRgCIAEoCUIT4j8QEg5wb2xpY3lQYWNrTmFtZVIOcG9saWN5UGFja05hb
  WUSRAoRcG9saWN5UGFja1ZlcnNpb24YAyABKAlCFuI/ExIRcG9saWN5UGFja1ZlcnNpb25SEXBvbGljeVBhY2tWZXJzaW9uEjIKC
  2Rlc2NyaXB0aW9uGAQgASgJQhDiPw0SC2Rlc2NyaXB0aW9uUgtkZXNjcmlwdGlvbhImCgdtZXNzYWdlGAUgASgJQgziPwkSB21lc
  3NhZ2VSB21lc3NhZ2USHQoEdGFncxgGIAMoCUIJ4j8GEgR0YWdzUgR0YWdzEl4KEGVuZm9yY2VtZW50TGV2ZWwYByABKA4yGy5wd
  Wx1bWlycGMuRW5mb3JjZW1lbnRMZXZlbEIV4j8SEhBlbmZvcmNlbWVudExldmVsUhBlbmZvcmNlbWVudExldmVsEhoKA3VybhgII
  AEoCUII4j8FEgN1cm5SA3VybiLfAwoMQW5hbHl6ZXJJbmZvEh0KBG5hbWUYASABKAlCCeI/BhIEbmFtZVIEbmFtZRIyCgtkaXNwb
  GF5TmFtZRgCIAEoCUIQ4j8NEgtkaXNwbGF5TmFtZVILZGlzcGxheU5hbWUSQAoIcG9saWNpZXMYAyADKAsyFS5wdWx1bWlycGMuU
  G9saWN5SW5mb0IN4j8KEghwb2xpY2llc1IIcG9saWNpZXMSJgoHdmVyc2lvbhgEIAEoCUIM4j8JEgd2ZXJzaW9uUgd2ZXJzaW9uE
  jsKDnN1cHBvcnRzQ29uZmlnGAUgASgIQhPiPxASDnN1cHBvcnRzQ29uZmlnUg5zdXBwb3J0c0NvbmZpZxJkCg1pbml0aWFsQ29uZ
  mlnGAYgAygLMioucHVsdW1pcnBjLkFuYWx5emVySW5mby5Jbml0aWFsQ29uZmlnRW50cnlCEuI/DxINaW5pdGlhbENvbmZpZ1INa
  W5pdGlhbENvbmZpZxpvChJJbml0aWFsQ29uZmlnRW50cnkSGgoDa2V5GAEgASgJQgjiPwUSA2tleVIDa2V5EjkKBXZhbHVlGAIgA
  SgLMhcucHVsdW1pcnBjLlBvbGljeUNvbmZpZ0IK4j8HEgV2YWx1ZVIFdmFsdWU6AjgBIvECCgpQb2xpY3lJbmZvEh0KBG5hbWUYA
  SABKAlCCeI/BhIEbmFtZVIEbmFtZRIyCgtkaXNwbGF5TmFtZRgCIAEoCUIQ4j8NEgtkaXNwbGF5TmFtZVILZGlzcGxheU5hbWUSM
  goLZGVzY3JpcHRpb24YAyABKAlCEOI/DRILZGVzY3JpcHRpb25SC2Rlc2NyaXB0aW9uEiYKB21lc3NhZ2UYBCABKAlCDOI/CRIHb
  WVzc2FnZVIHbWVzc2FnZRJeChBlbmZvcmNlbWVudExldmVsGAUgASgOMhsucHVsdW1pcnBjLkVuZm9yY2VtZW50TGV2ZWxCFeI/E
  hIQZW5mb3JjZW1lbnRMZXZlbFIQZW5mb3JjZW1lbnRMZXZlbBJUCgxjb25maWdTY2hlbWEYBiABKAsyHS5wdWx1bWlycGMuUG9sa
  WN5Q29uZmlnU2NoZW1hQhHiPw4SDGNvbmZpZ1NjaGVtYVIMY29uZmlnU2NoZW1hIokBChJQb2xpY3lDb25maWdTY2hlbWESSAoKc
  HJvcGVydGllcxgBIAEoCzIXLmdvb2dsZS5wcm90b2J1Zi5TdHJ1Y3RCD+I/DBIKcHJvcGVydGllc1IKcHJvcGVydGllcxIpCghyZ
  XF1aXJlZBgCIAMoCUIN4j8KEghyZXF1aXJlZFIIcmVxdWlyZWQiuAEKDFBvbGljeUNvbmZpZxJeChBlbmZvcmNlbWVudExldmVsG
  AEgASgOMhsucHVsdW1pcnBjLkVuZm9yY2VtZW50TGV2ZWxCFeI/EhIQZW5mb3JjZW1lbnRMZXZlbFIQZW5mb3JjZW1lbnRMZXZlb
  BJICgpwcm9wZXJ0aWVzGAIgASgLMhcuZ29vZ2xlLnByb3RvYnVmLlN0cnVjdEIP4j8MEgpwcm9wZXJ0aWVzUgpwcm9wZXJ0aWVzI
  vgBChhDb25maWd1cmVBbmFseXplclJlcXVlc3QSbAoMcG9saWN5Q29uZmlnGAEgAygLMjUucHVsdW1pcnBjLkNvbmZpZ3VyZUFuY
  Wx5emVyUmVxdWVzdC5Qb2xpY3lDb25maWdFbnRyeUIR4j8OEgxwb2xpY3lDb25maWdSDHBvbGljeUNvbmZpZxpuChFQb2xpY3lDb
  25maWdFbnRyeRIaCgNrZXkYASABKAlCCOI/BRIDa2V5UgNrZXkSOQoFdmFsdWUYAiABKAsyFy5wdWx1bWlycGMuUG9saWN5Q29uZ
  mlnQgriPwcSBXZhbHVlUgV2YWx1ZToCOAEqawoQRW5mb3JjZW1lbnRMZXZlbBIbCghBRFZJU09SWRAAGg3iPwoSCEFEVklTT1JZE
  h0KCU1BTkRBVE9SWRABGg7iPwsSCU1BTkRBVE9SWRIbCghESVNBQkxFRBACGg3iPwoSCERJU0FCTEVEMvACCghBbmFseXplchJCC
  gdBbmFseXplEhkucHVsdW1pcnBjLkFuYWx5emVSZXF1ZXN0GhoucHVsdW1pcnBjLkFuYWx5emVSZXNwb25zZSIAEkwKDEFuYWx5e
  mVTdGFjaxIeLnB1bHVtaXJwYy5BbmFseXplU3RhY2tSZXF1ZXN0GhoucHVsdW1pcnBjLkFuYWx5emVSZXNwb25zZSIAEkQKD0dld
  EFuYWx5emVySW5mbxIWLmdvb2dsZS5wcm90b2J1Zi5FbXB0eRoXLnB1bHVtaXJwYy5BbmFseXplckluZm8iABJACg1HZXRQbHVna
  W5JbmZvEhYuZ29vZ2xlLnByb3RvYnVmLkVtcHR5GhUucHVsdW1pcnBjLlBsdWdpbkluZm8iABJKCglDb25maWd1cmUSIy5wdWx1b
  WlycGMuQ29uZmlndXJlQW5hbHl6ZXJSZXF1ZXN0GhYuZ29vZ2xlLnByb3RvYnVmLkVtcHR5IgBCMFouZ2l0aHViLmNvbS9wdWx1b
  WkvcHVsdW1pL3YzL3Byb3RvL2dvL3B1bHVtaXJwY2IGcHJvdG8z"""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
      pulumirpc.plugin.PluginProto.javaDescriptor,
      com.google.protobuf.empty.EmptyProto.javaDescriptor,
      com.google.protobuf.struct.StructProto.javaDescriptor
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}