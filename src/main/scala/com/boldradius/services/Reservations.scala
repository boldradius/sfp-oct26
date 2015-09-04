package com.boldradius.services

import com.boldradius.domain._
import org.joda.time._

class Reservations {
  def proposeItineraries(origin: (AirportCode, DateTime), destination: (AirportCode, DateTime)): Vector[TentativeItinerary] = Vector()
}
