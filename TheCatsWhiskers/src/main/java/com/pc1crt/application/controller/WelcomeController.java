package com.pc1crt.application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Autowired
	OwnerRepository ownerRepository;

	@Controller
	public class MyController {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		@RequestMapping("/login")
		public String handleRequest(HttpServletRequest request, Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			model.addAttribute("name", auth.getName());
			System.out.println(auth.getName());
			return "start";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		String auth = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return "login";

	}

	@GetMapping("/home")
	public String main(Model model) {

		return "start"; // view
	}

	@GetMapping("/staff/availability")
	public String availability(Model model) {

		List<Room> rooms = roomRepository.findAll();
		List<Booking> bookings = new ArrayList<Booking>();
		for (Room room : rooms) {

			if (room.findBooking(LocalDate.now(), LocalDate.now()) != null) {
				bookings.addAll(room.findBookings(LocalDate.now(), LocalDate.now()));
				room.setAvailable(false);

			} else {
				room.setAvailable(true);
			}

		}
		model.addAttribute("bookings", bookings);
		model.addAttribute("rooms", rooms);
		return "Lists/todaysCats";

	}

	@GetMapping("/staff/room/cats/{id}")
	public String showCats(@PathVariable Integer id, Model model) {
		Room room = roomRepository.findByRoomNo(id);
		Booking booking = room.findBooking(LocalDate.now(), LocalDate.now());
		model.addAttribute("cats", booking.getCats());
		return "/Lists/bookingViewCats";

	}
}