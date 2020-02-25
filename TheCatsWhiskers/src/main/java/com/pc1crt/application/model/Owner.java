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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Embeddable
@Table(name = "owner")
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_number")
	
	private Integer customerNumber;
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	@NotBlank(message = "SurName is mandatory")
	private String surName;
	@NotNull
	@Column(name = "email", unique = true)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Must be a valid email")
	private String email;
	@Embedded
	private Address address = new Address();
	@NotBlank(message = "Number is mandatory")
	@Pattern(regexp="[\\d]{11}", message = "must be a valid phone number including area code")
	private String contactNumber;
	private Boolean cctv;
	
	@OneToMany()
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
	public Boolean getCctv() {
		return cctv;
	}

	public void setCctv(Boolean cctv) {
		this.cctv = cctv;
	}

	public void addCat(Cat cat) {
		this.cats.add(cat);
	}
	public void removeCat(Cat cat) {
		getCats().remove(cat);
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
