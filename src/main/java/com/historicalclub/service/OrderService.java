package com.historicalclub.service;

import com.historicalclub.error.TourNotFoundException;
import com.historicalclub.entity.Order;
import com.historicalclub.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private VacantDateService vacantDateService;

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

  public Order createOrder(Order order) {
    vacantDateService.bookVacantDate(order.getVacantDateId());
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
