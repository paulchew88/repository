package com.pc1crt.application.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc1crt.application.model.Room;
import com.pc1crt.application.model.RoomType;
import com.pc1crt.application.repositories.RoomRepository;

@RestController
public class RoomController {
	@Autowired
	RoomRepository roomRepository;
	
	@GetMapping("/api/room")
	public List<Room> index(){
		return roomRepository.findAll();
	}
	
	@GetMapping("/api/room/{id}")
	public Object show(@PathVariable Integer id){
		return roomRepository.findById(id);
	}
	@GetMapping("/api/room/search")
	public List<Room> search(@RequestBody Map<String,String> body){
		String searchTerm = body.get("text");
		return roomRepository.findByRoomType(searchTerm);
	}
	
	@PostMapping("/api/room")
	public Room create(@RequestBody Map<String,String> body) {
		Room room = new Room();
		room.setRoomNo(Integer.valueOf(body.get("roomNo")));
		room.setRoomType(RoomType.valueOf(body.get("roomType")));
		
		return roomRepository.save(room);		
	}

	@PutMapping("/api/room/{id}")
	public Room update(@PathVariable Integer id,@RequestBody Map<String,String> body) {
		Optional<Room> optinalRoom = roomRepository.findById(id);
		Room room = optinalRoom.get();
		room.setRoomNo(Integer.valueOf(body.get("roomNo")));
		room.setRoomType(RoomType.valueOf(body.get("roomType")));
		
		return roomRepository.save(room);
	}
}
