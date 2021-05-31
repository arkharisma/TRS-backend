package com.finalproject.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Trip;
import com.finalproject.backend.models.transportation.TripSchedule;
import com.finalproject.backend.payload.request.TripScheduleRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.TripRepository;
import com.finalproject.backend.repository.TripScheduleRepository;

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
@RequestMapping("/api/v1/tripSchedule")
public class TripScheduleController {
    @Autowired
	TripScheduleRepository tripScheduleRepository;

	@Autowired
	TripRepository tripRepository;
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		List<TripScheduleRequest> dataArrResult = new ArrayList<>() ;
		for(TripSchedule dataArr : tripScheduleRepository.findAll()){
			dataArrResult.add(new TripScheduleRequest(dataArr.getId(), dataArr.getAvailableSeats(), dataArr.getTripDate(), dataArr.getTripDetail().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<TripScheduleRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripScheduleRequest tripScheduleRequest) {
		Trip trip = tripRepository.findById(tripScheduleRequest.getTrip_detail()).get();
		TripSchedule tripSchedule = new TripSchedule(tripScheduleRequest.getTrip_date(), tripScheduleRequest.getAvailable_seats(), trip);
        return ResponseEntity.ok(new MessageResponse<TripSchedule>(true, "Success Adding Data", tripScheduleRepository.save(tripSchedule)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTrip(@PathVariable(value="id") String id, @Valid @RequestBody TripScheduleRequest tripScheduleDetail){
		Trip trip = tripRepository.findById(tripScheduleDetail.getTrip_detail()).get();
		TripSchedule tripSchedule = tripScheduleRepository.findById(id).get();
		if(tripSchedule == null) {
			return ResponseEntity.notFound().build();
		}
        tripSchedule.setAvailableSeats(tripScheduleDetail.getAvailable_seats());
        tripSchedule.setTripDate(tripScheduleDetail.getTrip_date());
        tripSchedule.setTripDetail(trip);

        TripSchedule updatedTripSchedule = tripScheduleRepository.save(tripSchedule);

        return ResponseEntity.ok(new MessageResponse<TripSchedule>(true, "Success Updating Data", updatedTripSchedule));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTrip(@PathVariable(value="id") String id) {
		String result = "";
		try {
            tripScheduleRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            tripScheduleRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse<TripSchedule>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<TripSchedule>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripById(@PathVariable(value="id") String id){
        TripSchedule tripSchedule = tripScheduleRepository.findById(id).get();
		if(tripSchedule == null) {
			return ResponseEntity.notFound().build();
		} else {
			TripScheduleRequest dataResult = new TripScheduleRequest(tripSchedule.getId(), tripSchedule.getAvailableSeats(), tripSchedule.getTripDate(), tripSchedule.getTripDetail().getId());
            return ResponseEntity.ok(new MessageResponse<TripScheduleRequest>(true, "Success Retrieving Data", dataResult));
		}
	}
}
