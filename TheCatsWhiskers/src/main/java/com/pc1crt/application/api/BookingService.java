package com.pc1crt.application.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc1crt.application.repositories.BookingRepository;
import com.pc1crt.application.repositories.CatRepository;
import com.pc1crt.application.repositories.OwnerRepository;
import com.pc1crt.application.repositories.RoomRepository;
import com.pc1crt.application.model.*;

@RestController
public class BookingController {
	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/api/booking")
	public List<Booking> index() {
		return bookingRepository.findAll();
	}

	@GetMapping("/api/booking/{id}")
	public Object show(@PathVariable BookingKey id) {
		return bookingRepository.findById(id);
	}

	@GetMapping("/api/booking/search")
	public List<Booking> search(@RequestBody Map<String, String> body) {
		LocalDate checkIn = LocalDate.parse(body.get("checkIn"));
		LocalDate checkOut = LocalDate.parse(body.get("checkOut"));
		return null;
	}

	@PostMapping("/api/booking")
	public Booking create(Booking booking, UriComponentsBuilder ucBuilder) {
		if (bookingRepository.existsById(booking.getId()))
			return null;
		else
			return bookingRepository.save(booking);
	}
	

	@PutMapping("/api/booking/{id}")
	public Booking update(@PathVariable Integer id, @RequestBody Booking booking, UriComponentsBuilder ucBuilder) {

	
			return bookingRepository.save(booking);
	}

	/*@DeleteMapping("/api/booking/{id}")
	public Boolean delete(@PathVariable BookingKey id) {
		if (bookingRepository.existsById(id)) {
			bookingRepository.deleteById(id);
			return true;
		} else
			return false;

	}*/

}
