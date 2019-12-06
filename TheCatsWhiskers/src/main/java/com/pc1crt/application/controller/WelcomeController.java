package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pc1crt.application.api.CatController;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;
import com.pc1crt.application.repositories.OwnerRepository;

@Controller
public class WelcomeController {

	// Logger log = (Logger) LoggerFactory.logger(this.getClass());
	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Autowired
	CatRepository catRepository;
	@Autowired
	OwnerRepository ownerRepository;

	public List<String> catName = new ArrayList<String>();

	@GetMapping("/home")
	public String main(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("tasks", ownerRepository.findAll());

		// model.addAttribute("tasks", catName);

		return "home"; // view
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("owner", new Owner());

		return "form";
	}

	// @RequestMapping(value="/form", method=RequestMethod.POST)
	/*
	 * public String customerSubmit(@ModelAttribute Owner owner, Model model) {
	 * 
	 * model.addAttribute("customer", owner);
	 * 
	 * 
	 * String info = String.
	 * format("Owner Submission: id = %s, firstName = %s, surName = %s, email = %s, "
	 * + "houseNumber = %s, postCode = %s, contactNumber = %s, streetName = %s",
	 * owner.getFirstName(), owner.getSurName(),
	 * owner.getEmail(),owner.getHouseNumber(),owner.getId(),owner.getStreetName(),
	 * owner.getPostCode(),owner.getContactNumber()); ownerRepository.save(owner);
	 */

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("employee") Owner owner, BindingResult result, ModelMap model) {

		// model.addAttribute("tasks", owner);
		ownerRepository.save(owner);
		return "result";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";

	}
}