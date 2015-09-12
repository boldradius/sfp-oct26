package com.boldradius.training.boldAir.domain

import com.github.nscala_time.time.Imports._
import squants.market._

case class Schedule(origin: (AirportCode, DateTime), destination: (AirportCode, DateTime))
case class Flight(aircraft: Aircraft, schedule: Schedule, price: Money)
case class TentativeItinerary(flights: Seq[Flight])
