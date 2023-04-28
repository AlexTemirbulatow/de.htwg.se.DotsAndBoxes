val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotsandboxes",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
    //jacocoCoverallsServiceName := "github-actions",
    //jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    //jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    //jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )
  .enablePlugins(JacocoCoverallsPlugin)