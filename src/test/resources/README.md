004-flight-itineraries

# Exercise 4 > Plan Flight Itineraries

For this exercise, we're going to put together what we've learned so far about recursion in Scala to build something more complex. Our goal is to provide a new method which can propose one or more `Itinerary`s based on a `Seq[Flight]`. This will take into account a ‘minimum connection time’, i.e. will I have time to catch my connecting flight?
- Make sure you've run `koan next` to fetch the tests & test data for this exercise – your sbt prompt should end with `004-flight-itineraries`
- First, you'll want to create a new `FlightPlanner` class under `training.scala.air_scala.scheduling`, with a class parameter `availableFlights` of `Seq[Flight]`.
- Define a new `proposeItineraries` in `FlightPlanner`, taking 4 arguments:
    + `from` and `to`: Instances of `AirportCode`, which must not be equal
    + In a second parameter list, `minConnectionTime`: A JodaTime `Duration`, defaulting to 90 minutes.
    + In a third parameter list, `maxConnections`: The maximum number of stops between `from` and `to`, defaulting to `2`
- **NOTE**: Use `zip` instead of `sliding`.
- Verify the provided tests pass.





