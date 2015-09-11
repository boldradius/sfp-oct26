package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class TypeClasses extends FreeSpec with MustMatchers {
  import domain._
  import NonPlaneLandings._

  "an airport that has a runway" - {
    "can accomodate anything that can land on a runway" - {
      "jet" in {
        def jet: AircraftType = `747`
        canLandAt(jet, "airport") mustBe true
      }
    }
    "another jet" in {
      def jet: AircraftType = MD11
      canLandAt(jet, "airport") mustBe true
    }
  }
  "an airport that has a landing pad" - {
    "can accomodate anything that can land on a landing pad" - {
      "ospray" in {
        def ospray: AircraftType = Ospray
        canLandAt(ospray, "airport") mustBe false
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
        canLandAt(apache, "airport") mustBe false
      }
    }
  }
}
