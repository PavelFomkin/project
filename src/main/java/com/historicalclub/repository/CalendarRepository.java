package com.historicalclub.repository;

import com.historicalclub.entity.Calendar;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CalendarRepository extends JpaRepository<Calendar, LocalDate> {

  Optional<Calendar> findByOrderId(Long orderId);

}
