003-folding

# Exercise 3 > Calculate Travel Time with `fold`

For this exercise, we are going to demonstrate the use of `folding` as alternate methods for recursive operations, without using Tail Recursion or `sliding`.

- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `003-folding`
- Add a new method – `totalFlightTime` – to the `Itinerary` companion object, which will determine the total in-flight time of an Itinerary
  + It should accept a parameter `itinerary` of type `Itinerary`
  + **HINT**: We will use a Joda `Period` instance for the time; there are pre-written helper methods on `Flight` and `Schedule` for determining the duration, and subtracting two instances from one another to get the time between.
- **NOTE**: You must implement this method in terms of one of the flavors of `fold`.
- **NOTE**: We only care about *total time in the air* – we do *not* include the layover times.
- **NOTE**: The way Joda `Period` works, it's possible to get a answer like "12 hours 75 minutes". Calling `normalizedStandard()` will fix that, but our tests do just in case.
- **BONUS**: If you want to really challenge yourself, account for connection times, i.e. if you land at 9PM, and your connection leaves at 10:30PM, that hour an a half must be counted.
    - An (optional) test for this can be reached by running `koan next` one more time.


- Think about how this differs, with pros & cons, from our first & second exercises (the use of Tail Recursion in Exercise #1, and `sliding`/`forall`/`PartialFunction` in Exercise #2)
- Use the `test` command to verify the provided tests pass.
- Use `koan next` to move to the next exercise; if you are skipping the "bonus" exercise, you'll want to run `koan next` twice.
