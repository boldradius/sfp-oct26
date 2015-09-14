package training.scala.air_scala


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
