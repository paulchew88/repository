package com.pc1crt.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
