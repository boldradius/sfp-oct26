package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class CustomValueClassesSpec extends FreeSpec with MustMatchers {
  import FrequentFlyerMilesValueClass._

  "implicit conversion from Int to value class FrequentFlyerMile" - {
    "example1" in {
      enoughForCancun(3000) mustBe false
    }
    "example2" in {
      enoughForCancun(10000) mustBe true
    }
  }
}
