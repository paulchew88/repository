package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import com.pc1crt.application.repositories.*;

@Controller
public class BookingController {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping("/bookings")
	public String main(Model model) {
		model.addAttribute("rooms", bookingRepository.findAll());

		return "roomBookingList";
	}

	@RequestMapping(value = "/newBooking", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("booking") Booking booking,@ModelAttribute("owner") Owner owner, BindingResult result, ModelMap model) {
		Owner newOwner =ownerRepository.findByEmailContaining(owner.getEmail());
		System.out.println(newOwner);
		booking.setOwner(newOwner);
		bookingRepository.save(booking);

		return "redirect:/rooms";
	}


	@RequestMapping("/booking/update/{id}")
	public String updateCat(@PathVariable Integer id, Model model) {
		Optional<Booking> optionalBooking = bookingRepository.findById(id);
		Booking booking = optionalBooking.get();
		model.addAttribute(booking);
		return "newBooking";
	}
	@GetMapping("/room/booking/{id}")
	public String getBookings(@PathVariable Integer id, Model model) {
		List<Booking> bookings = new ArrayList<Booking>();
		bookings = bookingRepository.findByRoomRoomNo(id);
		//list of bookings for room 1
		model.addAttribute("bookings",bookings);
		return "/roomBookingList";
	}
	
	@RequestMapping(value = "/newBooking", method = RequestMethod.GET)
	public String bookingForm(Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute(new Owner());
		return "newBooking";
	}
	
		
}
