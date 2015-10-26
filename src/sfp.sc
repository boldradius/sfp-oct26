abstract class A {
  def user : String = ""
}
class B extends A {
  //val user2: String
  //def user = user2
  override val user = "Test"
}
object Vector2 {
  def apply(a: Int*) = Vector(a)
}

val vectorA = Vector(1, 2)
val vectorB =Vector("x", "y")

val i = for {
  a <- vectorA if a > 1
  b <- vectorB
} yield (a, b)

i
vectorA.withFilter(a => a  > 1)
  .flatMap(a => vectorB.map(b =>
    (a, b)))

for (a <- Vector(1,2)) yield a + 1
Vector(1,2).map(a => a + 1)

for (a <- vectorA) println(a)
