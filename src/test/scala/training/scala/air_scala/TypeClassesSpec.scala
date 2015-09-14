package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class TypeClassesSpec extends FreeSpec with MustMatchers {
  import domain._
  import NonPlaneLandings._

  val airport = Airport("LAS", "McCarran", Set(Gate("A", Set(B747))),
                        Set(LandingStrip, LandingPad))

  "an airport that has a landing strip and a landing pad" - {
    "can accomodate anything that can land on a landing strip" - {
      "jets" in {
        def jet: AircraftModel = B747
        def anotherJet: AircraftModel = MD11
        canLandAt(jet, airport) mustBe true
        canLandAt(anotherJet, airport) mustBe true
      }
    }
    "can accomodate anything that can land on a landing pad" - {
      "osprey" in {
        def osprey: AircraftModel = V22Osprey
        canLandAt(ospray, airport) mustBe true
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
        canLandAt(apache, airport) mustBe true
      }
    }
  }
}
