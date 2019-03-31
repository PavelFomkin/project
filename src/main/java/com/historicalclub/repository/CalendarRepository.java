package com.historicalclub.repository;

import com.historicalclub.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CalendarRepository extends JpaRepository<Calendar, LocalDate> {

//  Optional<List<VacantDate>> findAllByTour(Tour tour);
//
//  Optional<List<VacantDate>> findAllByTourAndVacant(Tour tour, Boolean vacant);
//
//  Optional<VacantDate> findByIdAndVacant(Long vacId, Boolean vacant);

}
