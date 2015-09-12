package com.boldradius.training.boldAir

object Boarding {
  import domain.Passenger

  object BoardingQueue {

    def apply[A <: Passenger](elements: A*): BoardingQueue[A] =
      new BoardingQueue(elements.toVector)
  }

  class BoardingQueue[+A <: Passenger] private (private val elements: Seq[A]) {

    def dequeue: (A, BoardingQueue[A]) =
      elements match {
        case element +: elements => (element, new BoardingQueue(elements))
        case _ => throw new UnsupportedOperationException("Cannot dequeue from an empty queue")
      }

    def enqueue[B >: A <: Passenger](element: B): BoardingQueue[B] =
      new BoardingQueue(elements :+ element)
  }
}
