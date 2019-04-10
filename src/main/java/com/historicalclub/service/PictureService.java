package com.historicalclub.service;

import com.historicalclub.entity.Picture;
import com.historicalclub.repository.PictureRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PictureService {

  private final PictureRepository pictureRepository;
  private final TourService tourService;

  @Autowired
  public PictureService(PictureRepository pictureRepository, TourService tourService) {
    this.pictureRepository = pictureRepository;
    this.tourService = tourService;
  }

  public List<Picture> getPictures(Long id) {
    System.out.println("get pictures for tourId = " + id);
    tourService.checkThatTourExists(id);
    return pictureRepository.findAllByTourId(id).orElse(Collections.emptyList());
  }

  public ResponseEntity<?> deletePicturesByTourId(Long id) {
    System.out.println("delete pictures for tourId = " + id);
    pictureRepository.deleteByTourId(id);
    return ResponseEntity.ok().build();
  }

  public List<Picture> updatePictures(List<Picture> pictures, Long id){
    System.out.println("add pictures for tourId = " + id);
    tourService.checkThatTourExists(id);
    List<Picture> savedPictures = new ArrayList<>();
    pictures.forEach(picture -> savedPictures.add(pictureRepository.save(picture)));
    return savedPictures;
  }
}
