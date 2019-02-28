package com.historicalclub.service;

import com.historicalclub.TourNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantTour;
import com.historicalclub.repository.TourRepository;
import com.historicalclub.repository.VacantTourRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TourService {

  @Autowired
  TourRepository tourRepository;

  @Autowired
  VacantTourRepository vacantTourRepository;

  public List<VacantTour> getVacantTours(Long id) {
    System.out.println("get vacant tours");
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    return vacantTourRepository.findAllByTour(tour);
  }

  public VacantTour getVacantTour(Long id) {
    System.out.println("get vacant tour " + id);
    return vacantTourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
  }

  public List<VacantTour> getAvailableVacantTours(Long id) {
    System.out.println("get all available vacant tours");
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    return vacantTourRepository.findAllByTourAndVacant(tour, true);
  }

  public List<Tour> getTours() {
    fillDatabaseIfEmpty();
    System.out.println("show tours");
    return tourRepository.findAll();
  }

  public Tour getTour(Long id) {
    System.out.println("get tour " + id);
    Optional<Tour> tour = tourRepository.findById(id);
    return tour.orElseThrow(() -> new TourNotFoundException(id));
  }

  public Tour updateTour(Long id, Tour tour) {
    System.out.println("update tour " + id);
    Tour currentTour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    currentTour.setTitle(tour.getTitle());
    currentTour.setShortDescription(tour.getShortDescription());
    currentTour.setDescription(tour.getDescription());
    currentTour.setDuration(tour.getDuration());
    currentTour.setVenue(tour.getVenue());
    currentTour.setParticipants(tour.getParticipants());
    currentTour.setPrice(tour.getPrice());
    currentTour.setImageSrc(tour.getImageSrc());
    return tourRepository.save(currentTour);
  }

  public VacantTour updateVacantTour(Long vacId, VacantTour vacantTour) {
    System.out.println("update vacant tour " + vacId);
    VacantTour currentVacantTour = vacantTourRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    currentVacantTour.setStartDate(vacantTour.getStartDate());
    currentVacantTour.setVacantPlaces(vacantTour.getVacantPlaces());
    currentVacantTour.setVacant(vacantTour.getVacant());
    return vacantTourRepository.save(currentVacantTour);
  }

  public Tour createTour(Tour tour) {
    System.out.println("create tour");
    return tourRepository.save(tour);
  }

  public ResponseEntity<?> deleteTour(Long id) {
    System.out.println("delete tour " + id);
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    tourRepository.delete(tour);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  public VacantTour createVacantTour(VacantTour vacantTour) {
    return vacantTourRepository.save(vacantTour);
  }

  public ResponseEntity<?> deleteVacantTour(Long vacId) {
    System.out.println("delete vacant tour " + vacId);
    VacantTour vacantTour = vacantTourRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    vacantTourRepository.delete(vacantTour);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  public VacantTour changeStatusVacantTour(Long vacId) {
    System.out.println("change status vacant tour " + vacId);
    VacantTour vacantTour = vacantTourRepository.findById(vacId).orElseThrow(() -> new TourNotFoundException(vacId));
    vacantTour.setVacant(!vacantTour.getVacant());
    return vacantTourRepository.save(vacantTour);
  }

  public Tour switchTourVisibility(Long id) {
    System.out.println("switch visibility for tour " + id);
    Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(id));
    tour.setVisible(!tour.getVisible());
    return tourRepository.save(tour);
  }

  private void fillDatabaseIfEmpty(){
    System.out.println("fill database");
    if (tourRepository.findAll().isEmpty()) {
      Tour tour1 = Tour.builder()
                       .title("excursion 1")
                       .shortDescription("short description")
                       .description("description")
                       .duration("a week")
                       .participants(5)
                       .venue("Winter Palace")
                       .price(1000)
//                .pictures(Arrays.asList("assets/img/nature.jpg", "assets/img/nature.jpg"))
                       .imageSrc("assets/img/nature.jpg")
                       .visible(true)
                       .build();
      Tour tour2 = Tour.builder()
                       .title("excursion 2")
                       .shortDescription("short description 2")
                       .description("description 2")
                       .duration("two weeks")
                       .participants(5)
                       .venue("Winter Palace")
                       .price(3000)
//                .pictures(Arrays.asList("assets/img/nature.jpg", "assets/img/nature.jpg"))
                       .imageSrc("assets/img/nature.jpg")
                       .visible(true)
                       .build();
      tourRepository.save(tour1);
      tourRepository.save(tour2);

      VacantTour vacantTour = VacantTour.builder()
                                        .startDate(LocalDateTime.now())
                                        .vacantPlaces(tour1.getParticipants())
                                        .vacant(true)
                                        .tour(tour1).build();
      VacantTour vacantTour1 = VacantTour.builder()
                                         .startDate(LocalDateTime.now().plusDays(1))
                                         .vacantPlaces(tour1.getParticipants())
                                         .vacant(true)
                                         .tour(tour1).build();
      VacantTour vacantTour2 = VacantTour.builder()
                                         .startDate(LocalDateTime.now().plusDays(2))
                                         .vacantPlaces(tour1.getParticipants())
                                         .vacant(false)
                                         .tour(tour1).build();

      vacantTourRepository.save(vacantTour);
      vacantTourRepository.save(vacantTour1);
      vacantTourRepository.save(vacantTour2);
    }
  }
}
