package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class BusRequest {

	@ApiModelProperty(hidden = true)
	private String id;
	
	@NotNull
	private Integer capacity;

	@NotBlank
	private String code;

	@NotBlank
	private String make;

	@ApiModelProperty(hidden = true)
	// @NotBlank
	private String agency;

	public BusRequest() {
	}

	public BusRequest(String id, @NotNull Integer capacity, @NotBlank String code, @NotBlank String make,
			@NotBlank String agency) {
		this.id = id;
		this.capacity = capacity;
		this.code = code;
		this.make = make;
		this.agency = agency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}
}
