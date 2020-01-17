package com.pc1crt.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Cat;
import com.pc1crt.application.model.Owner;

public interface CatRepository extends JpaRepository<Cat, Integer> {
	public List<Cat> findByNameContaining(String text);
	public Cat findByChipNo(Integer id);

}
