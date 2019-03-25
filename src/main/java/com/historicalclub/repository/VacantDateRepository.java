package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantDateRepository extends JpaRepository<VacantDate, Long> {

  Optional<List<VacantDate>> findAllByTour(Tour tour);

  Optional<List<VacantDate>> findAllByTourAndVacant(Tour tour, Boolean vacant);

}
