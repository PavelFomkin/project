package com.historicalclub.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@Table(name = "vacation_tours")
public class VacationTour {
  @Id
  @GeneratedValue
  Long id;
  LocalDateTime startTime;
  Integer numberOfPeople;
  Boolean vacation;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tours")
  @OnDelete(action = OnDeleteAction.CASCADE)
  Tour tour;
}
