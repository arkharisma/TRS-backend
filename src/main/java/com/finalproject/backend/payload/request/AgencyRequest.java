package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AgencyRequest {

	@ApiModelProperty(hidden = true)
	private String id;
	
	@NotBlank
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	private String details;

	@NotBlank
	private String owner;

	public AgencyRequest() {
	}

	public AgencyRequest(String id, @NotBlank String code, @NotBlank String name, @NotBlank String details,
			@NotBlank String owner) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.details = details;
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
