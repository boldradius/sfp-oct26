package training.scala.air_scala.airport

// todo - let's leave out the helicopters and focus on types of runways
// i.e. can it take a 747? Or just a MD11 ? -bwm
sealed trait LandingSurface {
  val size: Int
}

case object ShortRunway extends LandingSurface {
  val size = 1
}

case object MediumRunway extends LandingSurface {
  val size = 2
}

case object LongRunway extends LandingSurface {
  val size = 3
}

case object LandingPad extends LandingSurface {
  val size = 0
}

