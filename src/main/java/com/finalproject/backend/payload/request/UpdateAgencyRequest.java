package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class UpdateAgencyRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String details;

	public UpdateAgencyRequest() {
	}

	public UpdateAgencyRequest(@NotBlank String name, @NotBlank String details) {
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
