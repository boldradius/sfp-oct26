package training.scala.air_scala.flights.scheduling

import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

import com.github.nscala_time.time.Imports._

object FlightPlanner {
  def filterByMaxConnections(maxConnections: Int)(itineraries: Set[Itinerary]): Set[Itinerary] = {
    itineraries.filter(_.connectionsCount <= maxConnections)
  }
  def filterByMinConnectionTime(minConnectionTime: Duration)(itineraries: Set[Itinerary])
      : Set[Itinerary] =
    itineraries.filter { itinerary =>
      Itinerary.layoverTimes(itinerary).forall(_ >= minConnectionTime)
    }
}

class FlightPlanner(availableFlights: Set[Flight]) {
  def proposeItineraries(departAfter: DateTime)
                        (from: AirportCode, to: AirportCode)
                        (minConnectionTime: Duration = Duration.standardMinutes(90))
                        (maxConnections: Int = 2): Set[Itinerary] = {
    require(from != to, "From and To must be different airports.")

    def propose(from: AirportCode, to: AirportCode, departAfter: DateTime): Set[Seq[Flight]] =
      if (from == to)
        Set(Seq.empty[Flight])
      else for {
        flight <- availableFlights
        if flight.schedule.origin.code == from
        if flight.schedule.origin.time > departAfter
        dest = flight.schedule.destination.code
        pathFromDest <- propose(dest, to, flight.schedule.destination.time)
      } yield
        flight +: pathFromDest

    val proposed: Set[Itinerary] =
      propose(from,to,departAfter) map ProposedItinerary.apply

    val filteredByMaxConnections =
      FlightPlanner.filterByMaxConnections(maxConnections)(proposed)

    val filteredByMinConnectionTime =
      FlightPlanner.filterByMinConnectionTime(minConnectionTime)(filteredByMaxConnections)

    filteredByMinConnectionTime
  }
}
