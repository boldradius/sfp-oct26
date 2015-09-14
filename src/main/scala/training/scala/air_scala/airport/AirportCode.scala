package training.scala.air_scala.airport


object AirportCode {

  implicit def toCode(str: String): AirportCode =
    AirportCode(str)

}

case class AirportCode(str: String) {
  require(str.matches("[A-Z]{3}"), "Airport Code must consist of 3 uppercase letters.")
}
