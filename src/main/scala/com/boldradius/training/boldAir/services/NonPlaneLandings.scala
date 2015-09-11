package com.boldradius.training.boldAir


object NonPlaneLandings {
  import domain._

  object Helicopters {
    sealed trait HelicopterType
    final case object Apache extends HelicopterType
  }

  trait Landable[A] {
    def landingSurface(craft: A): LandingFacility
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

  //TODO: FIx: String -> Airport
  def canLandAt[A](craft: A, airport: String)
                  (implicit landableInstance: Landable[A]): Boolean = {
    (landableInstance.landingSurface(craft) == LandingStrip)
  }


}
