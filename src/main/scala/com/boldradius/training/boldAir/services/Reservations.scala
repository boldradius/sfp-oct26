package com.boldradius.training.boldAir.services

import com.boldradius.training.boldAir._
import com.boldradius.training.boldAir.domain._
import com.github.nscala_time.time.Imports._
import squants.market._

object Reservations {
  import PowerOfFolding._

  def totalPrice(itinerary: TentativeItinerary): Money =
    foldLeft(itinerary.flights)(USD(0))(_ + _.price)
}
