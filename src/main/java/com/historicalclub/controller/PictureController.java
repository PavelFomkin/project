package com.historicalclub.controller;

import com.historicalclub.entity.Picture;
import com.historicalclub.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PictureController {

  private final PictureService pictureService;

  @Autowired
  public PictureController(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  @RequestMapping(value = "/pictures/{id}", method = RequestMethod.GET)
  public List<Picture> getPictures(@PathVariable Long id) {
    return pictureService.getPictures(id);
  }

  @RequestMapping(value = "/update-pictures/{id}", method = RequestMethod.PUT)
  public List<Picture> updatePictures(@PathVariable Long id, @Valid @RequestBody List<Picture> pictures) {
    return pictureService.updatePictures(pictures, id);
  }
}
