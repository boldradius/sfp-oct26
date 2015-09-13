// Customise the SBT command prompt
import scala.Console._
import scala.util.matching._



shellPrompt in ThisBuild := { state =>
  val base: File = Project.extract(state).get(sourceDirectory)
  val prjNbrNme = Project.extract(state).get(name)
  s"${RED}sbt${RESET}${BOLD}>${RESET} ${UNDERLINED}${GREEN}${prjNbrNme}${RESET}${BOLD}>${RESET} "
}
