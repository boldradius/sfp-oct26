003-folding

# Exercise 3 > Calculate Travel Time with `fold`

For this exercise, we are going to demonstrate the use of `folding`` as alternate methods for recursive operations, without using Tail Recursion or `sliding`.

- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `003-folding`
- Add a new method, `totalTravelTime`, to the `Itinerary` companion object, which will determine the total travel time  of an Itinerary
  + It should accept a parameter `itinerary` of type `Itinerary`
  + **HINT**: We will use a Joda `Duration` instance for the time (look at the Joda / NScalaTime docs for a hint)
- **NOTE**: You must implement this method in terms of one of the flavors of `fold`.
- **NOTE**: You need to account for connection times, i.e. if one segment lands at 9PM, and the connecting flight leaves at 10:30PM, you must account for that hour and a half layover.

- Think about how this differs, with pros & cons, from our first & second exercises (the use of Tail Recursion, and `sliding`)
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise.
