005-unapply

# Exercise 5 > Custom Extractors
#### Using `unapply`

You may have noted that `Flight` is not currently a case class. We've done that somewhat deliberately, to leave a place for us to implement our own extractors. In this exercise, we'll add a companion object to `Flight`, implementing both an `apply` factory and an `unapply` extractor.
- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `004-flight-itineraries`
- Create a Companion Object for `Flight`, with defined implementations of `apply` and `unapply`.
    - Make sure that `apply` takes the constructor arguments of `Flight`, and returns a new instance of `Flight`.
    - Write an extractor `unapply` which, given an instance of `Flight`, can be used in pattern matching.
- In the Test Console `sbt > test:console`, play with some pattern matches against instances of `Flight`.
- Verify the provided tests pass.

^ TODO - show a reason to send None back?





