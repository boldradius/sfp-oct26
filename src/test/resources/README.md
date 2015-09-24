002-sliding-pfs

# Exercise 2 > `sliding`, and `PartialFunction`

For this exercise, we are going to demonstrate the use of `sliding`, and `PartialFunction` as alternate methods for recursive operations, without using Tail Recursion.

- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `002-sliding-pfs`
- Add a new method, `totalMilesEarned`, to the `Itinerary` companion object, which will determine the total Frequent Flyers earned for a given `Itinerary` based on the mileage of each `Flight`
  + It should accept a parameter `itinerary` of type `Itinerary`
  + **HINT**: We will use a Squants `Length` instance for the mileage (they should already be in `Flight`)
- **NOTE**: You must implement this method in terms of `sliding`, and `PartialFunction`
- Think about how this differs, with pros & cons, from our first exercise (the use of Tail Recursion)
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise.
