package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.models.transportation.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, String> {
}
