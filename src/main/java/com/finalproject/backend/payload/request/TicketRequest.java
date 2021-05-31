package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TicketRequest {

	@ApiModelProperty(hidden = true)
	private String id;

	@NotNull
	private Boolean cancellable;

	@NotBlank
	private String journey_date;

	@NotNull
	private Integer seat_number;

	@NotBlank
	private String passenger;

	@NotBlank
	private String trip_schedule;

	public TicketRequest() {
	}

	public TicketRequest(String id, @NotNull Boolean cancellable, @NotBlank String journey_date,
			@NotNull Integer seat_number, @NotBlank String passenger, @NotBlank String trip_schedule) {
		this.id = id;
		this.cancellable = cancellable;
		this.journey_date = journey_date;
		this.seat_number = seat_number;
		this.passenger = passenger;
		this.trip_schedule = trip_schedule;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getCancellable() {
		return cancellable;
	}

	public void setCancellable(Boolean cancellable) {
		this.cancellable = cancellable;
	}

	public String getJourney_date() {
		return journey_date;
	}

	public void setJourney_date(String journey_date) {
		this.journey_date = journey_date;
	}

	public Integer getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(Integer seat_number) {
		this.seat_number = seat_number;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getTrip_schedule() {
		return trip_schedule;
	}

	public void setTrip_schedule(String trip_schedule) {
		this.trip_schedule = trip_schedule;
	}
}
