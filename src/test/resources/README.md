005-unapply


# Exercise 5 > Custom Extractors
#### Using `unapply`


While `FlightNumber` is a case class, it still requires us to construct it with independent parameters - `airlineCode: String` and `flightNumber: Int`. Since it's a case class, we've already been provided an `unapply` extractor. It would be nice, however, if we could also extract from a bare `String`.

- Create a Companion Object for `FlightNumber`, and define a new extractor method which, given a `String`, attempts to extract an `airlineCode` and `flightNumber`.

  - **HINT**: You can use the following RegEx to extract a flight number cleanly:

    ```
    val FlightNumRE = "([A-Z]{1,4})(\\d{1,4})".r
    ```

- In the Test Console – `sbt > test:console` – play with some pattern matches against instances of `FlightNumber`.
- Verify the provided tests pass.


