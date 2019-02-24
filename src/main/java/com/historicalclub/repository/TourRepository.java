package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

//    List<Tour> findById(long id);

}
