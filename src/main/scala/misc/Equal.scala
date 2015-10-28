package misc

object EqualExample {
  trait Equal[A] {
    def equal(a: A, b: A): Boolean
  }

  implicit val intEqual = new Equal[Int] {
    override def equal(a: Int, b: Int): Boolean =
      a == b
  }
  implicit val floatEqual = new Equal[Float] {
    override def equal(a: Float, b: Float): Boolean =
      a == b
  }

  implicit class EqualOps[A](a: A) {
    def ===(b: A)(implicit instance: Equal[A]) =
     instance.equal(a, b)
  }
  //implicit def toEqualOps[A](a: A) : EqualOps[A] =
  //  new EqualOps(a)

  1 === 2
  new EqualOps[Int](1) === 2
  new EqualOps[Int](1).===(2)
  new EqualOps[Int](1).===(2)(intEqual)

  1.0f === 2


  implicit val s = ""
  implicitly[String]
  implicitly[String](s)

  def equalInstance[A : Equal] = implicitly[Equal[A]]
  import scala.reflect._
  //def classTag[A : ClassTag] = implicitly[ClassTag[A]]

}

object Discard {
  implicit class Discardable[A](value: A) {
    def discard():Unit = ()
  }
}
              /*
object A {
  def f() : Int = {
    Set
    g().discard
    for {
      bob <- Table.user.insert(User("bob"))
      bob2 <- Table.user += User("bob2")
    } yield (bob, bob2)
  }             */

/*
object TypeErasure {
  List[String]() match {
    case _ : List[Int] => "List of Int"
    case _ : List[String] => "List of String"
  }
  }
  */
  object ByName {
    def multiplySquare(a: Double, b: => Double) : Double = {
      lazy val bLazy = b
      if (a == 0.0) 0.0
      else a * bLazy * bLazy
    }
    multiplySquare(2, 10 + 10)
  }
