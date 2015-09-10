package com.boldradius.training.boldAir

object PowerOfRecursion {
  def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq match {
    case first +: rest => f(first) +: map(rest)(f)
    case _ => Nil
  }
}
