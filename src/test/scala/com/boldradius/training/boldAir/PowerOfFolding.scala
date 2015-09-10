package com.boldradius.training.boldAir

import org.scalatest.FreeSpec

class PowerOfFolding extends FreeSpec {
  import TestHelpers._
  import squants.market._
  import Folding._

  "foldLeft" - {
    "summing a Seq of dollar ammounts" - {
      "Seq.empty" in {
        val initValue = USD(0)
        foldLeft(Seq.empty[Money])(initValue)(_ + _) === initValue
      }
    }
  }
}
