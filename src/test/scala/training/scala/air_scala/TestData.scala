package training.scala.air_scala

import training.scala.air_scala.aircraft._
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.airport.{Gate, AirportCode}
import training.scala.air_scala.flights.{FlightNumber, FlightLeg, Schedule, Flight}
import com.github.nscala_time.time.Imports._
import squants.market._
import MoneyConversions._

import scala.collection.immutable.HashMap

object TestData {

  case object CRJ200 extends AircraftModel with TurboProp {
    val seats = HashMap[SeatingClass, Vector[Seat]](
      FirstClass -> Vector.empty[Seat],
      BusinessClass -> Vector.empty[Seat],
      EconomyPlus -> Vector(
        EconomyPlusSeat(1, 'A'), EconomyPlusSeat(1, 'B'), EconomyPlusSeat(1, 'C'),
        EconomyPlusSeat(2, 'A'), EconomyPlusSeat(2, 'B'), EconomyPlusSeat(2, 'C'),
        EconomyPlusSeat(3, 'A'), EconomyPlusSeat(3, 'B'), EconomyPlusSeat(3, 'C'),
        EconomyPlusSeat(4, 'A' ), EconomyPlusSeat(4, 'B'), EconomyPlusSeat(4, 'C')
      ),
      Economy -> Vector(
        EconomySeat(5, 'A'), EconomySeat(5, 'B'), EconomySeat(5, 'C'),
        EconomySeat(6, 'A'), EconomySeat(6, 'B'), EconomySeat(6, 'C'),
        EconomySeat(7, 'A'), EconomySeat(7, 'B'), EconomySeat(7, 'C'),
        EconomySeat(8, 'A'), EconomySeat(8, 'B'), EconomySeat(8, 'C'),
        EconomySeat(9, 'A'), EconomySeat(9, 'B'), EconomySeat(9, 'C'),
        EconomySeat(10, 'A'), EconomySeat(10, 'B'), EconomySeat(10, 'C'),
        EconomySeat(11, 'A'), EconomySeat(11, 'B'), EconomySeat(11, 'C'),
        EconomySeat(12, 'A'), EconomySeat(12, 'B'), EconomySeat(12, 'C'),
        EconomySeat(13, 'A'), EconomySeat(13, 'B'), EconomySeat(13, 'C')
      )
    )
  }

  case object MD11 extends AircraftModel with WideBodyJet {
    val seats = HashMap[SeatingClass, Vector[Seat]](
      FirstClass -> Vector.empty[Seat],
      BusinessClass -> Vector(
        BusinessClassSeat(1, 'A'), BusinessClassSeat(1, 'B'), BusinessClassSeat(1, 'C'), BusinessClassSeat(1, 'D'),
        BusinessClassSeat(2, 'A'), BusinessClassSeat(2, 'B'), BusinessClassSeat(2, 'C'), BusinessClassSeat(2, 'D'),
        BusinessClassSeat(3, 'A'), BusinessClassSeat(3, 'B'), BusinessClassSeat(3, 'C'), BusinessClassSeat(3, 'D'),
        BusinessClassSeat(4, 'A'), BusinessClassSeat(4, 'B'), BusinessClassSeat(4, 'C'), BusinessClassSeat(4, 'D'),
        BusinessClassSeat(5, 'A'), BusinessClassSeat(5, 'B'), BusinessClassSeat(5, 'C'), BusinessClassSeat(5, 'D')
      ),
      EconomyPlus -> Vector(
        EconomyPlusSeat(6, 'A'), EconomyPlusSeat(6, 'B'), EconomyPlusSeat(6, 'C'),
        EconomyPlusSeat(6, 'D'), EconomyPlusSeat(6, 'E'), EconomyPlusSeat(6, 'F'),
        EconomyPlusSeat(6, 'G'), EconomyPlusSeat(6, 'H'), EconomyPlusSeat(6, 'J'),
        EconomyPlusSeat(7, 'A'), EconomyPlusSeat(7, 'B'), EconomyPlusSeat(7, 'C'),
        EconomyPlusSeat(7, 'D'), EconomyPlusSeat(7, 'E'), EconomyPlusSeat(7, 'F'),
        EconomyPlusSeat(7, 'G'), EconomyPlusSeat(7, 'H'), EconomyPlusSeat(7, 'J'),
        EconomyPlusSeat(8, 'A'), EconomyPlusSeat(8, 'B'), EconomyPlusSeat(8, 'C'),
        EconomyPlusSeat(8, 'D'), EconomyPlusSeat(8, 'E'), EconomyPlusSeat(8, 'F'),
        EconomyPlusSeat(8, 'G'), EconomyPlusSeat(8, 'H'), EconomyPlusSeat(8, 'J'),
        EconomyPlusSeat(9, 'A'), EconomyPlusSeat(9, 'B'), EconomyPlusSeat(9, 'C'),
        EconomyPlusSeat(9, 'D'), EconomyPlusSeat(9, 'E'), EconomyPlusSeat(9, 'F'),
        EconomyPlusSeat(9, 'G'), EconomyPlusSeat(9, 'H'), EconomyPlusSeat(9, 'J'),
        EconomyPlusSeat(10, 'A'), EconomyPlusSeat(10, 'B'), EconomyPlusSeat(10, 'C'),
        EconomyPlusSeat(10, 'D'), EconomyPlusSeat(10, 'E'), EconomyPlusSeat(10, 'F'),
        EconomyPlusSeat(10, 'G'), EconomyPlusSeat(10, 'H'), EconomyPlusSeat(10, 'J'),
        EconomyPlusSeat(11, 'A'), EconomyPlusSeat(11, 'B'), EconomyPlusSeat(11, 'C'),
        EconomyPlusSeat(11, 'D'), EconomyPlusSeat(11, 'E'), EconomyPlusSeat(11, 'F'),
        EconomyPlusSeat(11, 'G'), EconomyPlusSeat(11, 'H'), EconomyPlusSeat(11, 'J'),
        EconomyPlusSeat(12, 'A'), EconomyPlusSeat(12, 'B'), EconomyPlusSeat(12, 'C'),
        EconomyPlusSeat(12, 'D'), EconomyPlusSeat(12, 'E'), EconomyPlusSeat(12, 'F'),
        EconomyPlusSeat(12, 'G'), EconomyPlusSeat(12, 'H'), EconomyPlusSeat(12, 'J'),
        EconomyPlusSeat(13, 'A'), EconomyPlusSeat(13, 'B'), EconomyPlusSeat(13, 'C'),
        EconomyPlusSeat(13, 'D'), EconomyPlusSeat(13, 'E'), EconomyPlusSeat(13, 'F'),
        EconomyPlusSeat(13, 'G'), EconomyPlusSeat(13, 'H'), EconomyPlusSeat(13, 'J'),
        EconomyPlusSeat(14, 'A'), EconomyPlusSeat(14, 'B'), EconomyPlusSeat(14, 'C'),
        EconomyPlusSeat(14, 'D'), EconomyPlusSeat(14, 'E'), EconomyPlusSeat(14, 'F'),
        EconomyPlusSeat(14, 'G'), EconomyPlusSeat(14, 'H'), EconomyPlusSeat(14, 'J'),
        EconomyPlusSeat(15, 'A'), EconomyPlusSeat(15, 'B'), EconomyPlusSeat(15, 'C'),
        EconomyPlusSeat(15, 'D'), EconomyPlusSeat(15, 'E'), EconomyPlusSeat(15, 'F'),
        EconomyPlusSeat(15, 'G'), EconomyPlusSeat(15, 'H'), EconomyPlusSeat(15, 'J'),
        EconomyPlusSeat(16, 'A'), EconomyPlusSeat(16, 'B'), EconomyPlusSeat(16, 'C'),
        EconomyPlusSeat(16, 'D'), EconomyPlusSeat(16, 'E'), EconomyPlusSeat(16, 'F'),
        EconomyPlusSeat(16, 'G'), EconomyPlusSeat(16, 'H'), EconomyPlusSeat(16, 'J'),
        EconomyPlusSeat(20, 'A'), EconomyPlusSeat(20, 'B'), EconomyPlusSeat(20, 'C'),
        EconomyPlusSeat(20, 'D'), EconomyPlusSeat(20, 'E'), EconomyPlusSeat(20, 'F'),
        EconomyPlusSeat(20, 'G'), EconomyPlusSeat(20, 'H'), EconomyPlusSeat(20, 'J'),
        EconomyPlusSeat(21, 'A'), EconomyPlusSeat(21, 'B'), EconomyPlusSeat(21, 'C'),
        EconomyPlusSeat(21, 'D'), EconomyPlusSeat(21, 'E'), EconomyPlusSeat(21, 'F'),
        EconomyPlusSeat(21, 'G'), EconomyPlusSeat(21, 'H'), EconomyPlusSeat(21, 'J'),
        EconomyPlusSeat(22, 'A'), EconomyPlusSeat(22, 'B'), EconomyPlusSeat(22, 'C'),
        EconomyPlusSeat(22, 'D'), EconomyPlusSeat(22, 'E'), EconomyPlusSeat(22, 'F'),
        EconomyPlusSeat(22, 'G'), EconomyPlusSeat(22, 'H'), EconomyPlusSeat(22, 'J')
      ),
      Economy -> Vector(
        EconomySeat(17, 'A'), EconomySeat(17, 'B'), EconomySeat(17, 'C'),
        EconomySeat(17, 'D'), EconomySeat(17, 'E'), EconomySeat(17, 'F'),
        EconomySeat(17, 'G'), EconomySeat(17, 'H'), EconomySeat(17, 'J'),
        EconomySeat(18, 'A'), EconomySeat(18, 'B'), EconomySeat(18, 'C'),
        EconomySeat(18, 'D'), EconomySeat(18, 'E'), EconomySeat(18, 'F'),
        EconomySeat(18, 'G'), EconomySeat(18, 'H'), EconomySeat(18, 'J'),
        EconomySeat(19, 'A'), EconomySeat(19, 'B'), EconomySeat(19, 'C'),
        EconomySeat(19, 'D'), EconomySeat(19, 'E'), EconomySeat(19, 'F'),
        EconomySeat(19, 'G'), EconomySeat(19, 'H'), EconomySeat(19, 'J'),
        EconomySeat(23, 'A'), EconomySeat(23, 'B'), EconomySeat(23, 'C'),
        EconomySeat(23, 'D'), EconomySeat(23, 'E'), EconomySeat(23, 'F'),
        EconomySeat(23, 'G'), EconomySeat(23, 'H'), EconomySeat(23, 'J'),
        EconomySeat(24, 'A'), EconomySeat(24, 'B'), EconomySeat(24, 'C'),
        EconomySeat(24, 'D'), EconomySeat(24, 'E'), EconomySeat(24, 'F'),
        EconomySeat(24, 'G'), EconomySeat(24, 'H'), EconomySeat(24, 'J'),
        EconomySeat(25, 'A'), EconomySeat(25, 'B'), EconomySeat(25, 'C'),
        EconomySeat(25, 'D'), EconomySeat(25, 'E'), EconomySeat(25, 'F'),
        EconomySeat(25, 'G'), EconomySeat(25, 'H'), EconomySeat(25, 'J'),
        EconomySeat(26, 'A'), EconomySeat(26, 'B'), EconomySeat(26, 'C'),
        EconomySeat(26, 'D'), EconomySeat(26, 'E'), EconomySeat(26, 'F'),
        EconomySeat(26, 'G'), EconomySeat(26, 'H'), EconomySeat(26, 'J'),
        EconomySeat(27, 'A'), EconomySeat(27, 'B'), EconomySeat(27, 'C'),
        EconomySeat(27, 'D'), EconomySeat(27, 'E'), EconomySeat(27, 'F'),
        EconomySeat(27, 'G'), EconomySeat(27, 'H'), EconomySeat(27, 'J'),
        EconomySeat(28, 'A'), EconomySeat(28, 'B'), EconomySeat(28, 'C'),
        EconomySeat(28, 'D'), EconomySeat(28, 'E'), EconomySeat(28, 'F'),
        EconomySeat(28, 'G'), EconomySeat(28, 'H'), EconomySeat(28, 'J'),
        EconomySeat(29, 'A'), EconomySeat(29, 'B'), EconomySeat(29, 'C'),
        EconomySeat(29, 'D'), EconomySeat(29, 'E'), EconomySeat(29, 'F'),
        EconomySeat(29, 'G'), EconomySeat(29, 'H'), EconomySeat(29, 'J'),
        EconomySeat(30, 'A'), EconomySeat(30, 'B'), EconomySeat(30, 'C'),
        EconomySeat(30, 'D'), EconomySeat(30, 'E'), EconomySeat(30, 'F'),
        EconomySeat(30, 'G'), EconomySeat(30, 'H'), EconomySeat(30, 'J'),
        EconomySeat(31, 'A'), EconomySeat(31, 'B'), EconomySeat(31, 'C'),
        EconomySeat(31, 'D'), EconomySeat(31, 'E'), EconomySeat(31, 'F'),
        EconomySeat(31, 'G'), EconomySeat(31, 'H'), EconomySeat(31, 'J'),
        EconomySeat(32, 'A'), EconomySeat(32, 'B'), EconomySeat(32, 'C'),
        EconomySeat(32, 'D'), EconomySeat(32, 'E'), EconomySeat(32, 'F'),
        EconomySeat(32, 'G'), EconomySeat(32, 'H'), EconomySeat(32, 'J'),
        EconomySeat(33, 'A'), EconomySeat(33, 'B'), EconomySeat(33, 'C'),
        EconomySeat(33, 'D'), EconomySeat(33, 'E'), EconomySeat(33, 'F'),
        EconomySeat(33, 'G'), EconomySeat(33, 'H'), EconomySeat(33, 'J'),
        EconomySeat(34, 'A'), EconomySeat(34, 'B'), EconomySeat(34, 'C'),
        EconomySeat(34, 'D'), EconomySeat(34, 'E'), EconomySeat(34, 'F'),
        EconomySeat(34, 'G'), EconomySeat(34, 'H'), EconomySeat(34, 'J'),
        EconomySeat(35, 'A'), EconomySeat(35, 'B'), EconomySeat(35, 'C'),
        EconomySeat(35, 'D'), EconomySeat(35, 'E'), EconomySeat(35, 'F'),
        EconomySeat(35, 'G'), EconomySeat(35, 'H'), EconomySeat(35, 'J'),
        EconomySeat(36, 'A'), EconomySeat(36, 'B'), EconomySeat(36, 'C'),
        EconomySeat(36, 'D'), EconomySeat(36, 'E'), EconomySeat(36, 'F'),
        EconomySeat(36, 'G'), EconomySeat(36, 'H'), EconomySeat(36, 'J'),
        EconomySeat(37, 'A'), EconomySeat(37, 'B'), EconomySeat(37, 'C'),
        EconomySeat(37, 'D'), EconomySeat(37, 'E'), EconomySeat(37, 'F'),
        EconomySeat(37, 'G'), EconomySeat(37, 'H'), EconomySeat(37, 'J'),
        EconomySeat(38, 'A'), EconomySeat(38, 'B'), EconomySeat(38, 'C'),
        EconomySeat(38, 'D'), EconomySeat(38, 'E'), EconomySeat(38, 'F'),
        EconomySeat(38, 'G'), EconomySeat(38, 'H'), EconomySeat(38, 'J'),
        EconomySeat(39, 'A'), EconomySeat(39, 'B'), EconomySeat(39, 'C'),
        EconomySeat(39, 'D'), EconomySeat(39, 'E'), EconomySeat(39, 'F'),
        EconomySeat(39, 'G'), EconomySeat(39, 'H'), EconomySeat(39, 'J'),
        EconomySeat(40, 'A'), EconomySeat(40, 'B'), EconomySeat(40, 'C'),
        EconomySeat(40, 'D'), EconomySeat(40, 'E'), EconomySeat(40, 'F'),
        EconomySeat(40, 'G'), EconomySeat(40, 'H'), EconomySeat(40, 'J')
      )
    )
  }

  case object B747 extends AircraftModel with WideBodyJet {
    val seats = HashMap[SeatingClass, Vector[Seat]](
      FirstClass -> Vector(
        FirstClassSeat(1, 'A'), FirstClassSeat(1, 'D'),
        FirstClassSeat(2, 'A'), FirstClassSeat(2, 'D'),
        FirstClassSeat(3, 'A'), FirstClassSeat(3, 'D'),
        FirstClassSeat(4, 'A'), FirstClassSeat(4, 'D'),
        FirstClassSeat(5, 'A'), FirstClassSeat(5, 'D')
      ),
      BusinessClass -> Vector(
        BusinessClassSeat(6, 'A'), BusinessClassSeat(6, 'B'), BusinessClassSeat(6, 'C'), BusinessClassSeat(6, 'D'),
        BusinessClassSeat(7, 'A'), BusinessClassSeat(7, 'B'), BusinessClassSeat(7, 'C'), BusinessClassSeat(7, 'D'),
        BusinessClassSeat(8, 'A'), BusinessClassSeat(8, 'B'), BusinessClassSeat(8, 'C'), BusinessClassSeat(8, 'D'),
        BusinessClassSeat(9, 'A'), BusinessClassSeat(9, 'B'), BusinessClassSeat(9, 'C'), BusinessClassSeat(9, 'D'),
        BusinessClassSeat(10, 'A'), BusinessClassSeat(10, 'B'), BusinessClassSeat(10, 'C'), BusinessClassSeat(10, 'D')
      ),
      EconomyPlus -> Vector(
        EconomyPlusSeat(6, 'A'), EconomyPlusSeat(6, 'B'), EconomyPlusSeat(6, 'C'),
        EconomyPlusSeat(6, 'D'), EconomyPlusSeat(6, 'E'), EconomyPlusSeat(6, 'F'),
        EconomyPlusSeat(6, 'G'), EconomyPlusSeat(6, 'H'), EconomyPlusSeat(6, 'J'),
        EconomyPlusSeat(7, 'A'), EconomyPlusSeat(7, 'B'), EconomyPlusSeat(7, 'C'),
        EconomyPlusSeat(7, 'D'), EconomyPlusSeat(7, 'E'), EconomyPlusSeat(7, 'F'),
        EconomyPlusSeat(7, 'G'), EconomyPlusSeat(7, 'H'), EconomyPlusSeat(7, 'J'),
        EconomyPlusSeat(8, 'A'), EconomyPlusSeat(8, 'B'), EconomyPlusSeat(8, 'C'),
        EconomyPlusSeat(8, 'D'), EconomyPlusSeat(8, 'E'), EconomyPlusSeat(8, 'F'),
        EconomyPlusSeat(8, 'G'), EconomyPlusSeat(8, 'H'), EconomyPlusSeat(8, 'J'),
        EconomyPlusSeat(9, 'A'), EconomyPlusSeat(9, 'B'), EconomyPlusSeat(9, 'C'),
        EconomyPlusSeat(9, 'D'), EconomyPlusSeat(9, 'E'), EconomyPlusSeat(9, 'F'),
        EconomyPlusSeat(9, 'G'), EconomyPlusSeat(9, 'H'), EconomyPlusSeat(9, 'J'),
        EconomyPlusSeat(10, 'A'), EconomyPlusSeat(10, 'B'), EconomyPlusSeat(10, 'C'),
        EconomyPlusSeat(10, 'D'), EconomyPlusSeat(10, 'E'), EconomyPlusSeat(10, 'F'),
        EconomyPlusSeat(10, 'G'), EconomyPlusSeat(10, 'H'), EconomyPlusSeat(10, 'J'),
        EconomyPlusSeat(11, 'A'), EconomyPlusSeat(11, 'B'), EconomyPlusSeat(11, 'C'),
        EconomyPlusSeat(11, 'D'), EconomyPlusSeat(11, 'E'), EconomyPlusSeat(11, 'F'),
        EconomyPlusSeat(11, 'G'), EconomyPlusSeat(11, 'H'), EconomyPlusSeat(11, 'J'),
        EconomyPlusSeat(12, 'A'), EconomyPlusSeat(12, 'B'), EconomyPlusSeat(12, 'C'),
        EconomyPlusSeat(12, 'D'), EconomyPlusSeat(12, 'E'), EconomyPlusSeat(12, 'F'),
        EconomyPlusSeat(12, 'G'), EconomyPlusSeat(12, 'H'), EconomyPlusSeat(12, 'J'),
        EconomyPlusSeat(13, 'A'), EconomyPlusSeat(13, 'B'), EconomyPlusSeat(13, 'C'),
        EconomyPlusSeat(13, 'D'), EconomyPlusSeat(13, 'E'), EconomyPlusSeat(13, 'F'),
        EconomyPlusSeat(13, 'G'), EconomyPlusSeat(13, 'H'), EconomyPlusSeat(13, 'J'),
        EconomyPlusSeat(14, 'A'), EconomyPlusSeat(14, 'B'), EconomyPlusSeat(14, 'C'),
        EconomyPlusSeat(14, 'D'), EconomyPlusSeat(14, 'E'), EconomyPlusSeat(14, 'F'),
        EconomyPlusSeat(14, 'G'), EconomyPlusSeat(14, 'H'), EconomyPlusSeat(14, 'J'),
        EconomyPlusSeat(15, 'A'), EconomyPlusSeat(15, 'B'), EconomyPlusSeat(15, 'C'),
        EconomyPlusSeat(15, 'D'), EconomyPlusSeat(15, 'E'), EconomyPlusSeat(15, 'F'),
        EconomyPlusSeat(15, 'G'), EconomyPlusSeat(15, 'H'), EconomyPlusSeat(15, 'J'),
        EconomyPlusSeat(16, 'A'), EconomyPlusSeat(16, 'B'), EconomyPlusSeat(16, 'C'),
        EconomyPlusSeat(16, 'D'), EconomyPlusSeat(16, 'E'), EconomyPlusSeat(16, 'F'),
        EconomyPlusSeat(16, 'G'), EconomyPlusSeat(16, 'H'), EconomyPlusSeat(16, 'J'),
        EconomyPlusSeat(20, 'A'), EconomyPlusSeat(20, 'B'), EconomyPlusSeat(20, 'C'),
        EconomyPlusSeat(20, 'D'), EconomyPlusSeat(20, 'E'), EconomyPlusSeat(20, 'F'),
        EconomyPlusSeat(20, 'G'), EconomyPlusSeat(20, 'H'), EconomyPlusSeat(20, 'J'),
        EconomyPlusSeat(21, 'A'), EconomyPlusSeat(21, 'B'), EconomyPlusSeat(21, 'C'),
        EconomyPlusSeat(21, 'D'), EconomyPlusSeat(21, 'E'), EconomyPlusSeat(21, 'F'),
        EconomyPlusSeat(21, 'G'), EconomyPlusSeat(21, 'H'), EconomyPlusSeat(21, 'J'),
        EconomyPlusSeat(22, 'A'), EconomyPlusSeat(22, 'B'), EconomyPlusSeat(22, 'C'),
        EconomyPlusSeat(22, 'D'), EconomyPlusSeat(22, 'E'), EconomyPlusSeat(22, 'F'),
        EconomyPlusSeat(22, 'G'), EconomyPlusSeat(22, 'H'), EconomyPlusSeat(22, 'J')
      ),
      Economy -> Vector(
        EconomySeat(17, 'A'), EconomySeat(17, 'B'), EconomySeat(17, 'C'),
        EconomySeat(17, 'D'), EconomySeat(17, 'E'), EconomySeat(17, 'F'),
        EconomySeat(17, 'G'), EconomySeat(17, 'H'), EconomySeat(17, 'J'),
        EconomySeat(18, 'A'), EconomySeat(18, 'B'), EconomySeat(18, 'C'),
        EconomySeat(18, 'D'), EconomySeat(18, 'E'), EconomySeat(18, 'F'),
        EconomySeat(18, 'G'), EconomySeat(18, 'H'), EconomySeat(18, 'J'),
        EconomySeat(19, 'A'), EconomySeat(19, 'B'), EconomySeat(19, 'C'),
        EconomySeat(19, 'D'), EconomySeat(19, 'E'), EconomySeat(19, 'F'),
        EconomySeat(19, 'G'), EconomySeat(19, 'H'), EconomySeat(19, 'J'),
        EconomySeat(23, 'A'), EconomySeat(23, 'B'), EconomySeat(23, 'C'),
        EconomySeat(23, 'D'), EconomySeat(23, 'E'), EconomySeat(23, 'F'),
        EconomySeat(23, 'G'), EconomySeat(23, 'H'), EconomySeat(23, 'J'),
        EconomySeat(24, 'A'), EconomySeat(24, 'B'), EconomySeat(24, 'C'),
        EconomySeat(24, 'D'), EconomySeat(24, 'E'), EconomySeat(24, 'F'),
        EconomySeat(24, 'G'), EconomySeat(24, 'H'), EconomySeat(24, 'J'),
        EconomySeat(25, 'A'), EconomySeat(25, 'B'), EconomySeat(25, 'C'),
        EconomySeat(25, 'D'), EconomySeat(25, 'E'), EconomySeat(25, 'F'),
        EconomySeat(25, 'G'), EconomySeat(25, 'H'), EconomySeat(25, 'J'),
        EconomySeat(26, 'A'), EconomySeat(26, 'B'), EconomySeat(26, 'C'),
        EconomySeat(26, 'D'), EconomySeat(26, 'E'), EconomySeat(26, 'F'),
        EconomySeat(26, 'G'), EconomySeat(26, 'H'), EconomySeat(26, 'J'),
        EconomySeat(27, 'A'), EconomySeat(27, 'B'), EconomySeat(27, 'C'),
        EconomySeat(27, 'D'), EconomySeat(27, 'E'), EconomySeat(27, 'F'),
        EconomySeat(27, 'G'), EconomySeat(27, 'H'), EconomySeat(27, 'J'),
        EconomySeat(28, 'A'), EconomySeat(28, 'B'), EconomySeat(28, 'C'),
        EconomySeat(28, 'D'), EconomySeat(28, 'E'), EconomySeat(28, 'F'),
        EconomySeat(28, 'G'), EconomySeat(28, 'H'), EconomySeat(28, 'J'),
        EconomySeat(29, 'A'), EconomySeat(29, 'B'), EconomySeat(29, 'C'),
        EconomySeat(29, 'D'), EconomySeat(29, 'E'), EconomySeat(29, 'F'),
        EconomySeat(29, 'G'), EconomySeat(29, 'H'), EconomySeat(29, 'J'),
        EconomySeat(30, 'A'), EconomySeat(30, 'B'), EconomySeat(30, 'C'),
        EconomySeat(30, 'D'), EconomySeat(30, 'E'), EconomySeat(30, 'F'),
        EconomySeat(30, 'G'), EconomySeat(30, 'H'), EconomySeat(30, 'J'),
        EconomySeat(31, 'A'), EconomySeat(31, 'B'), EconomySeat(31, 'C'),
        EconomySeat(31, 'D'), EconomySeat(31, 'E'), EconomySeat(31, 'F'),
        EconomySeat(31, 'G'), EconomySeat(31, 'H'), EconomySeat(31, 'J'),
        EconomySeat(32, 'A'), EconomySeat(32, 'B'), EconomySeat(32, 'C'),
        EconomySeat(32, 'D'), EconomySeat(32, 'E'), EconomySeat(32, 'F'),
        EconomySeat(32, 'G'), EconomySeat(32, 'H'), EconomySeat(32, 'J'),
        EconomySeat(33, 'A'), EconomySeat(33, 'B'), EconomySeat(33, 'C'),
        EconomySeat(33, 'D'), EconomySeat(33, 'E'), EconomySeat(33, 'F'),
        EconomySeat(33, 'G'), EconomySeat(33, 'H'), EconomySeat(33, 'J'),
        EconomySeat(34, 'A'), EconomySeat(34, 'B'), EconomySeat(34, 'C'),
        EconomySeat(34, 'D'), EconomySeat(34, 'E'), EconomySeat(34, 'F'),
        EconomySeat(34, 'G'), EconomySeat(34, 'H'), EconomySeat(34, 'J'),
        EconomySeat(35, 'A'), EconomySeat(35, 'B'), EconomySeat(35, 'C'),
        EconomySeat(35, 'D'), EconomySeat(35, 'E'), EconomySeat(35, 'F'),
        EconomySeat(35, 'G'), EconomySeat(35, 'H'), EconomySeat(35, 'J'),
        EconomySeat(36, 'A'), EconomySeat(36, 'B'), EconomySeat(36, 'C'),
        EconomySeat(36, 'D'), EconomySeat(36, 'E'), EconomySeat(36, 'F'),
        EconomySeat(36, 'G'), EconomySeat(36, 'H'), EconomySeat(36, 'J'),
        EconomySeat(37, 'A'), EconomySeat(37, 'B'), EconomySeat(37, 'C'),
        EconomySeat(37, 'D'), EconomySeat(37, 'E'), EconomySeat(37, 'F'),
        EconomySeat(37, 'G'), EconomySeat(37, 'H'), EconomySeat(37, 'J'),
        EconomySeat(38, 'A'), EconomySeat(38, 'B'), EconomySeat(38, 'C'),
        EconomySeat(38, 'D'), EconomySeat(38, 'E'), EconomySeat(38, 'F'),
        EconomySeat(38, 'G'), EconomySeat(38, 'H'), EconomySeat(38, 'J'),
        EconomySeat(39, 'A'), EconomySeat(39, 'B'), EconomySeat(39, 'C'),
        EconomySeat(39, 'D'), EconomySeat(39, 'E'), EconomySeat(39, 'F'),
        EconomySeat(39, 'G'), EconomySeat(39, 'H'), EconomySeat(39, 'J'),
        EconomySeat(40, 'A'), EconomySeat(40, 'B'), EconomySeat(40, 'C'),
        EconomySeat(40, 'D'), EconomySeat(40, 'E'), EconomySeat(40, 'F'),
        EconomySeat(40, 'G'), EconomySeat(40, 'H'), EconomySeat(40, 'J')
      )
    )
  }

  val sfoDeparture = (DateTime.now + 2.days).withTime(1, 49, 0, 0).
    withZone(DateTimeZone.forID("US/Pacific"))

  val ewrArrival = (DateTime.now + 2.days).withTime(10, 29, 0, 0).
    withZone(DateTimeZone.forID("US/Eastern"))

  val sfoArrival = (DateTime.now + 8.days).withTime(6, 14, 0, 0).
    withZone(DateTimeZone.forID("US/Pacific"))

  val ewrDeparture = (DateTime.now + 8.days).withTime(2, 59, 0, 0).
    withZone(DateTimeZone.forID("US/Eastern"))

  val SFO = AirportCode("SFO")

  val EWR = AirportCode("EWR")

  def sfToNewarkSchedule = Schedule(
    FlightLeg(SFO, sfoDeparture),
    FlightLeg(EWR, ewrArrival)
  )

  def newarkToSFSchedule = Schedule(
    FlightLeg(EWR, ewrDeparture),
    FlightLeg(SFO, sfoDeparture)
  )

  implicit val moneyContext = defaultMoneyContext
  implicit val moneyNum = new MoneyNumeric()

  def sfoToEwrSegment =
    new Flight(
      FlightNumber("UA", 1683),
      Aircraft(B747),
      sfToNewarkSchedule,
      USD(256.15)
    )

  def ewrToSfoSegment =
    new Flight(
      FlightNumber("UA", 1601),
      Aircraft(B747),
      newarkToSFSchedule,
      USD(382.26)
    )

}

/*
case object A320 extends AircraftModel with NarrowBodyJet {

}
*/
