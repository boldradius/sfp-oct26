002-forall-sliding-pfs

# Exercise 2 > `sliding`, and `PartialFunction`

For this exercise, we are going to demonstrate the use of `forall`, `sliding`, and `PartialFunction` as alternate methods for recursive operations, without using Tail Recursion.

- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `002-forall-sliding-pfs`
- Add a new `isScheduleIncreasing` method to the `Itinerary` companion object, which checks whether or not a given `Itinerary` is increasing in time (i.e. you won't miss a flight segment)
  + It should accept a parameter `itinerary` of type `Itinerary`
- **NOTE**: You must implement this method in terms of `forall`, `sliding`, and `PartialFunction`
- **HINT**: For our purposes, an empty or single item `Itinerary` are considered to be ‘increasing’
- Think about how this differs, with pros & cons, from our first exercise (the use of Tail Recursion)
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise.
