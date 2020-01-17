package com.pc1crt.application.model;

import java.sql.Date;
import java.time.Duration;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkInDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOutDate;
	@Embedded
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_number")
	private Owner owner;
	@Embedded
	@ElementCollection
	@OneToMany
	private List<Cat> cats;
	@Embedded
	@ManyToOne
	@JoinColumn(name = "room")
	private Room room;

	public Booking() {
	}

	public Booking(Integer bookingNo, LocalDate checkInDate, LocalDate checkOutDate, Owner owner, List<Cat> cats,
			Room room) {
		this.bookingNo = bookingNo;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.owner = owner;
		this.cats = cats;
		this.room = room;
	}

	public Integer getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(Integer bookingNo) {
		this.bookingNo = bookingNo;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
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

	public List<Cat> getCats() {
		return cats;
	}

	public void setCats(List<Cat> cats) {
		this.cats = cats;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Booking [bookingNo=");
		builder.append(bookingNo);
		builder.append(", checkInDate=");
		builder.append(checkInDate);
		builder.append(", checkOutDate=");
		builder.append(checkOutDate);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", cats=");
		builder.append(cats);
		builder.append(", room=");
		builder.append(room);
		builder.append("]");
		return builder.toString();
	}

}
