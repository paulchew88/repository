package com.pc1crt.application.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.Room;
import com.pc1crt.application.model.RoomType;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	public List<Room> findByRoomType(RoomType text);
	public Room findByRoomNo(Integer id);
	public Booking findByBookingsCheckInDateBeforeAndBookingsCheckOutDateAfter(LocalDate date1, LocalDate date2);

}
