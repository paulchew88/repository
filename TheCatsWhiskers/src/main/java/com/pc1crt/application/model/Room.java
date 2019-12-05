package com.pc1crt.application.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Room {
private int roomNo;
private RoomType roomType;



public Room() {
}
public int getRoomNo() {
	return roomNo;
}
public void setRoomNo(int roomNo) {
	this.roomNo = roomNo;
}
public RoomType getRoomType() {
	return roomType;
}
public void setRoomType(RoomType roomType) {
	this.roomType = roomType;
}

}
