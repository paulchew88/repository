package com.pc1crt.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pc1crt.application.error.FormErrorExcepeion;
import com.pc1crt.application.error.UnavailableException;
import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.model.User;
import com.pc1crt.application.repositories.UserRepository;

@Controller
public class AdminController {
	@Autowired
	UserRepository userRepo;

	@GetMapping("/admin/users")
	public String g(Model model) {
		model.addAttribute("users", userRepo.findAll());

		return "Lists/users";
	}

	@GetMapping("/admin/users/update/{id}")
	public String getUserUpdateForm(@PathVariable Integer id, Model model) {
		User user = userRepo.findByIdEquals(id);
		model.addAttribute(user);
		return "/UpdateForms/users";
	}

	@PostMapping("/admin/users/update/{id}")
	public String updateUser(@PathVariable Integer id, @Valid User user, BindingResult result, Model model) {
		if (user.getPassword().contains(" ")) {

			throw new FormErrorExcepeion("password must not contain spaces user not updated");
		} else if (user.getPassword() == "") {
			user.setPassword(userRepo.findByIdEquals(user.getId()).getPassword());
			userRepo.save(user);
			throw new FormErrorExcepeion("password was left blank and was not updated");
		} else {
			userRepo.save(user);
		}
		return "redirect:/admin/users";
	}

	@GetMapping("admin/users/new")
	public String getUserForm(Model model) {
		model.addAttribute(new User());
		return "/NewForms/newUser";
	}

	@RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model)
			throws Exception {
		if (result.hasErrors()) {
			return "/NewForms/newUser";
		}

		if (userRepo.findByUserName(user.getUserName()) == null) {
			if (user.getPassword().contains(" ")) {

				throw new FormErrorExcepeion("password must not contain spaces user not created");
			}
			if (user.getPassword() == "") {

				throw new FormErrorExcepeion("password was left blank and was not created");
			} else {
				userRepo.save(user);
				return "/Lists/users";
			}
		}
		throw new UnavailableException("user name already exists");

	}
	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("user", new User());

		return "NewForms/newUser";

	}
	@ExceptionHandler({ FormErrorExcepeion.class })
	public String getFormError(FormErrorExcepeion ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		model.addAttribute("users", userRepo.findAll());
		return "/Lists/users";
	}
}