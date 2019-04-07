package com.historicalclub.service;

import com.historicalclub.entity.Tour;
import com.historicalclub.entity.VacantDate;
import com.historicalclub.error.TourNotFoundException;
import com.historicalclub.entity.Order;
import com.historicalclub.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  private final EmailService emailService;
  private final OrderRepository orderRepository;
  private final VacantDateService vacantDateService;
  private final TourService tourService;

  @Autowired
  public OrderService(EmailService emailService, OrderRepository orderRepository, VacantDateService vacantDateService, TourService tourService) {
    this.emailService = emailService;
    this.orderRepository = orderRepository;
    this.vacantDateService = vacantDateService;
    this.tourService = tourService;
  }

  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  public List<Order> getOrders(Long id) {
    return orderRepository.findAllByTourId(id);
  }

  public ResponseEntity<?> deleteOrder(Long orderId) {
    System.out.println("delete order " + orderId);
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new TourNotFoundException(orderId));
    orderRepository.delete(order);
    System.out.println("deleted");
    return ResponseEntity.ok().build();
  }

  @Transactional
  public Order createOrder(Order order) {
    Tour tour = tourService.getTourIfAvailable(order.getTourId());
    VacantDate vacantDate = vacantDateService.bookVacantDate(order.getVacantDateId());
//    emailService.sendBookingEmail(order, tour, vacantDate);
    return orderRepository.save(order);
  }

  public Order confirmOrder(Long orderId) {
    System.out.println("confirmation order " + orderId);
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new TourNotFoundException(orderId));
    order.setConfirmation(true);
    return orderRepository.save(order);
  }

  public Order cancelOrderConfirmation(Long orderId) {
    System.out.println("cancel order confirmation " + orderId);
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new TourNotFoundException(orderId));
    order.setConfirmation(false);
    return orderRepository.save(order);
  }
}
