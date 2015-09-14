package training.scala.air_scala

object Boarding {

  import training.scala.air_scala.airline.Passenger

  object BoardingQueue {

    def apply[A <: Passenger](elements: A*): BoardingQueue[A] =
      new BoardingQueue(elements.toVector)
  }

  class BoardingQueue[+A <: Passenger] private (private val elements: Seq[A]) {

    def dequeue: (A, BoardingQueue[A]) =
      elements match {
        case head +: tail => (head, new BoardingQueue(tail))
        case _ => throw new UnsupportedOperationException("Cannot dequeue from an empty queue.")
      }

    // TODO: This should be solved with a Lower Bound, like it is in Advanced Scala
    // I didn't change it yet to make sure I didn't break code expectations,
    // but this type boundary is confusing and not something we want to demo yet.
    // -bwm-
    def enqueue[B >: A <: Passenger](element: B): BoardingQueue[B] =
      new BoardingQueue(elements :+ element)
  }
}
