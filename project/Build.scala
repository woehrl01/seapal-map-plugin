import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "maps"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "com.google.inject" % "guice" % "3.0",
    "com.google.inject.extensions" % "guice-multibindings" % "3.0",
    "util" % "util_2.10" % "1.0-SNAPSHOT",
    "boatdemo" % "boatdemo_2.10" % "1.3-SNAPSHOT" ,
    "persondemo" % "persondemo_2.10" % "1.1-SNAPSHOT",
	"eu.henkelmann" % "junit_xml_listener" % "0.2"
  )
  
  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here  

	// known as group id...
	organization := "de.htwg.seapal",
	
	// disable using the Scala version in output paths and artifacts
	crossPaths := false,
	
    resolvers += "HTWG Resolver" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local",
	resolvers += "Christoph's Maven Repo" at "http://maven.henkelmann.eu/",
	
	testListeners <<= (target, streams).map((t, s) => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath, s.log))),

    publishTo := Some("HTWG Publish To" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime()) 
  
  )

}
