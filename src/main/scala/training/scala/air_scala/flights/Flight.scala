package training.scala.air_scala.flights

import squants.market.Money
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.flights.scheduling._

case class Flight(aircraft: Aircraft, schedule: Schedule, price: Money)
