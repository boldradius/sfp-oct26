package com.boldradius.training.boldAir

import org.scalatest.FreeSpec

class PowerOfRecursionSpec extends FreeSpec {
  import PowerOfRecursion._
  import squants.market._

  "map" - {
    "multiplying a Seq of dollar ammounts by a constant factor" - {
      val factor = 2
      "empty Seq" in {
        assert(map(Seq.empty[Money])(_ * factor) === Seq.empty[Money])
      }
      "non-empty Seq" in {
        val amount1 = USD(100)
        val amount2 = USD(200)
        assert(map(Seq(amount1, amount2))(_ * factor)
                 === Seq(amount1*factor, amount2*factor))
      }
    }
  }
}
