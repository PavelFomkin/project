package com.historicalclub.repository;

import com.historicalclub.entity.Picture;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {

  Optional<List<Picture>> findAllByTourId(Long tourId);

  Optional<List<Picture>> deleteByTourId(Long tourId);
}
