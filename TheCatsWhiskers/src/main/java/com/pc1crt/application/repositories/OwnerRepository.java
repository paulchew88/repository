package com.pc1crt.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	
	public Owner findByEmailContaining(String text);
	
	public Owner findByCatsChipNo(Integer id);
	

}
