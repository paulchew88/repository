package com.pc1crt.application.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc1crt.application.model.Owner;
import com.pc1crt.application.repositories.OwnerRepository;

@RestController
public class OwnerController {
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping("/api/owner")
	public List<Owner> index() {
		return ownerRepository.findAll();
	}

	@GetMapping("/api/owner/{id}")
	public Object show(@PathVariable String id) {
		return ownerRepository.findById(id);
	}

	@GetMapping("/api/owner/search")
	public Owner search(@RequestBody Map<String, String> body) {
		String searchTerm = body.get("text");
		return ownerRepository.findByEmailContaining(searchTerm);
	}

	@PostMapping("/api/owner")
	public Owner create(@RequestBody Owner owner, UriComponentsBuilder ucBuilder) {
		if (ownerRepository.existsById(owner.getEmail()))
			return null;
		else
			return ownerRepository.save(owner);
	}

	@PutMapping("/api/owner/{id}")
	public Owner update(@RequestBody Owner owner, UriComponentsBuilder ucBuilder) {
		if (!ownerRepository.existsById(owner.getEmail()))
			return null;
		else
			return ownerRepository.save(owner);
	}


	@DeleteMapping("/api/owner/{id}")
	public Boolean delete(@PathVariable String id) {
		if (ownerRepository.existsById(id)) {
			ownerRepository.deleteById(id);
			return true;
		} else
			return false;

	}
}
