package com.boldradius.training.boldAir

object TestHelpers {
  import org.joda.time.DateTime
  import domain._
  import squants.market._

  def arbitrarySchedule = Schedule((AirportCode("YXE"), new DateTime()), (AirportCode("YYZ"), new DateTime()))
  def arbitraryFlight(id: String, price: Money) =
    Flight(Aircraft(MD11, id), arbitrarySchedule, price)

}
