package com.boldradius.training.boldAir.domain

import com.github.nscala_time.time.Imports._

case class Schedule(origin: (AirportCode, DateTime), destination: (AirportCode, DateTime))

trait Itinerary

case class TentativeItinerary() extends Itinerary
case class BookedItinerary() extends Itinerary
