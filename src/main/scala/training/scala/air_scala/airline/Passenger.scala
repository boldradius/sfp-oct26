package training.scala.air_scala.airline

import training.scala.air_scala.aircraft._

import scalaz.std.option._
import scalaz.syntax.std.option._
import scalaz.syntax.applicative._

/**
 * Perfect core for future exercise regarding Abstract Types...
 * Passenger would have an abstract type Nationality, which would
 * need to be filled in on a concrete instance of Passenger
 *
 * Also, add "FrequentFlyer" info... maybe instead?
 */
class Passenger(val familyName: String,
                val givenName: String,
                val middleName: Option[String],
                val seatPosition: SeatPosition,
                val seatingClass: SeatingClass,
                val frequentFlyer: Option[FrequentFlyer]) {

  require(seatPosition != Middle) // won't be able to recover from exception

}


object Passenger {
  def apply(fields: Map[String, String]): Option[Passenger] =
    for {fn <- fields.get("familyName").filterNot(_.contains(" "))
         gn <- fields.get("givenName").filterNot(_.contains(" "))
         mn = fields.get("middleName")
         sp <- fields.get("seatPosition").flatMap{_ match {case "Middle" => some(Middle)}}
         sc <- fields.get("seatingClass").flatMap{_ match {case "FirstClass" => some(FirstClass)}}
         ff <- fields
          .get("frequentFlyer")
          .cata(_ match {case "Odersky" => some(some(Odersky))}, some(none))
          //.fold[Option[Option[FrequentFlyer]]](some(none))(_ match {case "Odersky" => some(some(Odersky))})
    } yield new Passenger(fn, gn, mn, sp, sc, ff)

}

object test {
 some(1).cata(_+1, 0)
}




