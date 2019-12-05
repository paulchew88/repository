package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.repositories.CatRepository;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Autowired
	CatRepository catRepository;

	public List<String> catName= new ArrayList<String>();

	@GetMapping("/")
	public String main(Model model) {
		catName.clear();
		List<Cat>cats = catRepository.findAll();
		for(Cat cat:cats) {
			catName.add(cat.getName());
		}
		model.addAttribute("message", message);

		model.addAttribute("tasks", catName);

		return "home"; // view
	}

	// /hello?name=kotlin
	@GetMapping("/hello")
	public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "User") String name,
			Model model) {

		model.addAttribute("message", name);

		return "alt"; // view
	}

	@RequestMapping("/start")
	public String Start() {

		return "start";
	}

}