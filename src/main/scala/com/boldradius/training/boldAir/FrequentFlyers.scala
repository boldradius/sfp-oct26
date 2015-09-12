package com.boldradius.training.boldAir

object FrequentFlyerMilesImplicitClass {
  import scala.language.implicitConversions

  implicit class FrequentFlyerMile(val value: Int) extends AnyVal {
    def +(that: FrequentFlyerMile): FrequentFlyerMile =
      new FrequentFlyerMile(this.value + that.value)

    def enoughForCancun: Boolean =
      value >= 10000
  }
}

object FrequentFlyerMilesValueClass {
  import scala.language.implicitConversions

  def enoughForCancun(miles: FrequentFlyerMile): Boolean =
    miles.value >= 10000

  class FrequentFlyerMile(val value: Int) extends AnyVal {
    def +(that: FrequentFlyerMile): FrequentFlyerMile =
      new FrequentFlyerMile(this.value + that.value)

  }
  object FrequentFlyerMile {
    implicit def intToFrequentFlyerMile(miles: Int): FrequentFlyerMile =
      new FrequentFlyerMile(miles)
  }
}
