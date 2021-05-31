package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TripRequest {
	@ApiModelProperty(hidden = true)
	private String id;
	
	@NotNull
	private Integer fare;

	@NotBlank
	private String journey_time;

	@NotBlank
	private String agency;

	@NotBlank
	private String bus;

	@NotBlank
	private String sourceStop;

	@NotBlank
	private String destStop;

	public TripRequest() {
	}

	public TripRequest(String id, @NotNull Integer fare, @NotBlank String journey_time, @NotBlank String agency,
			@NotBlank String bus, @NotBlank String sourceStop, @NotBlank String destStop) {
		this.id = id;
		this.fare = fare;
		this.journey_time = journey_time;
		this.agency = agency;
		this.bus = bus;
		this.sourceStop = sourceStop;
		this.destStop = destStop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
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
