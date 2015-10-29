package training.scala

import scala.util.Try

import scala.math.Ordering.Implicits._

package object air_scala {
  object ValidInt {
    def unapply(str: String): Boolean = Try(str.toInt).isSuccess
  }

  def isIncreasing[A <: Ordered[A]](seq: Seq[A]): Boolean = {
    seq.sliding(2).forall {
      case s1 +: s2 +: _ => s1 < s2
      case _ => true
    }
  }

  def isIncreasing2[A: Ordering](as: Seq[A]): Boolean = {
    as.sliding(2).forall {
      case s1 +: s2 +: _ => s1 < s2 //implicitly[Ordering[A]].lt(s1, s2)
      case _ => true
    }
  }

}
