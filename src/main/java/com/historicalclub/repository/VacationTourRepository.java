package com.historicalclub.repository;

import com.historicalclub.entity.VacationTour;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationTourRepository extends JpaRepository<VacationTour, Long> {

}
