003a-folding-layovers

# Exercise 3a [Bonus] > Calculate Full Travel Time, with Layover, via `folding`

For this bonus exercise, we're going to further demonstrate the use of `folding` as an alternate method for recursive operations, without using Tail Recursion or `sliding`.

- Make sure you've run `koan next` to fetch the tests & test data for this **bonus** exercise – your sbt prompt should end with `003a-folding-layovers`
- Add a new method – `totalLayoverTime` – to the `Itinerary` companion object, which will determine the total time, including layovers, of an Itinerary
  + It should accept a parameter `itinerary` of type `Itinerary`
- **NOTE**: You must implement this method in terms of one of the flavors of `fold`.
- **NOTE**: The way Joda `Period` works, it's possible to get a answer like "12 hours 75 minutes". Calling `normalizedStandard()` will fix that, but our tests do just in case.

- Think about how this (and the master exercise) differs, with pros & cons, from our first & second exercises (the use of Tail Recursion in Exercise #1, and `sliding`/`forall`/`PartialFunction` in Exercise #2)
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise.
