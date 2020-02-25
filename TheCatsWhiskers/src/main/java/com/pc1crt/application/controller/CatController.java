package com.pc1crt.application.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pc1crt.application.error.FormErrorExcepeion;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;

@Controller
public class CatController {

	@Autowired
	CatRepository catRepository;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	@GetMapping("/staff/cats")
	public String main(Model model) {
		model.addAttribute("cats", catRepository.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("name", auth.getName());
		System.out.println(auth.getName());
		return "ownerCatList";
	}

	@RequestMapping(value = "/newCat", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("cat") Cat cat, BindingResult result, ModelMap model) {

		catRepository.save(cat);

		return "redirect:/";
	}

	@RequestMapping(value = "/staff/newCat", method = RequestMethod.GET)
	public String catForm(Model model) {
		model.addAttribute("cat", new Cat());

		return "newCat";
	}

	@RequestMapping("/staff/cat/update/{id}")
	public String updateCat(@PathVariable Integer id, Model model) {
		Cat cat = catRepository.findByChipNo(id);
		model.addAttribute(cat);
		return "newCat";
	}

	@RequestMapping("/staff/cat/checkin")
	public String checkedin(@PathVariable Integer id, Model model) {
		Cat cat = catRepository.findByChipNo(id);
		if (!cat.getCheckedIn()) {
			cat.setCheckedIn(true);
			throw new FormErrorExcepeion(cat.getName() + " was checked in");
		} else {
			throw new FormErrorExcepeion(cat.getName() + " was checked out");

		}
	}

	@ExceptionHandler({ FormErrorExcepeion.class })
	public String getFormError(FormErrorExcepeion ex, Model model) {
		model.addAttribute("error", ex.getMessage());

		return "/Searches/bookingSearch";
	}
}
