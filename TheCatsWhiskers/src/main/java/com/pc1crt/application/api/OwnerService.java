package com.pc1crt.application.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.OwnerRepository;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class OwnerController {
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping("/api/owner")
	public List<Owner> index() {
		return ownerRepository.findAll();
	}

	@GetMapping("/api/owner/{id}")
	public Object show(@PathVariable Integer id) {
		return ownerRepository.findById(id);
	}

	@GetMapping("/api/owner/search")
	public Owner search(@RequestBody Map<String, String> body) {
		String searchTerm = body.get("text");
		return ownerRepository.findByEmailContaining(searchTerm);
	}

	@GetMapping("/api/owner/cat/{id}")
	public List<Cat> getCats(@PathVariable Integer id) {
		Optional<Owner> optionalowner = ownerRepository.findById(id);
		Owner owner = new Owner();
		owner = optionalowner.get();
		List<Cat> cats = new ArrayList<Cat>();
		for (Cat cat : owner.getCats()) {
			cats.add(cat);

		}
		return cats;
	}

	@PostMapping("/api/owner")
	public Owner create(@RequestBody Owner owner, UriComponentsBuilder ucBuilder) {
		if (ownerRepository.existsById(owner.getCustomerNumber()))
		return null;
		else
		return ownerRepository.save(owner);
	}

	@PostMapping("/api/owner/cat/{id}")
	public Owner update(@PathVariable Integer id, @RequestBody Owner owner, UriComponentsBuilder ucBuilder) {
		if (!ownerRepository.existsById(id))
			return null;
		else
			return ownerRepository.save(owner);
	}

	@DeleteMapping("/api/owner/{id}")
	public Boolean delete(@PathVariable Integer id) {
		if (ownerRepository.existsById(id)) {
			ownerRepository.deleteById(id);
			return true;
		} else
			return false;

	}

	@PostMapping("/api/owner/cat/add/{id}")
	public Owner addCat(@PathVariable Integer id, @RequestBody Cat cat, UriComponentsBuilder ucBuilder) {
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		Owner owner = optionalOwner.get();
		List<Cat> cats = owner.getCats();
		cats.add(cat);
		owner.setCats(cats);

		return ownerRepository.save(owner);
	}

	@DeleteMapping("/api/owner/cat/{id}")
	public Owner removeCat(@PathVariable Integer id, @RequestBody Cat cat, UriComponentsBuilder ucBuilder) {

		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		Owner owner = optionalOwner.get();
		List<Cat> cats = owner.getCats();
		System.out.println(cats);
		/*
		 * Iterator<Cat> it = cats.iterator(); while(it.hasNext()) { if
		 * (it.equals(cat)){ cats.remove(it); } it.next(); }
		 */
		int found = 0;
		Cat newCat = new Cat();
		//List<Cat> found = new ArrayList<Cat>();
		for(int i = 0; i< cats.size(); i++) {
			if( cats.get(i).getChipNo() == cat.getChipNo()) {
				found = i;
			}
		}
		
		System.out.println(newCat);

		cats.remove(found);
		owner.setCats(cats);
		System.out.println(cats);

		return ownerRepository.save(owner);

	}
}
