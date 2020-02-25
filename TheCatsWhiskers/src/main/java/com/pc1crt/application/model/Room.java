package com.pc1crt.application.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javassist.expr.NewArray;

@Entity
@Embeddable
public class Room {
	@Id
	@Column(name = "room_no")
	private Integer roomNo;
	private RoomType roomType;
	private boolean available;
	@OneToMany(mappedBy = "room")
	private List<Booking> bookings = new ArrayList<Booking>();

		
	

	public Room() {
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the bookings
	 */
	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking findBooking(LocalDate date1, LocalDate date2) {
		Booking booking = null;
		if (bookings.size() != 0) {
			for (Booking bList : bookings) {
				System.out.println(bList);
				if (
				// between start and finish
				(date1.isAfter(bList.getCheckInDate()) && date2.isBefore(bList.getCheckOutDate()))
						// before start and before finish
						|| (date1.isBefore(bList.getCheckInDate()) && !date2.isBefore(bList.getCheckInDate())
								&& date2.isBefore(bList.getCheckOutDate()))
						// after start date and finish
						|| (date1.isAfter(bList.getCheckInDate()) && !date1.isAfter(bList.getCheckOutDate())
								&& date2.isAfter(bList.getCheckOutDate()))
						// before start and after finish
						|| (date1.isBefore(bList.getCheckInDate()) && date2.isAfter(bList.getCheckOutDate()))
						// start is equal to check in
						|| date1.isEqual(bList.getCheckInDate())

				) {
					booking = bList;
				}

			}
		}
		return booking;
	}

	public List<Booking> findBookings(LocalDate date1, LocalDate date2) {
		List<Booking> tempBooking = new ArrayList<Booking>();
		for (Booking bList : bookings) {
			System.out.println(bList);
			if (
			// between start and finish
			(date1.isAfter(bList.getCheckInDate()) && date2.isBefore(bList.getCheckOutDate()))
					// before start and before finish
					|| (date1.isBefore(bList.getCheckInDate()) && !date2.isBefore(bList.getCheckInDate())
							&& date2.isBefore(bList.getCheckOutDate()))
					// after start date and finish
					|| (date1.isAfter(bList.getCheckInDate()) && !date1.isAfter(bList.getCheckOutDate())
							&& date2.isAfter(bList.getCheckOutDate()))
					// before start and after finish
					|| (date1.isBefore(bList.getCheckInDate()) && date2.isAfter(bList.getCheckOutDate()))
					// start is equal to check in
					|| date1.isEqual(bList.getCheckInDate())

			) {
				tempBooking.add(bList);
			}

		}
		return tempBooking;
	}

}
