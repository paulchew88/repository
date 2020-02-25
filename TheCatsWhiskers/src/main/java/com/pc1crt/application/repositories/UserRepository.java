package com.pc1crt.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import com.pc1crt.application.model.*;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByUserName(String userName);



}
