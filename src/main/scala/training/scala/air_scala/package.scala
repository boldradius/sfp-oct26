package training.scala

import scala.util.Try
import scala.math.Ordering.Implicits._

package object air_scala {
  def isIncreasing2[A : Ordering](seq: Seq[A]) : Boolean =
    seq.zip(seq.tail).forall{case (a, b) => a < b}
      //implicitly[Ordering[A]].lt(a, b)}

  def isIncreasing[A <: Ordered[A]](seq: Seq[A]) : Boolean =
    seq.zip(seq.tail).forall{case (a, b) => a < b}

  object ValidInt {
    def unapply(str: String): Boolean = Try(str.toInt).isSuccess
  }
}
