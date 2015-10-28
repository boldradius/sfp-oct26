package misc



class Queue[+A] private (val elements: Seq[A]) {
  def enqueue[B >: A](a: B) : Queue[B] =
    new Queue(a +: elements)
  def dequeue : (A, Queue[A]) = elements match {
    case a +: others => (a, new Queue(others))
    case _ => throw new UnsupportedOperationException("Queue is empty")
  }

  override def hashCode(): Int = elements.hashCode()

  override def equals(obj: Any): Boolean =
    obj match {
      case that : Queue[A] => elements == that.elements
      case _ => false
    }

  override def toString: String = elements.toString
}

object Queue {
  def apply[A](elements: A*) : Queue[A] = new Queue(elements)
  val q = Queue(1, 2 , 3)
}