package com.pc1crt.application.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String StreetName;
	private String houseNumber;
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
