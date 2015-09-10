package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class PowerOfFolding extends FreeSpec with MustMatchers {
  import TestHelpers._
  import squants.market._
  import Folding._

  "foldLeft" - {
    "summing a Seq of dollar ammounts" - {
      "Seq.empty" in {
        val initValue = USD(0)
        initValue mustBe foldLeft(Seq.empty[Money])(initValue)(_ + _)
      }
    }
  }
}
