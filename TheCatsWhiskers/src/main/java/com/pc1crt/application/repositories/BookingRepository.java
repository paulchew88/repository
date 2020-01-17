package com.pc1crt.application.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.application.model.Booking;
import com.pc1crt.application.model.BookingKey;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> findByRoomRoomNo(Integer id);
	public Booking findByBookingNo(Integer id);


}
