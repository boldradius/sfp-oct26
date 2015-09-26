package training.scala

import scala.util.Try

package object air_scala {
  object ValidInt {
    def unapply(str: String): Boolean = Try(str.toInt).isSuccess
  }
}
