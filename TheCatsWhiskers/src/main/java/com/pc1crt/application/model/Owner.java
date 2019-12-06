package com.pc1crt.application.model;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Embeddable
@Table(name="owner")
public class Owner {
	private String firstName;
	private String surName;
	@Column(name = "email",unique = true)
	private String email;
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "Customer_no")
	private Integer customerNumber;
	private String StreetName;
	private String houseNumber;
	private String postCode;
	private String contactNumber;
	
	public Owner() {}

	
	public Integer getId() {
		return customerNumber;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Owner [firstName=" + firstName + ", surName=" + surName + ", email=" + email + ", customerNumber="
				+ customerNumber + ", StreetName=" + StreetName + ", houseNumber=" + houseNumber + ", postCode="
				+ postCode + ", contactNumber=" + contactNumber + "]";
	}
	
	

}
