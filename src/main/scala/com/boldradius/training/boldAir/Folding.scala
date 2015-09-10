package com.boldradius.training.boldAir
import scala.annotation.tailrec

object Folding {
  def foldLeft[A,B](seq: Seq[A])(initValue: B)(fn: (B,A) => B) = {
    @tailrec
    def helper(accum: B, seq: Seq[A]): B = seq match {
      case Seq() => accum
      case Seq(value, rest @ _*) => helper(fn(accum, value), rest)
    }
    helper(initValue, seq)
  }
}
