package training.scala.air_scala.airport


object AirportCode {

  def apply(str: String): AirportCode = {
    require(str.matches("[A-Z]{3}"), "Airport Code must consist of 3 uppercase letters.")
    new AirportCode(str) // ensures that we call the constructor instead of the apply method
  }

  implicit def toCode(str: String): AirportCode =
    AirportCode(str)

}

/*case class AirportCode(str: String) {
  require(str.matches("[A-Z]{3}"), "Airport Code must consist of 3 uppercase letters.")
}*/

class AirportCode(val str: String) extends AnyVal {
}

//object Bla {
//  val bla: AirportCode = "kaka"
//
//  val bla2: AirportCode = 123
//}
