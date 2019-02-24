package com.historicalclub.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
//  @Column(unique = true)
  private String title;

  private String description;
  private String shortDescription;
  private String duration;
  private Integer participants;

  private String venue;
  private Integer price;
//  private List<String> pictures;
  private String imageSrc;
}
