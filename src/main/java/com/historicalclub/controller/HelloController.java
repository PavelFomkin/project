package com.historicalclub.controller;

import com.historicalclub.TourNotFoundException;
import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacationTour;
import com.historicalclub.repository.TourRepository;
import com.historicalclub.repository.VacationTourRepository;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    VacationTourRepository vacationTourRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/tours/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<VacationTour> getVacationTour(@PathVariable Long id) {
        System.out.println("show vacations");
        List<VacationTour> vacationTours = new ArrayList<>();

        Optional<Tour> optTour = tourRepository.findById(id);
        if (optTour.isPresent()) {
            Tour tour = optTour.get();
            VacationTour vacationTour = VacationTour.builder()
                    .id(1L)
                    .startDate(LocalDateTime.now())
                    .vacationPlaces(tour.getParticipants())
                    .vacation(true)
                    .tour(tour).build();
            VacationTour vacationTour1 = VacationTour.builder()
                    .id(2L)
                    .startDate(LocalDateTime.now().plusDays(1))
                    .vacationPlaces(tour.getParticipants())
                    .vacation(true)
                    .tour(tour).build();
            vacationTours.add(vacationTour);
            vacationTours.add(vacationTour1);
        }

//        vacationTourRepository.save(vacationTour);
//        vacationTourRepository.save(vacationTour1);

        return id == 1 ? vacationTours : new ArrayList<>();
    }

    @RequestMapping("/tours")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Tour> getTours() {
        System.out.println("show tours");
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
                .build();
        if (!tourRepository.existsById(1L)) {
            tourRepository.save(tour1);
            tourRepository.save(tour2);
        }

        return tourRepository.findAll();
    }

    @RequestMapping("/tour/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Tour getTour(@PathVariable Long id, HttpServletResponse response) {
        System.out.println("show tour number "+id);
        Optional<Tour> tour = tourRepository.findById(id);
        return tour.orElseThrow(() -> new TourNotFoundException(id));
    }

}
