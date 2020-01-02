package com.pc1crt.application.controller;

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

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;

@Controller
public class CatController {

	@Autowired
	CatRepository catRepository;
	
	@GetMapping("/cats")
	public String main(Model model) {
		model.addAttribute("cats", catRepository.findAll());

		return "ownerCatList";
	}
	
	@RequestMapping(value = "/newCat", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("cat") Cat cat, BindingResult result, ModelMap model) {

		catRepository.save(cat);

		return "redirect:/owners";
	}

	@RequestMapping(value = "/newCat", method = RequestMethod.GET)
	public String catForm(Model model) {
		model.addAttribute("cat", new Cat());

		return "newCat";
	}
	@RequestMapping("/cat/update/{id}")
	public String updateCat(@PathVariable Integer id, Model model) {
		Optional<Cat> optionalCat = catRepository.findById(id);
		Cat cat = optionalCat.get();
		model.addAttribute(cat);
		return "newCat";
	}
}
