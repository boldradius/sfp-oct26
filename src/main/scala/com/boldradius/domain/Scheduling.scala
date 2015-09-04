package com.boldradius.domain

import org.joda.time.DateTime

case class Schedule(origin: (AirportCode, DateTime), destination: (AirportCode, DateTime))

trait Itinerary

case class TentativeItinerary() extends Itinerary
case class BookedItinerary() extends Itinerary
