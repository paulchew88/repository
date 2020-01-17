package com.pc1crt.application.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {
	@NotBlank(message = "Street Name is mandatory")
	private String StreetName;
	@NotBlank(message = "House Number / Name is mandatory")
	private String houseNumber;
	@NotBlank(message = "PostCode is mandatory")
	private String postCode;

	public Address() {
	}

	public String getStreetName() {
		return StreetName;
	}

	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append(houseNumber);
		builder.append(", ");
		builder.append(StreetName);
		builder.append(", ");
		builder.append(postCode);
		builder.append("");
		return builder.toString();
	}

}
