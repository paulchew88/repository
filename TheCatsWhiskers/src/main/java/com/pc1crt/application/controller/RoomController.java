package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.model.Room;
import com.pc1crt.application.repositories.RoomRepository;

@Controller
public class RoomController {
	@Autowired
	RoomRepository roomRepository;

	@GetMapping("/rooms")
	public String main(Model model) {
		model.addAttribute("rooms", roomRepository.findAll());

		return "roomList";
	}
	@RequestMapping(value = "/newRoom", method = RequestMethod.GET)
	public String RoomForm(Model model) {
		model.addAttribute("room", new Room());

		return "newRoom";
	}

	@RequestMapping(value = "/newRoom", method = RequestMethod.POST)
	public String submitRoom(@Valid @ModelAttribute("room") Room room, BindingResult result, ModelMap model) {

		roomRepository.save(room);

		return "redirect:/rooms";

	}
	@RequestMapping("/room/update/{id}")
	public String updateRoom(@PathVariable Integer id, Model model) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = optionalRoom.get();
		model.addAttribute(room);
		return "newRoom";
	}
	@RequestMapping("/room/delete/{id}")
	public String delete(@PathVariable Integer id) {
		roomRepository.deleteById(id);
		return "redirect:/rooms";
	}
/*	@RequestMapping("/room/booking/{id}")
	public String showCats(@PathVariable Integer id, Model model) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = optionalRoom.get();
		model.addAttribute("cats", room.getBooking());

		return "roomBookingList";
	}
	@RequestMapping(value = "/addBooking", method = RequestMethod.POST)
	public String addCat(@Valid @ModelAttribute("booking") Booking booking, @ModelAttribute("room") Room room,
			BindingResult result, ModelMap model) {
		List<Booking> bookings = new ArrayList<Booking>();
		if (room.getBooking() != null) {
			bookings = room.getBooking();
		}
		if (!roomRepository.existsById(booking.getBookingNo())) {
			roomRepository.save(room);
		}

	
		bookings.add(booking);
		room.setBooking(bookings);

		roomRepository.save(room);

		return "redirect:/rooms";
	}*/
}
