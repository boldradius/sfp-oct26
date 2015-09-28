package training.scala.air_scala.exercises.four

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.TestData

class FlightItineraryPlannerSpec extends FreeSpec with MustMatchers {
  import training.scala.air_scala.domain._
  import TestData._
  import com.github.nscala_time.time.Imports._
  import training.scala.air_scala.flights.scheduling._
  import training.scala.air_scala.flights.Flight

  "proposing itineraries" - {

    "tests for desired departure time" in pending

    "when origin and destination airports are the same" - {
      val airport = SFO

      "an exception is thrown" in {
        def planner = new FlightPlanner(
          availableFlights = Set[Flight](
            SFOToEWRFlight,
            EWRToLHRFlight,
            LHRToEWRFlight,
            EWRToSFOFlight
          ))

        an[IllegalArgumentException] must be thrownBy {
          planner.proposeItineraries(arbDate)(airport, airport)()()
        }
      }
    }

    "when no flights to destination exist" - {
      val destination = SFO
      val planner = new FlightPlanner(
        availableFlights = Set(
          SFOToEWRFlight,
          LHRToEWRFlight
        ))

      "no itineraries are returned" in {
        planner.proposeItineraries(arbDate)(LHR, destination)()() mustBe Set.empty[Itinerary]
      }
    }

    "when no flights from origin exist" - {
      val origin = EWR
      val planner = new FlightPlanner(
        availableFlights = Set(
          SFOToEWRFlight,
          LHRToEWRFlight
        ))

      "no itineraries are returned" in {
        planner.proposeItineraries(arbDate)(origin, SFO)()() mustBe Set.empty[Itinerary]
      }
    }

    "when direct flights from origin to destination exist" - {
      val origin = SFO
      val destination = EWR

      val planner = new FlightPlanner(
        availableFlights = Set(
          SFOToEWRFlight,
          SFOToEWRFlightEarlierFlight,
          LHRToEWRFlight
        ))

      "returns direct flights with departure time later than departAfter time" in {
        val SFOToEWRItinerary = ProposedItinerary(Seq(SFOToEWRFlight))

        val departAfter = SFOToEWRFlightEarlierFlight.schedule.origin.time + 1.minute

        planner.proposeItineraries(departAfter)(origin,destination)()() mustBe
        Set[ProposedItinerary](SFOToEWRItinerary)
      }
    }
  }

  "filtering itineraries" ignore {
    // You might implement things differently under the hood, so these
    // tests are ignored. However, these tests can serve as a guide to
    // implemenation, if help is needed.

    "filtering itineraries by max connections" - {
      "when no itineraries given, returns no itineraries" in {
        val itineraries = Set.empty[Itinerary]
        FlightPlanner.filterByMaxConnections(arbMaxConnections)(itineraries) mustBe Set.empty[Itinerary]
      }

      "itineraries with too many connections are filtered out" in {
        val maxConnections = 2
        val hasNoConnections = NewarkToSFItinerary
        val hasTwoConnections = NewarkToLondonToSFItinerary
        val hasMoreThanTwoConnections = SFToLondonRoundTripItinerary

        val itineraries = Set[Itinerary](
          hasNoConnections,
          hasTwoConnections,
          hasMoreThanTwoConnections
        )

        val expected = Set(
          hasNoConnections,
          hasTwoConnections
        )

        FlightPlanner.filterByMaxConnections(maxConnections)(itineraries) mustBe expected
      }
    }
    "filtering itineraries by min connection time" - {
      "when no itineraries given, returns no itineraries" in {
        val itineraries = Set.empty[Itinerary]
        FlightPlanner.filterByMinConnectionTime(arbMinConnectionTime)(itineraries) mustBe Set.empty[Itinerary]
      }

      "itineraries with a connection duration that is too short are filtered out" in {
        val minConnectionTime = (EWRFromSFOArrival to EWRToLHRDeparture).toDuration + 1.minute
        val connectionTimeTooShort = SFToLondonItinerary
        val containsConnectionTimeTooShort = SFToLondonItinerary
        val connectionTimesOk = NewarkToLondonToSFItinerary

        val itineraries = Set[Itinerary](
          connectionTimeTooShort,
          connectionTimesOk,
          containsConnectionTimeTooShort
        )

        FlightPlanner.filterByMinConnectionTime(minConnectionTime)(itineraries) mustBe Set(connectionTimesOk)
      }

    }

    "getting layover times of itinerary" - {
      "when itinerary is empty, layover times is empty" in {
        Itinerary.layoverTimes(emptyItinerary) mustBe Seq.empty[Duration]
      }
      "when itinerary is single flight, layover times is empty" in {
        Itinerary.layoverTimes(singletonItinerary) mustBe Seq.empty[Duration]
      }
      "when itinerary contains two flights" - {
        "a single layover is returned" in {
          val itinerary = ProposedItinerary(flights = Seq(SFOToEWRFlight, EWRToLHRFlight))
          val expectedLayovers = Seq((EWRFromSFOArrival to EWRToLHRDeparture).toDuration)
          Itinerary.layoverTimes(itinerary) mustBe expectedLayovers
        }
      }
      "when itinerary contains more than two flights" - {
        "a sequence of layovers is returned" in {
          val itinerary = SFToLondonRoundTripItinerary
          val expectedLayovers = Seq(
            (EWRFromSFOArrival to EWRToLHRDeparture).toDuration,
            (LHRFromEWRArrival to LHRToEWRDeparture).toDuration,
            (EWRFromLHRArrival to EWRToSFODeparture).toDuration
          )
          Itinerary.layoverTimes(itinerary) mustBe expectedLayovers
        }
      }
    }
  }

  def arbMinConnectionTime = 90.minutes
  def arbMaxConnections = 2
  def emptyPlanner = new FlightPlanner(availableFlights = Set.empty[Flight])
  def emptyItinerary = ProposedItinerary(flights = Seq.empty[Flight])
  def singletonItinerary = ProposedItinerary(flights = Seq(LHRToEWRFlight))
  def arbDate = DateTime.now
}
