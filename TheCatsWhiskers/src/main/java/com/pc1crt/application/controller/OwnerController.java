package com.pc1crt.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
import com.pc1crt.application.repositories.OwnerRepository;

@Controller
public class OwnerController {
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	CatRepository catRepository;

	@GetMapping("/owners")
	public String main(Model model) {
		model.addAttribute("owners", ownerRepository.findAll());

		return "customerList";
	}

	@RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("owner", new Owner());

		return "newCustomer";
	}

	@RequestMapping(value = "/newCustomer", method = RequestMethod.POST)
	public String submitOwner(@Valid @ModelAttribute("owner") Owner owner, BindingResult result, ModelMap model) {

		ownerRepository.save(owner);

		return "redirect:/owners";

	}

	@RequestMapping("/owner/delete/{id}")
	public String delete(@PathVariable Integer id) {
		ownerRepository.deleteById(id);
		return "redirect:/owners";
	}

	@RequestMapping("/owner/cat/{id}")
	public String showCats(@PathVariable Integer id, Model model) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		Owner owner = optionalOwner.get();
		model.addAttribute("cats", owner.getCats());

		return "ownerCatList";
	}

	@RequestMapping("/owner/update/{id}")
	public String updateOwner(@PathVariable Integer id, Model model) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		Owner owner = optionalOwner.get();
		model.addAttribute(owner);
		return "newCustomer";
	}

	@GetMapping("/owner/cat/add/{id}")
	public String main(@PathVariable Integer id, Model model) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		Owner owner = optionalOwner.get();

		model.addAttribute(new Cat());
		model.addAttribute(owner);

		return "ownerAddCat";
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

		System.out.println(cat);
		System.out.println(owner);
		cats.add(cat);
		owner.setCats(cats);

		ownerRepository.save(owner);

		return "redirect:/owners";
	}

	@RequestMapping("owner/cat/delete/{id}")
	public String removeCat(@PathVariable Integer id, @ModelAttribute("cat") Cat cat, BindingResult result,
			ModelMap model) {

		Owner owner = ownerRepository.findByCatsChipNo(id);

		List<Cat> cats = owner.getCats();
		int found = 0;
		Cat newCat = new Cat();
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).getChipNo() == cat.getChipNo()) {
				found = i;
			}
		}

		cats.remove(found);
		owner.setCats(cats);
		ownerRepository.save(owner);
		return "redirect:/owners";
	}
}
