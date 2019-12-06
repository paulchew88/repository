package com.pc1crt.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	public List<Room> findByRoomType(String text);
	

}
