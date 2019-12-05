package com.pc1crt.application.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.MealPlan;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;
import com.pc1crt.application.repositories.OwnerRepository;

@RestController
public class CatController {
	@Autowired
	CatRepository catRepository;
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping("/api/cat")
	public List<Cat> index() {
		return catRepository.findAll();

	}

	@GetMapping("/api/cat/{id}")
	public Object show(@PathVariable Integer id) {
		return catRepository.findById(id);
	}

	@GetMapping("/api/cat/search")
	public List<Cat> search(@RequestBody Map<String, String> body) {
		String searchTerm = body.get("text");
		return catRepository.findByNameContaining(searchTerm);
	}
	@GetMapping("/api/cat/search/owner")
	public List<Cat> searchOwner(@RequestBody Map<String, String> body) {
		Owner owner = ownerRepository.findByEmailContaining(body.get("text"));
		return catRepository.findByOwner(owner);
	}

	@PostMapping("/api/cat")
	public Cat create(@RequestBody Map<String, String> body) {
		Cat cat = new Cat();
		Owner owner = ownerRepository.findByEmailContaining(body.get("email"));
		cat.setChipNo(Integer.valueOf(body.get("chipNo")));
		cat.setFood(MealPlan.valueOf(body.get("mealPlan")));
		cat.setLitterType(body.get("litterType"));
		cat.setName(body.get("name"));
		cat.setOtherInformatioin(body.get("otherInformation"));
		cat.setOwner(owner);
		cat.setTemperment(body.get("temperment"));
		cat.setVaccinatedDate(LocalDate.parse(body.get("date")));
		
		
		return catRepository.save(cat);
	}
	@PutMapping("/api/cat/{id}")
	public Cat update(@PathVariable Integer id, @RequestBody Map<String, String> body) {
		Optional<Cat> optionalCat = catRepository.findById(id);
		Cat cat = optionalCat.get();
		
		Owner owner = ownerRepository.findByEmailContaining(body.get("email"));
		cat.setChipNo(Integer.valueOf(body.get("chipNo")));
		cat.setFood(MealPlan.valueOf(body.get("mealPlan")));
		cat.setLitterType(body.get("litterType"));
		cat.setName(body.get("name"));
		cat.setOtherInformatioin(body.get("otherInformation"));
		cat.setOwner(owner);
		cat.setTemperment(body.get("temperment"));
		cat.setVaccinatedDate(LocalDate.parse(body.get("date")));
		
		return catRepository.save(cat);
	}
}
