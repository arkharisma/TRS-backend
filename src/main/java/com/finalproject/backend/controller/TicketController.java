package com.finalproject.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Ticket;
import com.finalproject.backend.models.transportation.TripSchedule;
import com.finalproject.backend.models.user.User;
import com.finalproject.backend.payload.request.TicketRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.TicketRepository;
import com.finalproject.backend.repository.TripScheduleRepository;
import com.finalproject.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TripScheduleRepository tripScheduleRepository;
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		List<TicketRequest> dataArrResult = new ArrayList<>() ;
		for(Ticket dataArr : ticketRepository.findAll()){
			dataArrResult.add(new TicketRequest(dataArr.getId(), dataArr.getCancellable(), dataArr.getJourneyDate(), dataArr.getSeatNumber(), dataArr.getPassenger().getId(), dataArr.getTripSchedule().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<TicketRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTicket(@Valid @RequestBody TicketRequest ticketRequest) {
		User user = userRepository.findById(ticketRequest.getPassenger()).get();
		TripSchedule tripSchedule = tripScheduleRepository.findById(ticketRequest.getTrip_schedule()).get();
		Ticket ticket = new Ticket(ticketRequest.getSeat_number(), ticketRequest.getCancellable(), ticketRequest.getJourney_date(), user, tripSchedule);
        return ResponseEntity.ok(new MessageResponse<Ticket>(true, "Success Adding Data", ticketRepository.save(ticket)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTicket(@PathVariable(value="id") String id, @Valid @RequestBody TicketRequest ticketDetail){
		User user = userRepository.findById(ticketDetail.getPassenger()).get();
		TripSchedule tripSchedule = tripScheduleRepository.findById(ticketDetail.getTrip_schedule()).get();
		Ticket ticket = ticketRepository.findById(id).get();
		if(ticket == null) {
			return ResponseEntity.notFound().build();
		}
        ticket.setCancellable(ticketDetail.getCancellable());
        ticket.setJourneyDate(ticketDetail.getJourney_date());
        ticket.setSeatNumber(ticketDetail.getSeat_number());
        ticket.setPassenger(user);
        ticket.setTripSchedule(tripSchedule);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return ResponseEntity.ok(new MessageResponse<Ticket>(true, "Success Updating Data", updatedTicket));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTicket(@PathVariable(value="id") String id) {
		String result = "";
		try {
            ticketRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            ticketRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse<Ticket>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<Ticket>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTicketById(@PathVariable(value="id") String id){
        Ticket ticket = ticketRepository.findById(id).get();
		if(ticket == null) {
			return ResponseEntity.notFound().build();
		} else {
			TicketRequest dataResult = new TicketRequest(ticket.getId(), ticket.getCancellable(), ticket.getJourneyDate(), ticket.getSeatNumber(), ticket.getPassenger().getId(), ticket.getTripSchedule().getId());
            return ResponseEntity.ok(new MessageResponse<TicketRequest>(true, "Success Retrieving Data", dataResult));
		}
	}
}
