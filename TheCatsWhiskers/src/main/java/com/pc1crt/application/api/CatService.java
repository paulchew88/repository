package com.pc1crt.application.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.MealPlan;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.CatRepository;
import com.pc1crt.application.repositories.OwnerRepository;
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CatService {
	@Autowired
	CatRepository catRepository;

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


	@PostMapping("/api/cat")
	public Cat create(@RequestBody Cat cat, UriComponentsBuilder ucBuilder) {
		if (catRepository.existsById(cat.getChipNo()))
			return null;

		else
			return catRepository.save(cat);
	}

	@PutMapping("/api/cat/{id}")
	public Object update(@PathVariable Integer id, @RequestBody Cat cat, UriComponentsBuilder ucBuilder) {

		if (!catRepository.existsById(cat.getChipNo()))
			return null;

		else
			return catRepository.save(cat);
	}
	@DeleteMapping("/api/cat/{id}")
	public Boolean delete(@PathVariable Integer id) {
		if (catRepository.existsById(id)) {
			catRepository.deleteById(id);
			return true;
		} else
			return false;

	}

}
