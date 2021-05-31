package com.finalproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.finalproject.backend.models.transportation.Stop;
import com.finalproject.backend.models.transportation.Ticket;
import com.finalproject.backend.models.transportation.Trip;
import com.finalproject.backend.models.transportation.TripSchedule;
import com.finalproject.backend.models.user.User;
import com.finalproject.backend.payload.request.TicketRequest;
import com.finalproject.backend.payload.request.TripRequest;
import com.finalproject.backend.payload.request.TripScheduleRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.StopRepository;
import com.finalproject.backend.repository.TicketRepository;
import com.finalproject.backend.repository.TripRepository;
import com.finalproject.backend.repository.TripScheduleRepository;
import com.finalproject.backend.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reservation")
public class TransportationReservationController {

    @Autowired
    StopRepository stopRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripScheduleRepository tripScheduleRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/stops")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllStops(){
        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stopRepository.findAll()));
	}

    @GetMapping("/tripsbystops")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getTripsByStops(@RequestParam String sourceStop, @RequestParam String destStop) {
        List<TripRequest> dataArrResult = new ArrayList<>() ;
		for(Trip dataArr : tripRepository.findTripsByStops(sourceStop, destStop)){
			dataArrResult.add(new TripRequest(dataArr.getId(), dataArr.getFare(), dataArr.getJourneyTime(), dataArr.getAgency().getId(), dataArr.getBus().getId(), dataArr.getSourceStop().getId(), dataArr.getDestStop().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<TripRequest>(true, "Success Retrieving Data", dataArrResult));
	}

    @GetMapping("/tripschedules")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getTripSchedules(@RequestParam String date) {
        List<TripScheduleRequest> dataArrResult = new ArrayList<>() ;
		for(TripSchedule dataArr : tripScheduleRepository.findTripScheduleByDate(date)){
			dataArrResult.add(new TripScheduleRequest(dataArr.getId(), dataArr.getAvailableSeats(), dataArr.getTripDate(), dataArr.getTripDetail().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<TripScheduleRequest>(true, "Success Retrieving Data", dataArrResult));
	}

    @PostMapping("/bookticket")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookTicket(@RequestBody TicketRequest ticketRequest) {
        User user = userRepository.findById(ticketRequest.getPassenger()).get();
        TripSchedule tripSchedule = tripScheduleRepository.findById(ticketRequest.getTrip_schedule()).get();
        Ticket ticket = new Ticket(ticketRequest.getSeat_number(), ticketRequest.getCancellable(), ticketRequest.getJourney_date(), user, tripSchedule);
        return ResponseEntity.ok(new MessageResponse<Ticket>(true, "Success Retrieving Data", ticketRepository.save(ticket)));
    }
}
