package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.finalproject.backend.models.transportation.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, String> {
    List<Bus> findByAgencyId(String agency_id);
}
