package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

  Optional<List<Tour>> findAllByVisible(boolean visible);

}
