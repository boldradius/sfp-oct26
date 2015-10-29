package training.scala.air_scala.misc

/**
 * Created by pardis on 15-10-27.
 */


class Queue[+A](val elements: Seq[A]) {

  override def equals(obj: Any): Boolean = {
    obj match {
      case that: Queue[A] => elements == that.elements
      case _ => false
    }
  }

  override def hashCode(): Int = {
    this.elements.hashCode()
  }

  def dequeue(): (A, Queue[A]) = elements match {
    case a +: others => (a, new Queue(others))
    case _ => throw new UnsupportedOperationException
  }

  def enqueue[B >: A](a: B): Queue[B] = new Queue(a +: elements)

}


object Queue {

  def apply[A](elements: A*): Queue[A] = {
    new Queue(elements)
  }
}