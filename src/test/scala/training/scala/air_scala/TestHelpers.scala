package training.scala.air_scala

import training.scala.air_scala.aircraft.{MD11, Aircraft}
import training.scala.air_scala.flights.Flight
import training.scala.air_scala.flights.scheduling.Schedule

object TestHelpers {
  import com.github.nscala_time.time.Imports._
  import domain._
  import squants.market._

  def arbitrarySchedule = Schedule((airport.AirportCode("YXE"), new DateTime()), (airport.AirportCode("YYZ"), new DateTime()))
  def arbitraryFlight(id: String, price: Money) =
    Flight(Aircraft(MD11, id), arbitrarySchedule, price)

}
