package com.historicalclub.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String email;
    String phoneNumber;
    Integer participants;
    String comment;

    LocalDateTime bookingDate;
    Boolean confirmation;

    Long tourId;
    LocalDateTime date;
}
