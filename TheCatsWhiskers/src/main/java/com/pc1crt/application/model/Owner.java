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
@Table(name = "owner")
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_number")
	private Integer customerNumber;
	private String firstName;
	private String surName;
	@NotNull
	@Column(name = "email", unique = true)
	private String email;
	@Embedded
	private Address address;
	private String contactNumber;
	@Embedded
	@ElementCollection
	private List<Cat> cats;

	public Owner() {
	}

	public List<Cat> getCats() {
		return cats;
	}

	public void setCats(List<Cat> cats) {
		this.cats = cats;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Owner [customerNumber=");
		builder.append(customerNumber);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", surName=");
		builder.append(surName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", cats=");
		builder.append(cats);
		builder.append("]");
		return builder.toString();
	}

}
