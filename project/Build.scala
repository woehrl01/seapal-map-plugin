import sbt._
import Keys._
import play.Project._
import de.johoop.jacoco4sbt._
import JacocoPlugin._

object ApplicationBuild extends Build {

  val appName         = "maps"
  val appVersion      = "1.0-SNAPSHOT"

  // JaCoco settings  
  lazy val jacocoSettings = Defaults.defaultSettings ++ Seq(jacoco.settings:_*)

  // general project dependencies
  val generalDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean,
    "com.google.inject" % "guice" % "3.0",
    "org.ektorp" % "org.ektorp" % "1.3.0",
    "de.htwg.seapal" % "common" % "1.0-SNAPSHOT",
    "com.db4o" % "db4o-full-java5" % "8.1-SNAPSHOT"
  )
  
  // team component dependencies
  val teamDependencies = Seq(
  	// your project dependencies here...
    "de.htwg.seapal" % "boat" % "1.0-SNAPSHOT" ,
    "de.htwg.seapal" % "person" % "1.1-SNAPSHOT",
    "de.htwg.seapal" % "tripdemo" % "1.0-SNAPSHOT"
  )
  
  // all project dependencies
  val appDependencies = generalDependencies ++ teamDependencies 

  val main = play.Project(appName, appVersion, appDependencies, settings = jacocoSettings).settings(
    
    // disable parallel execution
    parallelExecution in jacoco.Config := false,
    
	// known as group id...
	organization := "de.htwg.seapal",
	
	// disable using the Scala version in output paths and artifacts
	crossPaths := false,
	
	// add additional resovers
    resolvers += "HTWG Resolver" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local",
	resolvers += "db4o-repo" at "http://source.db4o.com/maven",

	// add publishing target
    publishTo := Some("HTWG Publish To" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime()), 
  
  	// setup entry points for sonar code analyzer
  	pomExtra :=
	  <build>
	    <sourceDirectory>app</sourceDirectory>
	    <testSourceDirectory>test</testSourceDirectory>
	    <resources>
	      <resource>
	        <directory>app</directory>
	      </resource>
	    </resources>
	  </build>
  )

}
