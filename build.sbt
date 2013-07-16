import AssemblyKeys._

name := "hOCRInfoAggregator"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
    "junit" % "junit" % "3.8.1" % "test",
    "org.apache.lucene" % "lucene-spellchecker" % "3.6.1",
    "org.apache.xmlgraphics" % "fop" % "1.0",
    "com.ibm.icu" % "icu4j" % "49.1",
    "org.jdom" % "jdom" % "2.0.2",
    "jaxen" % "jaxen" % "1.1.3"
).map(_.excludeAll(ExclusionRule( organization = "maven-plugins" )))

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

assemblySettings
