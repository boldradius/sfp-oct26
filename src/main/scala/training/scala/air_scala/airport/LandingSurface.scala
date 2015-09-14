package training.scala.air_scala.airport

// todo - let's leave out the helicopters and focus on types of runways
// i.e. can it take a 747? Or just a MD11 ? -bwm
sealed trait LandingSurface

case object LandingStrip extends LandingSurface

case object LandingPad extends LandingSurface

