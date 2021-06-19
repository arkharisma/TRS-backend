package com.finalproject.backend.payload.response;

import javax.persistence.Id;

public class TripClientResponse {

	@Id
	private String id;
	private String trip_date;
	private String available_seats;
	private Integer fare;
	private String journey_time;
    private String agency_name;
    private String bus_code;

	public TripClientResponse() {
	}

	public TripClientResponse(String id, String trip_date, String available_seats, Integer fare, String journey_time,
			String agency_name, String bus_code) {
		this.id = id;
		this.trip_date = trip_date;
		this.available_seats = available_seats;
		this.fare = fare;
		this.journey_time = journey_time;
		this.agency_name = agency_name;
		this.bus_code = bus_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrip_date() {
		return trip_date;
	}

	public void setTrip_date(String trip_date) {
		this.trip_date = trip_date;
	}

	public String getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(String available_seats) {
		this.available_seats = available_seats;
	}

	public Integer getFare() {
		return fare;
	}

	public void setFare(Integer fare) {
		this.fare = fare;
	}

	public String getJourney_time() {
		return journey_time;
	}

	public void setJourney_time(String journey_time) {
		this.journey_time = journey_time;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getBus_code() {
		return bus_code;
	}

	public void setBus_code(String bus_code) {
		this.bus_code = bus_code;
	}
}
