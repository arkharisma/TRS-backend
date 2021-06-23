package com.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.finalproject.backend.models.transportation.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByPassengerId(String passenger_id);
}
