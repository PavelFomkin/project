package com.historicalclub.controller;

import com.historicalclub.entity.Order;
import com.historicalclub.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

  @Autowired
  OrderService orderService;

  @RequestMapping(value = "/orders", method = RequestMethod.GET)
  public List<Order> getOrders() {
    return orderService.getOrders();
  }

  @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
  public List<Order> getOrdersByTourId(@PathVariable Long id) {
    return orderService.getOrders(id);
  }

  @RequestMapping(value = "/confirmation/{id}", method = RequestMethod.GET)
  public Order confirmOrder(@PathVariable Long id) {
    return orderService.confirmOrder(id);
  }

  @RequestMapping(value = "/cancel-confirmation/{id}", method = RequestMethod.GET)
  public Order cancelOrderConfirmation(@PathVariable Long id) {
    return orderService.cancelOrderConfirmation(id);
  }

//  @RequestMapping(value = "/switch-tour-visibility/{id}", method = RequestMethod.GET)
//  public Tour switchTourVisibility(@PathVariable Long id) {
//    return tourService.switchTourVisibility(id);
//  }
//
//  @RequestMapping(value = "/tour/{id}", method = RequestMethod.GET)
//  public Tour getTour(@PathVariable Long id) {
//    return tourService.getTour(id);
//  }
//
  @RequestMapping(value = "/create-order", method = RequestMethod.POST)
  public Order createOrder(@Valid @RequestBody Order order) {
    return orderService.createOrder(order);
  }
//
//  @RequestMapping(value = "/update-tour/{id}", method = RequestMethod.PUT)
//  public Tour updateTour(@PathVariable Long id, @Valid @RequestBody Tour tour) {
//    return tourService.updateTour(id, tour);
//  }

  @RequestMapping(value = "/delete-order/{orderId}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
    return orderService.deleteOrder(orderId);
  }
}
