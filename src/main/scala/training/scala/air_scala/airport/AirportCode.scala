package training.scala.air_scala.airport


object AirportCode {
  def apply(str: String) : AirportCode = {
    require(str.matches("[A-Z]{3}"), "Airport Code must consist of 3 uppercase letters.")
    new AirportCode(str)
  }

  implicit def toCode(str: String): AirportCode =
    AirportCode(str)

}

class AirportCode(val str: String) extends AnyVal {
}
