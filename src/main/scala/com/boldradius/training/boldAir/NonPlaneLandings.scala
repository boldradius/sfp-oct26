package com.boldradius.training.boldAir


object NonPlaneLandings {
  import domain._

  object Helicopters {
    sealed trait HelicopterType
    case object Apache extends HelicopterType
  }

  trait Landable[A] {
    def landingSurface(craft: A): LandingSurface
  }
  object Landable {
    implicit object landableAircraftType extends Landable[AircraftType] {
      def landingSurface(a: AircraftType) = a match {
        case MD11   => LandingStrip
        case `747`  => LandingStrip
        case Ospray => LandingPad
      }
    }
  }

  def canLandAt[A](craft: A, airport: Airport)
                  (implicit landableInstance: Landable[A]): Boolean =
    airport.landingSurfaces contains landableInstance.landingSurface(craft)


}
