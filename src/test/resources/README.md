001-tail-recursion

# Exercise 1 > Tail Recursive Operations

For this exercise, we are going to demonstrate the use of Tail Recursive Methods to calculate the total price of an `Itinerary`

- Run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `001-tail-recursion`
- Add a new method, `totalPrice`, to the `Itinerary` companion object, which will determine the total cost of a given list of `Flight`s in an `Itinerary`.
  + It should accept a parameter `itinerary` of type `Itinerary`
  + **HINT**: We will use a Squants `Money` instance for price (they should already be in `Flight`)
- **NOTE**: You must make your method tail recurisve.
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise.
