package training.scala.air_scala

/**
 * Created by pardis on 15-10-27.
 */

object TestEqual {

  trait Equal[A] {
    def equal(a1: A, a2: A): Boolean
  }

  implicit val intEqual = new Equal[Int] {
    override def equal(a1: Int, a2: Int): Boolean = {
      a1 == a2
    }
  }

  implicit val stringEqual = new Equal[String] {
    override def equal(a1: String, a2: String): Boolean = {
      a1 == a2
    }
  }

  implicit class EqualOps[A](val a: A) extends AnyVal {
    def ===(b: A)(implicit instance: Equal[A]): Boolean = {
      instance.equal(a, b)
    }
  }
}

