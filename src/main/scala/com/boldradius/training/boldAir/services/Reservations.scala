package com.boldradius.training.boldAir.services

import com.boldradius.training.boldAir.domain.{TentativeItinerary, AirportCode}
import com.github.nscala_time.time.Imports._

class Reservations {
  def proposeItineraries(origin: (AirportCode, DateTime),
                         destination: (AirportCode, DateTime)): Vector[TentativeItinerary] =
    Vector.empty[TentativeItinerary]
}
