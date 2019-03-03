package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantDateRepository extends JpaRepository<VacantDate, Long> {

  List<VacantDate> findAllByTour(Tour tour);

  List<VacantDate> findAllByTourAndVacant(Tour tour, Boolean vacant);

}
