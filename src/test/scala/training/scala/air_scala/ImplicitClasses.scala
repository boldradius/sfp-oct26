
package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class ImplicitClasses extends FreeSpec with MustMatchers {
  import FrequentFlyerMilesImplicitClass._

  "implicit conversion from Int to implicit value class FrequentFlyerMile" - {
    "example1" in {
      3000.enoughForCancun mustBe false
    }
    "example2" in {
      10000.enoughForCancun mustBe true
    }
  }
}
