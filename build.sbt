import AssemblyKeys._
import sbtassembly.Plugin._

name := "hOCRInfoAggregator"

version := "1.0"

scalaVersion := "2.10.0"

mainClass := Some("eu.himeros.hocr.RunAll")

libraryDependencies ++= Seq(
    "junit" % "junit" % "3.8.1" % "test",
    "org.apache.lucene" % "lucene-spellchecker" % "3.6.1",
    "org.apache.xmlgraphics" % "fop" % "1.0",
    "com.ibm.icu" % "icu4j" % "49.1",
    "org.jdom" % "jdom" % "2.0.2",
    "jaxen" % "jaxen" % "1.1.3"
).map(_.excludeAll(
	ExclusionRule( organization = "maven-plugins" ),
	ExclusionRule( organization = "xerces" ),
	ExclusionRule( organization = "xalan" )
))

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

assemblySettings

mainClass in assembly := Some("eu.himeros.hocr.RunAll")

test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
	case PathList("org","w3c","dom", xs @ _*) => MergeStrategy.first
	case x => old(x)
  }
}