package com.blinnproject.myworkdayback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Training extends BaseEntityAudit {

  @Column(nullable = false)
  private String name;

  private String sportPreset;

  private DayOfWeek[] trainingDays;

  private String description;

  private Boolean hasWarpUp;

  private Boolean hasStretching;

  @OneToMany(mappedBy = "training")
  Set<TrainingExercises> trainingExercises;

  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = true)
  @JsonIgnore
  private User user;

}
