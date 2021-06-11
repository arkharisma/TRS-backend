package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddTripRequest {
	
	@NotNull
	private Integer fare;

	@NotBlank
	private String journey_time;

	@NotBlank
	private String bus;

	@NotBlank
	private String sourceStop;

	@NotBlank
	private String destStop;

	public AddTripRequest() {
	}

	public AddTripRequest(@NotNull Integer fare, @NotBlank String journey_time, @NotBlank String bus,
			@NotBlank String sourceStop, @NotBlank String destStop) {
		this.fare = fare;
		this.journey_time = journey_time;
		this.bus = bus;
		this.sourceStop = sourceStop;
		this.destStop = destStop;
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

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getSourceStop() {
		return sourceStop;
	}

	public void setSourceStop(String sourceStop) {
		this.sourceStop = sourceStop;
	}

	public String getDestStop() {
		return destStop;
	}

	public void setDestStop(String destStop) {
		this.destStop = destStop;
	}
}
