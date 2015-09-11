package com.boldradius.training.boldAir.services

import com.boldradius.training.boldAir._
import com.boldradius.training.boldAir.domain._
import com.github.nscala_time.time.Imports._
import squants.market._

object Reservations {
  import Folding._

  def totalPriceFold(itinerary: TentativeItinerary): Money =
    foldLeft(itinerary.flights)(USD(0))(_ + _.price)
}
