package training.scala.air_scala

import training.scala.air_scala.aircraft.{B747, V22Osprey, MD11, AircraftModel}
import training.scala.air_scala.airline._
import training.scala.air_scala.airport.{LandingPad, LandingStrip, LandingSurface, Airport}


object NonPlaneLandings {

  object Helicopters {
    sealed trait HelicopterType
    case object Apache extends HelicopterType
  }

  trait Landable[A] {
    def landingSurface(craft: A): LandingSurface
  }
  object Landable {
    implicit object landableAircraftType extends Landable[AircraftModel] {
      def landingSurface(a: AircraftModel) = a match {
        case MD11   => LandingStrip
        case B747  => LandingStrip
        case V22Osprey => LandingPad
      }
    }
  }

  def canLandAt[A](craft: A, airport: Airport)
                  (implicit landableInstance: Landable[A]): Boolean =
    airport.landingSurfaces contains landableInstance.landingSurface(craft)


}
