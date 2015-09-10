package com.boldradius.training.boldAir.services

import com.boldradius.training.boldAir.domain._
import com.boldradius.training.boldAir.Folding._
import com.github.nscala_time.time.Imports._
import squants.market._

object Reservations {
  def totalPrice(itinerary: TentativeItinerary): Money =
    foldLeft(itinerary.flights)(USD(0))(_ + _.price)
}
