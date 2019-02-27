package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantTour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantTourRepository extends JpaRepository<VacantTour, Long> {

  List<VacantTour> findAllByTour(Tour tour);

  List<VacantTour> findAllByTourAndVacant(Tour tour, Boolean vacant);

}
