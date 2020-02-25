package com.pc1crt.application.model;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Embeddable
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
	private Boolean cctv;

	@ManyToMany
	private Set<Cat> cats;
	@ManyToOne
	@JoinColumn(name = "room")
	private Room room;
	private Integer length;
	private Double costForCats = 0.00;
	private Double costsPerNight;
	private Double totalCost = 0.00;

	public Booking() {
	}

	public Booking(Integer bookingNo, LocalDate checkInDate, LocalDate checkOutDate, Owner owner, Set<Cat> cats,
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

	public Set<Cat> getCats() {
		return cats;
	}

	public void setCats(Set<Cat> cats) {
		this.cats = cats;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the cctv
	 */
	public Boolean getCctv() {
		return cctv;
	}

	/**
	 * @param cctv the cctv to set
	 */
	public void setCctv(Boolean cctv) {
		this.cctv = cctv;
	}

	/**
	 * @return the costForCats
	 */
	public Double getCostForCats() {
		return costForCats;
	}

	/**
	 * @param costForCats the costForCats to set
	 */
	public void setCostForCats(Double costForCats) {
		this.costForCats = costForCats;
	}

	/**
	 * @return the costsPerNight
	 */
	public Double getCostsPerNight() {
		return costsPerNight;
	}

	/**
	 * @param costsPerNight the costsPerNight to set
	 */
	public void setCostsPerNight(Double costsPerNight) {
		this.costsPerNight = costsPerNight;
	}

	/**
	 * @return the totalCost
	 */
	public Double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public void addCat(Cat cat) {
		this.cats.add(cat);
		cat.getBookings().add(this);
	}

	public void removeCat(Cat cat) {
		this.cats.remove(cat);
		cat.getBookings().remove(this);
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
		builder.append(", cctv=");
		builder.append(cctv);
		builder.append("]");
		return builder.toString();
	}

	public Double totalCost() {
		if (cats.size() == 1) {
			costsPerNight = 15.00;
		} else if (cats.size() == 2) {
			costsPerNight = 20.00;
		} else if (cats.size() == 3) {
			costsPerNight = 25.00;
		} else if (cats.size() == 4) {
			costsPerNight = 30.00;
		}
		if (!cats.isEmpty()) {
			for (Cat cat : cats) {
				if (cat.getFood() == MealPlan.Luxury) {
					costForCats += 3.00;

				} else if (cat.getFood() == MealPlan.Standard) {
					costForCats += 1.50;
				}
			}
		}
		

	
	
		length =  checkOutDate.getDayOfYear() -checkInDate.getDayOfYear();
		
		setTotalCost((costsPerNight * length) + (costForCats * length));
		
		if (cctv != null) {
			if (cctv) {
				setTotalCost(getTotalCost()+ 1.00);
			}
		}
		return this.totalCost;
	}

}
