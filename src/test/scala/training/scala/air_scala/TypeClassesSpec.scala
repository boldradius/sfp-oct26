package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class TypeClassesSpec extends FreeSpec with MustMatchers {
  import domain._
  import NonPlaneLandings._

  val airport = Airport("LAS", "McCarran", Set(Gate("A", Set(`747`))),
                        Set(LandingStrip, LandingPad))

  "an airport that has a landing strip and a landing pad" - {
    "can accomodate anything that can land on a landing strip" - {
      "jets" in {
        def jet: AircraftType = `747`
        def anotherJet: AircraftType = MD11
        assert(canLandAt(jet, airport))
        assert(canLandAt(anotherJet, airport))
      }
    }
    "can accomodate anything that can land on a landing pad" - {
      "ospray" in {
        def ospray: AircraftType = Ospray
        assert(canLandAt(ospray, airport))
      }
      "helicopter" in {
        import Helicopters._
        // Note that we decouple operations from data, so we can
        // define helicopter ops even though HelicopterType is defined
        // elsewhere.
        implicit object landableHelicopterType extends Landable[HelicopterType] {
          def landingSurface(a: HelicopterType) = LandingPad
        }

        val apache: HelicopterType = Apache
        assert(canLandAt(apache, airport))
      }
    }
  }
}
