package com.pc1crt.application.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Embeddable
@Table(name="owner")
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_number")
	private Integer customerNumber;
	private String firstName;
	private String surName;
	@NotNull
	@Column(name = "email", unique = true)
	private String email;
	private String StreetName;
	private String houseNumber;
	private String postCode;
	private String contactNumber;
	@Embedded
	@ElementCollection
	@OneToMany
	private List<Cat> cats;
	
	public Owner() {}

	

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
	public List<Cat> getCats() {
		return cats;
	}

	public void setCats(List<Cat> cats) {
		this.cats = cats;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}



	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}



	@Override
	public String toString() {
		return "Owner [firstName=" + firstName + ", surName=" + surName + ", email=" + email + ", StreetName=" + StreetName + ", houseNumber=" + houseNumber + ", postCode="
				+ postCode + ", contactNumber=" + contactNumber + "]";
	}


	
	

}
