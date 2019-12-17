package com.pc1crt.application.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking {
	@EmbeddedId
	private BookingKey id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOutDate;
	@Embedded
	@ManyToOne
	@JoinColumn(name = "customer_number")
	@NotNull
	private Owner owner;
	
	
	public Booking() {

	}


	public BookingKey getBookingId() {
		return id;
	}


	

	@Transient
	public BookingKey getId() {
		return id;
	}
	@Transient
	public void setId(BookingKey id) {
		this.id = id;
	}


	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	



}
