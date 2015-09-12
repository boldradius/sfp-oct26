package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class TypeClasses extends FreeSpec with MustMatchers {
  import domain._
  import NonPlaneLandings._

  val airport = Airport("LAS", "McCarran", Set(Gate("A", Set(`747`))),
                        Set(LandingStrip, LandingPad))

  "an airport that has a landing strip and a landing pad" - {
    "can accomodate anything that can land on a landing strip" - {
      "jets" in {
        def jet: AircraftType = `747`
        def anotherJet: AircraftType = MD11
        canLandAt(jet, airport) mustBe true
        canLandAt(anotherJet, airport) mustBe true
      }
    }
    "can accomodate anything that can land on a landing pad" - {
      "ospray" in {
        def ospray: AircraftType = Ospray
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
