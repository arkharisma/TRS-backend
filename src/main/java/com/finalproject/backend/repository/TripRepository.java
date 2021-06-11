package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.finalproject.backend.models.transportation.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {
    @Query(value = "SELECT DISTINCT * FROM trip WHERE source_stop_id = :sourceStop AND dest_stop_id = :destStop", nativeQuery = true)
    List<Trip> findTripsByStops(String sourceStop, String destStop);

    List<Trip> findByAgencyId(String agency_id);
}