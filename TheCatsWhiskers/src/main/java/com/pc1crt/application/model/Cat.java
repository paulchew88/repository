package com.pc1crt.application.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.boot.model.relational.Namespace.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Embeddable
@Table(name = "cat")
public class Cat {
	private String name;

	@Id
	/*
	 * @GeneratedValue
	 * 
	 * private Integer catId;
	 */

	@NotNull
	@Column(name = "chip_no")
	private Integer chipNo;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "cats")
	private Set<Booking> bookings;

	private MealPlan food;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate vaccinatedDate;
	private String temperment;
	private String litterType;
	private String otherInformation;
	private Boolean checkedIn;


	public Cat() {
	}

	public Cat(String name, @NotNull Integer chipNo, Set<Booking> bookings, MealPlan food, LocalDate vaccinatedDate,
			String temperment, String litterType, String otherInformation) {
		this.name = name;
		this.chipNo = chipNo;
		this.bookings = bookings;
		this.food = food;
		this.vaccinatedDate = vaccinatedDate;
		this.temperment = temperment;
		this.litterType = litterType;
		this.otherInformation = otherInformation;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChipNo() {
		return chipNo;
	}

	public void setChipNo(Integer chipNo) {
		this.chipNo = chipNo;
	}

	public MealPlan getFood() {
		return food;
	}

	public void setFood(MealPlan food) {
		this.food = food;
	}

	public LocalDate getVaccinatedDate() {
		return vaccinatedDate;
	}

	public void setVaccinatedDate(LocalDate vaccinatedDate) {
		this.vaccinatedDate = vaccinatedDate;
	}

	public String getTemperment() {
		return temperment;
	}

	public void setTemperment(String temperment) {
		this.temperment = temperment;
	}

	public String getLitterType() {
		return litterType;
	}

	public void setLitterType(String litterType) {
		this.litterType = litterType;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public void addBooking(Booking booking) {
		this.bookings.add(booking);
		booking.getCats().add(this);
	}

	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
		booking.getCats().remove(this);
	}





}