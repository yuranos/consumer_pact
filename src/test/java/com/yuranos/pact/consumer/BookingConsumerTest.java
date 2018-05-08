package com.yuranos.pact.consumer;

import java.util.HashMap;
import java.util.Map;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Yura Nosenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(NAME_ASCENDING)
public class BookingConsumerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private final int port = 8083;

	@Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("raml-based-producer-application", "localhost", port, this);


	@Pact(consumer="booking-consumer-pact", provider = "raml-based-producer-application")
	public RequestResponsePact bookingCreatedAndFetched(PactDslWithProvider builder) {
		return builder
				.given("State 1")
					.uponReceiving("Represents a successful scenario for creating a booking")
					.path("/bookings")
					.method("POST")
					.headers("Content-Type", "application/json")
					.body("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Jack\",\"passengerSurname\":\"Daniels\",\"age\":30}")
					.willRespondWith()
					.status(201)
				.given("State 2")
					.uponReceiving("Represents a successful scenario for getting a booking")
					.path("/bookings/1")
					.method("GET")
					.headers("Content-Type", "application/json")
					.willRespondWith()
					.status(200)
					.body("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Jack\",\"passengerSurname\":\"Daniels\",\"age\":30}")
					.headers(responseHeaders())
				.toPact();
	}

	@Test
	@PactVerification
	public void validate_1_createJackConsumerTest() {
		HttpStatus status = createBooking("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Jack\",\"passengerSurname\":\"Daniels\",\"age\":30}").getStatusCode();
		Assert.assertEquals(HttpStatus.CREATED, status);

        status = getBooking(1).getStatusCode();
        Assert.assertEquals(HttpStatus.OK, status);
	}

	private ResponseEntity<Booking> createBooking(String booking) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange("http://localhost:" + port + "/bookings", HttpMethod.POST,
				new HttpEntity<>(booking, httpHeaders),
				Booking.class);
	}

	private ResponseEntity<Booking> getBooking(int id) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange("http://localhost:" + port + "/bookings/" + id, HttpMethod.GET,
				new HttpEntity<>(httpHeaders),
				Booking.class);
	}

	private Map<String, String> responseHeaders() {
		Map<String, String> map = new HashMap<>();
		map.put("Content-Type", "application/json;charset=UTF-8");
		return map;
	}
}
