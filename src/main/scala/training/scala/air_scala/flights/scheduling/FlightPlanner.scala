package training.scala.air_scala.flights.scheduling

import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

import com.github.nscala_time.time.Imports._

class FlightPlanner(availableFlights: Seq[Flight]) {

  def proposeItineraries(from: AirportCode, to: AirportCode)
                        (minConnectionTime: Duration = Duration.standardMinutes(90))
                        (maxConnections: Int = 2): Unit = {
    require(from != to, "From and To must be different airports.")

  }

}
