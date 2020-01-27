package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@Autowired
	CatRepository catRepository;

	@GetMapping("/bookings")
	public String main(Model model) {
		model.addAttribute("rooms", bookingRepository.findAll());

		return "roomBookingList";
	}

	@RequestMapping(value = "/newBooking", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("booking") Booking booking, @ModelAttribute("owner") Owner owner,
			BindingResult result, ModelMap model) {
		
		Owner newOwner = ownerRepository.findByEmailContaining(owner.getEmail());
		System.out.println(newOwner);
		booking.setOwner(newOwner);
		
		bookingRepository.save(booking);
		model.addAttribute(booking);
		System.out.println(booking);
		model.addAttribute("cats", booking.getOwner().getCats());

		return "bookingAddCat";
	}

	@RequestMapping("/booking/update/{id}")
	public String updateCat(@PathVariable Integer id, Model model) {

		Booking booking = bookingRepository.findByBookingNo(id);
		model.addAttribute(booking);
		model.addAttribute("cats", booking.getOwner().getCats());

		System.out.println(booking);
		return "bookingAddCat";
	}

	@PostMapping("/addCats/{id}")
	public String addCats(@PathVariable("id") Integer id, @Valid Booking booking, @ModelAttribute("cat") Cat cat,
			@RequestParam(value = "cats", required = false) Set<Cat> cats, BindingResult bindingResult, Model model) {
		Booking newBooking = bookingRepository.findByBookingNo(id);
		System.out.println(newBooking);
		if (!cats.isEmpty()) {
			newBooking.getCats().clear();
			for (Cat newCat : cats) {
				
				newBooking.addCat(newCat);
			}
		
		bookingRepository.save(newBooking);
		System.out.println(newBooking);
		}
		else {
		 newBooking.getCats().clear();
		}

		return "redirect:/rooms";
	}

	@GetMapping("/room/booking/{id}")
	public String getBookings(@PathVariable Integer id, Model model) {
		Set<Booking> bookings = bookingRepository.findByRoomRoomNo(id);
		// list of bookings for room 1
		model.addAttribute("bookings", bookings);
		return "/roomBookingList";
	}

	@GetMapping("/newBooking")
	public String bookingForm(Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute(new Owner());
		return "NewForms/newBooking";
	}

	@RequestMapping("/room/booking/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Booking booking = bookingRepository.findByBookingNo(id);
		booking.setOwner(null);
		bookingRepository.deleteById(id);
		return "redirect:/rooms";
	}
}
