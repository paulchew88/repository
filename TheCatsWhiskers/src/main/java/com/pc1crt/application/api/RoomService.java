package com.pc1crt.application.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.MealPlan;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.model.Room;
import com.pc1crt.application.model.RoomType;
import com.pc1crt.application.repositories.RoomRepository;

@RestController
public class RoomService {
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
		return roomRepository.findByRoomType(RoomType.valueOf(body.get(searchTerm)));
	}
	
	@PostMapping("/api/room")
	public Room add(@RequestBody Room room, UriComponentsBuilder ucBuilder) {
		if (room.getRoomNo() != null) {
			if (roomRepository.existsById(room.getRoomNo()))
				return null;
			else
				return roomRepository.save(room);
		}
		else {
			return roomRepository.save(room);
		}
	
	}

	@PutMapping("/api/room/{id}")
	public Room update(@RequestBody Room room, UriComponentsBuilder ucBuilder) {
		if (room.getRoomNo() != null) {
			if (roomRepository.existsById(room.getRoomNo()))
				return null;
			else
				return roomRepository.save(room);
		}
		else {
			return roomRepository.save(room);
		}
	
	}
	@DeleteMapping("/api/room/{id}")
	public Boolean delete(@PathVariable Integer id) {
		if (roomRepository.existsById(id)) {
			roomRepository.deleteById(id);
			return true;
		} else
			return false;

	}
	
	/*@GetMapping("/api/room/booking/{id}")
	public List<Booking> getBookings(@PathVariable Integer id){
		List<Booking> bookings = new ArrayList<Booking>();
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = optionalRoom.get();
		bookings = room.getBooking();
		
		return bookings;
		
	}
	@PostMapping("/api/room/booking/add/{id}")
	public Room addBooking(@PathVariable Integer id, @RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = optionalRoom.get();
		List<Booking> bookings = room.getBooking();
		bookings.add(booking);
		room.setBooking(bookings);

		return roomRepository.save(room);
	}*/
}

