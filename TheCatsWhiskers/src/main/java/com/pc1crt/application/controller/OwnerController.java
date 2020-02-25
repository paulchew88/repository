package com.pc1crt.application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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

import com.pc1crt.application.error.FormErrorExcepeion;
import com.pc1crt.application.error.UnavailableException;
import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;
import com.pc1crt.application.repositories.OwnerRepository;

@Controller
public class OwnerController {
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	CatRepository catRepository;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	@GetMapping("/staff/newCustomer")
	public String customerForm(Model model) {
		model.addAttribute("owner", new Owner());

		return "NewForms/newOwner";
	}

	@PostMapping("/newOwner")
	public String submitOwner(@Valid @ModelAttribute("owner") Owner owner, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "NewForms/newOwner";
		}
		if (ownerRepository.findByEmailContaining(owner.getEmail()) == null) {
			ownerRepository.save(owner);

			return "redirect:/staff/owners";
		}
		throw new UnavailableException("Email already registered");
	}

	@GetMapping("/staff/owner/update/{id}")
	public String getOwnerUpdateForm(@PathVariable Integer id, Model model) {
		Owner owner = ownerRepository.findByCustomerNumber(id);
		model.addAttribute(owner);
		return "UpdateForms/updateOwner";
	}

	@PostMapping("/owner/update/{id}")
	public String updateOwner(@PathVariable Integer id, @Valid Owner owner, BindingResult result, Model model) {
		if (result.hasErrors()) {
			owner.setCustomerNumber(id);
			return "UpdateForms/updateOwner";
		}

		ownerRepository.save(owner);

		return "redirect:/owners";
	}

	@RequestMapping("/admin/owner/delete/{id}")
	public String delete(@PathVariable Integer id) {
		try {
			ownerRepository.deleteById(id);
		} catch (Exception e) {
			throw new FormErrorExcepeion("oops unable to delete owner. ensure owner has no outstanding bookings");
		}
		return "redirect:/staff/owners";

	}

	@GetMapping("/staff/owner/cat/{id}")
	public String showCats(@PathVariable Integer id, Model model) {

		Owner owner = ownerRepository.findByCustomerNumber(id);
		model.addAttribute("cats", owner.getCats());
		System.out.println(owner.getCats());
		return "Lists/ownerCatList";
	}

	@GetMapping("/staff/owner/cat/add/{id}")
	public String main(@PathVariable Integer id, Model model) {
		Owner owner = ownerRepository.findByCustomerNumber(id);
		model.addAttribute(owner);
		model.addAttribute(new Cat());
		return "NewForms/ownerAddCat";
	}

	@RequestMapping(value = "/addCat", method = RequestMethod.POST)
	public String addCat(@Valid @ModelAttribute("cat") Cat cat, @ModelAttribute("owner") Owner owner,
			BindingResult result, ModelMap model) {
		List<Cat> cats = new ArrayList<Cat>();
		if (owner.getCats() != null) {
			cats = owner.getCats();
		}
		if (!catRepository.existsById(cat.getChipNo())) {
			catRepository.save(cat);
		}

		cats.add(cat);
		owner.setCats(cats);

		ownerRepository.save(owner);

		return "redirect:/staff/owners";
	}

	@RequestMapping("/admin/owner/cat/delete/{id}")
	public String removeCat(@PathVariable Integer id) {

		Owner owner = ownerRepository.findByCatsChipNo(id);
		owner.removeCat(catRepository.findByChipNo(id));

		ownerRepository.save(owner);

		return "redirect:/staff/owners";
	}

	@GetMapping("/staff/owners")
	public String search(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("name", auth.getName());
		System.out.println(auth.getAuthorities());
		model.addAttribute("search", ownerRepository.findAll());

		return "/Searches/ownerSearch";
	}

	@RequestMapping(value = "/staff/search/owner", method = RequestMethod.GET)
	public String showStudentBySurname(@RequestParam(value = "search", required = false) String search, Model model) {

		List<Owner> ownersByEmail = ownerRepository.findByEmailContainingOrEmailEquals(search, search);
		if (!ownersByEmail.isEmpty()) {
			model.addAttribute("search", ownersByEmail);
		} else {
			throw new FormErrorExcepeion("No owners found");
		}

		return "/Searches/ownerSearch";
	}

	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("owner", new Owner());

		return "NewForms/newOwner";

	}

	@ExceptionHandler({ FormErrorExcepeion.class })
	public String getFormError(FormErrorExcepeion ex, Model model) {
		model.addAttribute("error", ex.getMessage());

		return "/Searches/ownerSearch";

	}
}