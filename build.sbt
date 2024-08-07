libraryDependencies ++= List(
  "org.slf4j" % "slf4j-nop" % "2.0.13",
  "org.postgresql" % "postgresql" % "42.2.18",
  "com.typesafe.slick"%% "slick-hikaricp" % "3.5.1",
  "org.scalatest" %% "scalatest" % "3.2.19" % Test
)

scalacOptions += "-deprecation"
run / fork := true
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.5.1"

// based on https://stackoverflow.com/a/63780833/333643
lazy val runAll = taskKey[Unit]("Run all main classes")

def runAllIn(config: Configuration) = Def.task {
    val s = streams.value
    val cp = (config / fullClasspath).value
    val r = (config / run / runner).value
    val classes = (config / discoveredMainClasses).value
    classes.foreach { className =>
      r.run(className, cp.files, Seq(), s.log).get
    }
}

runAll := {
  runAllIn(Compile).value
  runAllIn(Test).value
}