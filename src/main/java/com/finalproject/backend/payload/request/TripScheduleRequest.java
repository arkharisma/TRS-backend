package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TripScheduleRequest {

	@ApiModelProperty(hidden = true)
	private String id;

	@NotNull
	private Integer available_seats;

	@NotBlank
	private String trip_date;

	@NotBlank
	private String trip_detail;

	public TripScheduleRequest() {
	}

	public TripScheduleRequest(String id, @NotNull Integer available_seats, @NotBlank String trip_date,
			@NotBlank String trip_detail) {
		this.id = id;
		this.available_seats = available_seats;
		this.trip_date = trip_date;
		this.trip_detail = trip_detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(Integer available_seats) {
		this.available_seats = available_seats;
	}

	public String getTrip_date() {
		return trip_date;
	}

	public void setTrip_date(String trip_date) {
		this.trip_date = trip_date;
	}

	public String getTrip_detail() {
		return trip_detail;
	}

	public void setTrip_detail(String trip_detail) {
		this.trip_detail = trip_detail;
	}
}
