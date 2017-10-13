
name := """whakakoi"""

organization := "com.phirschi"

version := "0.0.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

incOptions := incOptions.value.withNameHashing(true)
updateOptions := updateOptions.value.withCachedResolution(cachedResoluton = true)

libraryDependencies ++= {
  val ngVersion="4.0.0"
  Seq(
    filters,
    ws,
    cacheApi,
    guice,
    //mongodb driver
    "org.reactivemongo" % "reactivemongo_2.11" % "0.12.2",
    //bcrypt for password hashing
    "org.mindrot" % "jbcrypt" % "0.4",
    //angular2 dependencies
    "org.webjars.npm" % "angular__common" % ngVersion,
    "org.webjars.npm" % "angular__compiler" % ngVersion,
    "org.webjars.npm" % "angular__core" % ngVersion,
    "org.webjars.npm" % "angular__http" % ngVersion,
    "org.webjars.npm" % "angular__forms" % ngVersion,
    "org.webjars.npm" % "angular__router" % ngVersion,
    "org.webjars.npm" % "angular__platform-browser-dynamic" % ngVersion,
    "org.webjars.npm" % "angular__platform-browser" % ngVersion,
    "org.webjars.npm" % "angular__animations" % ngVersion,
    "org.webjars.npm" % "systemjs" % "0.19.40",
    "org.webjars.npm" % "rxjs" % "5.0.0-beta.12",
    "org.webjars.npm" % "reflect-metadata" % "0.1.8",
    "org.webjars.npm" % "zone.js" % "0.6.26",
    "org.webjars.npm" % "core-js" % "2.4.1",
    "org.webjars.npm" % "symbol-observable" % "1.0.1",

    "org.webjars.npm" % "typescript" % "2.1.4",

    //tslint dependency
    "org.webjars.npm" % "tslint-eslint-rules" % "3.1.0",
    "org.webjars.npm" % "tslint-microsoft-contrib" % "2.0.12",
    //   "org.webjars.npm" % "codelyzer" % "2.0.0-beta.1",
    "org.webjars.npm" % "types__jasmine" % "2.2.26-alpha" % "test",
    //test
    //  "org.webjars.npm" % "jasmine-core" % "2.4.1"
    // normalize css styles
    "org.webjars.npm" % "node-normalize-scss" % "1.3.2"
  )
}
dependencyOverrides += "org.webjars.npm" % "minimatch" % "3.0.0"

// use the webjars npm directory (target/web/node_modules ) for resolution of module imports of angular2/core etc
resolveFromWebjarsNodeModulesDir := true

// use the combined tslint and eslint rules plus ng2 lint rules
(rulesDirectories in tslint) := Some(List(
  tslintEslintRulesDir.value,
  ng2LintRulesDir.value
))

logLevel in tslint := Level.Debug
routesGenerator := InjectedRoutesGenerator


fork in run := true

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.grantvictor.binders._"
