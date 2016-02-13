name := "spiderx"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.10" % "1.5.1" ,
  "org.apache.thrift" % "libthrift" % "0.9.3",
  "backtype" % "dfs-datastores-cascading" % "1.2.0",
  "cascalog" % "cascalog" % "1.10.0" ,
  "backtype" % "cascading-thrift" % "0.2.3" exclude("org.apache.thrift", "libthrift"),
  "org.apache.hbase" % "hbase-client" % "0.96.1-hadoop2",
  "org.apache.hbase" % "hbase-server" % "0.96.1-hadoop2",
  "org.apache.hbase" % "hbase-common" % "0.96.1-hadoop2"
  )

resolvers ++= Seq("Concurrent Maven Repo" at "http://conjars.org/repo",
  "Sonatype Public" at "https://oss.sonatype.org/content/groups/public/",
  "clojars" at "http://clojars.org/repo",
  "central" at "http://repo1.maven.org/maven2"
)




assemblyJarName in assembly := "spiderx_2.10-1.0.jar"

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

assemblyOption in assembly ~= { _.copy(cacheOutput = false) }