package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UserRequest {

	@ApiModelProperty(hidden = true)
	private String id;
	
	@NotNull
	private String first_name;

	@NotBlank
	private String last_name;

	@NotBlank
	private String mobile_number;

	public UserRequest() {
	}

	public UserRequest(String id, @NotNull String first_name, @NotBlank String last_name,
			@NotBlank String mobile_number) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_number = mobile_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
}
