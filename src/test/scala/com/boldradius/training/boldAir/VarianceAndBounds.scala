package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class VarianceAndBounds extends FreeSpec with MustMatchers {
  import Boarding._
  import domain._

  "boarding queue" - {
    val tom = Male("Tom")
    val boardingQueue = BoardingQueue(tom)
    "enqueuing an arbitary Object" - {
      "fails compilation" in {
        """boardingQueue.enqueue("arbitrary")""" mustNot compile
      }
    }
    "enqueuing another male passenger" - {
      "yields a BoardingQueue[Male]" in {
        type QueueOfMales = BoardingQueue[Male]
        val joe = Male("Joe")
        boardingQueue.enqueue(joe) mustBe a[QueueOfMales]
      }
    }
    "enqueuing a female passenger" - {
      "yields a BoardingQueue[Male]" in {
        type QueueOfPassengers = BoardingQueue[Passenger]
        type QueueOfMales = BoardingQueue[Male]
        val sally = Female("Sally")
        boardingQueue.enqueue(sally) mustBe a[QueueOfMales]
      }
    }
  }
}
