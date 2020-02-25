package com.pc1crt.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	
	public Owner findByEmailContaining(String text);
	public List<Owner>findByEmailContainingOrEmailEquals(String text,String moreText);
	
	public Owner findByCatsChipNo(Integer id);
	public Owner findByCustomerNumber(Integer id);

	public Object findByAddressPostCodeContaining(String postCode);

	public Object findByFirstNameContainingAndSurNameContaining(String firstName, String surName);
	

}
