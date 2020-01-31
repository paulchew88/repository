package com.pc1crt.application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pc1crt.application.api.CatService;
import com.pc1crt.application.model.*;
import com.pc1crt.application.repositories.*;

@Controller
public class WelcomeController {
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/")
	public String main(Model model) {

		return "start"; // view
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";

	}

	@GetMapping("/availability")
	public String availability(Model model) {
		
		List<Room> rooms = roomRepository.findAll();
		
		for (Room room : rooms) {
			if (room.findBooking(LocalDate.now(), LocalDate.now()) == null) {
				room.setAvailable(true);
			} else {
				room.setAvailable(false);
			}
		}

		model.addAttribute("rooms", rooms);

		return "Lists/availability";

	}
	@GetMapping("/availabilitys")
	public String availabilitys(Model model) {
		System.out.println(LocalDate.now());
		Set<Booking> bookings = bookingRepository.findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate.now(),
				LocalDate.now());
		List<Room> rooms = roomRepository.findAll();
		for (Room room : rooms) {
			for (Booking booking : bookings) {
				if (room.getRoomNo() == booking.getRoom().getRoomNo()) {
					room.setAvailable(false);
				} else {
					room.setAvailable(true);
				}
			}
		}
		
		model.addAttribute("rooms", rooms);
		return "Lists/availability";

	}

	//tried to show 2 separate Lists but could not persist a list within a list object
	
	/*@GetMapping("/room/availability")
	public String roomAvailability(Model model) {
		Set<Booking> bookings = bookingRepository.findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate.now(),
				LocalDate.now());
		List<Room> rooms = roomRepository.findAll();
		List<Room> tempRoom = new ArrayList<Room>();
		System.out.println(rooms);
		for (Booking b : bookings) {
			if (!bookings.isEmpty()) {
				for (Room r : rooms) {
					System.out.println(r);
					if(b.getRoom().getRoomNo() != r.getRoomNo()) {
						Room room = r;
						tempRoom.add(room);
					}
				}
			}

		}
	model.addAttribute("bookings", bookings);
	model.addAttribute("rooms", tempRoom);

		return "/Lists/roomAvailability";
	}*/

	@GetMapping("/room/cats/{id}")
	public String showCats(@PathVariable Integer id, Model model) {
		Room room = roomRepository.findByRoomNo(id);
		Booking booking = room.findBooking(LocalDate.now(), LocalDate.now());
		model.addAttribute("cats", booking.getCats());
		return "/Lists/bookingViewCats";

	}
}