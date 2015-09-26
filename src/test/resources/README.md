006-unapplySeq

# Exercise 6 > Custom Sequence Extractors
## Using `unapplySeq`

**TODO**: Talk about VarArgs briefly?

Given that an `Itinerary` is made up of a `Seq[Flight]`, this might be an ideal place for `unapplySeq`.
- Define an `unapplySeq` method in `Itinerary`s companion object, which allows us to extract the Sequence of `Flight`s from an `Itinerary`.
- In the Test Console – `sbt > test:console` – play with some pattern matches against the `Itinerary.unapplySeq`.
- Verify that the provided tests pass.


