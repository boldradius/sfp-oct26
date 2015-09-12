package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class VarianceAndBounds extends FreeSpec with MustMatchers {
  import Boarding._
  import domain._

  "boarding queue" - {
    val tom = Canadian("Tom")
    val boardingQueue = BoardingQueue(tom)
    "enqueuing an arbitary Object" - {
      "fails compilation" in {
        """boardingQueue.enqueue("arbitrary")""" mustNot compile
      }
    }
    "enqueuing another Canadian passenger" - {
      "yields a BoardingQueue[Canadian]" in {
        type QueueOfCanadians = BoardingQueue[Canadian]
        val joe = Canadian("Joe")
        boardingQueue.enqueue(joe) mustBe a[QueueOfCanadians]
      }
    }
    "enqueuing an American passenger" - {
      "yields a BoardingQueue[Canadian]" in {
        type QueueOfPassengers = BoardingQueue[Passenger]
        type QueueOfCanadians = BoardingQueue[Canadian]
        val sally = American("Sally")
        boardingQueue.enqueue(sally) mustBe a[QueueOfCanadians]
      }
    }
  }
}
