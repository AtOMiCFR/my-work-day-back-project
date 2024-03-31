package com.blinnproject.myworkdayback.repository;

import com.blinnproject.myworkdayback.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  Optional<Schedule> findByIdAndCreatedBy(Long scheduleId, Long createdBy);
}
