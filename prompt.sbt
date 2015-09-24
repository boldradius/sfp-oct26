// Customise the SBT command prompt
import scala.Console._
import scala.util.matching._


shellPrompt in ThisBuild := { state =>
  val base: File = Project.extract(state).get(sourceDirectory)
  val basePath = base / "test" / "resources" / "README.md"
  val exercise = BLUE + IO.readLines(basePath).head + RESET
  val manRmnd = RED + "man [e]" + RESET
  val prjNbrNme = Project.extract(state).get(name)
  s"$manRmnd ${BOLD}>${RESET} ${UNDERLINED}${GREEN}${prjNbrNme}${RESET}${BOLD}>${RESET} $exercise ${BOLD}>${RESET} "
}
