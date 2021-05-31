package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.finalproject.backend.models.transportation.TripSchedule;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, String> {
    List<TripSchedule> findByTripDate(String tripDate);

    @Query(value = "SELECT DISTINCT * FROM trip_schedule WHERE trip_date = :tripDate", nativeQuery = true)
    List<TripSchedule> findTripScheduleByDate(String tripDate);
}