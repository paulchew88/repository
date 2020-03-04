package com.pc1crt.application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pc1crt.application.error.FormErrorExcepeion;
import com.pc1crt.application.error.UnavailableException;
import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.model.Room;
import com.pc1crt.application.model.RoomType;
import com.pc1crt.application.repositories.*;

@Controller
public class BookingController {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	CatRepository catRepository;
	@Autowired
	RoomRepository roomRepository;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	

	@GetMapping("/staff/bookings")
	public String main(Model model) {
		model.addAttribute("search", bookingRepository.findAll());

		return "/Searches/bookingSearch";
	}

	@RequestMapping(value = "/newBooking", method = RequestMethod.POST)
	public String submit(@Valid Room room, @ModelAttribute("booking") Booking booking, @ModelAttribute("owner") Owner owner,
			BindingResult result, ModelMap model) throws Exception {

		Owner newOwner = ownerRepository.findByEmailContaining(owner.getEmail());
			
		booking.setRoom(room);
		booking.setOwner(newOwner);

		if (booking.getRoom().findBooking(booking.getCheckInDate(), booking.getCheckOutDate()) == null) {
			bookingRepository.save(booking);
			model.addAttribute(booking);
			model.addAttribute("cats", booking.getOwner().getCats());

			return "bookingAddCat";
		} else {

			throw new UnavailableException("date is already booked");
		}
	}

	@RequestMapping("/staff/booking/update/{id}")
	public String updateCat(@PathVariable Integer id, Model model) {

		Booking booking = bookingRepository.findByBookingNo(id);
		model.addAttribute(booking);
		model.addAttribute("cats", booking.getOwner().getCats());

		System.out.println(booking);
		return "bookingAddCat";
	}

	@PostMapping("/staff/addCats/{id}")
	public String addCats(@PathVariable("id") Integer id, @Valid Booking booking, @ModelAttribute("cat") Cat cat,
			@RequestParam(value = "cats", required = false) Set<Cat> cats, BindingResult bindingResult, Model model) {
		Booking newBooking = bookingRepository.findByBookingNo(id);
		
		if (cats == null) {
			
			newBooking.setOwner(null);
			bookingRepository.deleteById(newBooking.getBookingNo());
			throw new FormErrorExcepeion(
					"you need to add atleast 1 cat to a booking, this booking has been removed please re-create it and add the correct amount of cats");
		} else if ((cats.size() <= 2 && newBooking.getRoom().getRoomType().compareTo(RoomType.Standard_Room) == 0)
				|| (cats.size() <= 4 && newBooking.getRoom().getRoomType().compareTo(RoomType.Family_Room) == 0)) {

			if (!cats.isEmpty()) {
				newBooking.getCats().clear();
				for (Cat newCat : cats) {
					if(newCat.getVaccinatedDate().isBefore(newBooking.getCheckInDate().minusWeeks(4)) && newCat.getVaccinatedDate().isAfter(newBooking.getCheckInDate().minusMonths(12))) {
						
						System.out.println(newCat.getVaccinatedDate());
						System.out.println(newBooking.getCheckInDate().minusWeeks(4));
						newBooking.addCat(newCat);
					}
					else {
						newBooking.setOwner(null);
						bookingRepository.deleteById(newBooking.getBookingNo());
						throw new FormErrorExcepeion(newCat.getName()+ " can't be added to this booking as their vaccination date is either over 12 months ago or less than 4 weeks ago. Please re-create this booking");
					}
				}
				newBooking.totalCost();
				bookingRepository.save(newBooking);
				
			}
			return "redirect:/staff/booking";
		} else {
			newBooking.setOwner(null);
			bookingRepository.deleteById(newBooking.getBookingNo());
			throw new FormErrorExcepeion(
					"too many cats for this type of room, this booking has been removed please re-create it and add the correct amount of cats");
		}

	}

	@GetMapping("/staff/room/booking/{id}")
	public String getBookings(@PathVariable Integer id, Model model) {
		Set<Booking> bookings = bookingRepository.findByRoomRoomNo(id);
		// list of bookings for room 1
		model.addAttribute("search", bookings);
		return "/Searches/bookingSearch";
	}

	@GetMapping("/staff/booking/new/{id}")
	public String bookingForm(@PathVariable Integer id,Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute("room",roomRepository.findByRoomNo(id));
		model.addAttribute(new Owner());
		return "NewForms/newBooking";
	}

	@RequestMapping("/admin/room/booking/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Booking booking = bookingRepository.findByBookingNo(id);
		booking.setOwner(null);
		bookingRepository.deleteById(id);
		return "redirect:/staff/booking";
	}

	@GetMapping("/staff/booking")
	public String search(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("name", auth.getName());
		System.out.println(auth.getName());
		return "/Searches/bookingSearch";
	}

	@RequestMapping(value = "/staff/search/booking", method = RequestMethod.GET)
	public String showBookingByDate(@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2, Model model,
			@RequestParam(value = "roomNo", required = false) String roomNo) {
		if (roomNo.toLowerCase().contentEquals("all")) {

			List<Room> rooms = roomRepository.findAll();
			List<Booking> bookings = new ArrayList<Booking>();
			for (Room room : rooms) {
				
					if (room.findBooking(LocalDate.parse(date1), LocalDate.parse(date2)) != null) {
						bookings.addAll(room.findBookings(LocalDate.parse(date1), LocalDate.parse(date2)));
						room.setAvailable(false);

					}
					else {
						room.setAvailable(true);
					}
				
			}
			model.addAttribute("bookings", bookings);
			model.addAttribute("rooms", rooms);
			return "Lists/availability";
		} else if (date1.isEmpty() || date2.isEmpty() || roomNo.isEmpty()) {
			throw new FormErrorExcepeion("All 3 search fields are required");

		} else {

			Room newRoom = roomRepository.findByRoomNo(Integer.parseInt(roomNo));
			List<Booking> bookingByDates = newRoom.findBookings(LocalDate.parse(date1), LocalDate.parse(date2));

			if (!bookingByDates.isEmpty()) {
				model.addAttribute("search", bookingByDates);
			} else {
				throw new FormErrorExcepeion("No bookings found");
			}

			return "/Searches/bookingSearch";
		}
	}

	@GetMapping("/staff/booking/View/{id}")
	public String viewBooking(@PathVariable Integer id, Model model) {

		model.addAttribute("bookings", bookingRepository.findByBookingNo(id));
		return "/Lists/bookings";
	}

	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute(new Owner());
		model.addAttribute("error", ex.getMessage());

		return "NewForms/newBooking";
	}

	@ExceptionHandler({ FormErrorExcepeion.class })
	public String getFormError(FormErrorExcepeion ex, Model model) {
		model.addAttribute("error", ex.getMessage());

		return "/Searches/bookingSearch";
	}

}
