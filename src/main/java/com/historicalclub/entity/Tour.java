package com.historicalclub.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tours")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tour {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(unique = true)
  private String title;
  @Column(length=1000)
  private String shortDescription;
  @Lob
  @Column
  private String description;
  private String duration;
  private Integer participants;
  private String venue;
  private Integer price;
  private Boolean visible;
  private String imageUrl;
  private LocalTime minStartTime;
  private LocalTime maxStartTime;

  @ElementCollection
  @CollectionTable(name = "disabledDates")
  private List<LocalDate> disabledDates;

  @ElementCollection
  @CollectionTable(name = "disabledDaysOfWeek")
  private List<Integer> disabledDaysOfWeek;

  @ElementCollection
  @CollectionTable(name = "pictures")
  private List<String> pictures;
}
