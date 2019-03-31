package com.historicalclub.repository;

import com.historicalclub.entity.Tour;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
  String GET_PREVIEW_TOURS = "select tour.ID , tour.TITLE , tour.SHORT_DESCRIPTION, tour.IMAGE_URL, tour.VISIBLE from TOURS tour";
  String GET_PREVIEW_AVAILABLE_TOURS = "select tour.ID , tour.TITLE , tour.SHORT_DESCRIPTION, tour.IMAGE_URL, tour.VISIBLE " +
          "from TOURS tour where tour.VISIBLE = true";

  @Query(value = GET_PREVIEW_TOURS, nativeQuery = true)
  List<Object> getPreviewTours();

  @Query(value = GET_PREVIEW_AVAILABLE_TOURS, nativeQuery = true)
  List<Object> getPreviewAvailableTours();
}
