package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.backend.models.transportation.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
}
