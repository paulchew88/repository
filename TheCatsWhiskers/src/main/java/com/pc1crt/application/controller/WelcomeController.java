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
		return "availability";

	}
}