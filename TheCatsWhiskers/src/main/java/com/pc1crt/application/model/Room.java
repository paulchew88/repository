package com.pc1crt.application.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
private Integer roomNo;
private RoomType roomType;



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
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + roomNo;
	result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Room other = (Room) obj;
	if (roomNo != other.roomNo)
		return false;
	if (roomType != other.roomType)
		return false;
	return true;
}

}
