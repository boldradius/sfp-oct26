package training.scala.air_scala.flights

import org.joda.time.DateTime
import squants.market.Money
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.airport.{AirportCode, Gate}
import training.scala.air_scala.flights.scheduling._
import com.github.nscala_time.time.Imports._
import squants._
import squants.market._
import squants.space._

/*
 __ Implementation Note __
 This is deliberately not a case class, as we'll be using it
 for other purposes later.

*/
class Flight(val number: FlightNumber,
             val aircraft: Aircraft,
             val schedule: Schedule,
             val price: Money,
             val miles: Length) extends Ordered[Flight] {


  override val toString = s"Flight { number: $number, aircraft: $aircraft, schedule: $schedule, " +
    s"price: $price, miles: $miles }"

  override def compare(that: Flight): Int = this.schedule.compare(that.schedule)
}


case class FlightNumber(airlineCode: String, number: Int) {
  require(airlineCode.matches("[A-Z]{1,3}"),
          "Airline Code must consist of 1-3 uppercase letters.")
  require(number > 0 && number < 8999,
          "Flight Number be between 1 & 8999")
}

case class FlightLeg(code: AirportCode, time: DateTime) extends Ordered[FlightLeg] {
  override def compare(that: FlightLeg): Int = this.time.compareTo(that.time)
}

case class Schedule(origin: FlightLeg, destination: FlightLeg) extends Ordered[Schedule] {
  override def compare(that: Schedule): Int =
    this.origin.compare(that.origin) + this.destination.compare(that.destination)
}

