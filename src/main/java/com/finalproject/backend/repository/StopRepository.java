package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.finalproject.backend.models.transportation.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {
	List<Stop> findByName (String name);
	List<Stop> findByCode (String code);
}
