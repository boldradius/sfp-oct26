package com.boldradius.training.boldAir

object PowerOfRecursion {
  def map[A,B](seq: Seq[A])(fn: A => B): Seq[B] = seq match {
    case Seq() => Seq()
    case Seq(first, rest @ _*) => fn(first) +: map(rest)(fn)
  }
}
