package com.pc1crt.application.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
private Integer roomNo;
private RoomType roomType;
@Embedded
@ElementCollection
@OneToMany
private List<Booking> booking;



public Room() {}


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
public List<Booking> getBooking() {
	return booking;
}
public void setBooking(List<Booking> booking) {
	this.booking = booking;
}




}
