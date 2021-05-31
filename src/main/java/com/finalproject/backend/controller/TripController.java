package com.finalproject.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Agency;
import com.finalproject.backend.models.transportation.Bus;
import com.finalproject.backend.models.transportation.Stop;
import com.finalproject.backend.models.transportation.Trip;
import com.finalproject.backend.payload.request.TripRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.AgencyRepository;
import com.finalproject.backend.repository.BusRepository;
import com.finalproject.backend.repository.StopRepository;
import com.finalproject.backend.repository.TripRepository;

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
@RequestMapping("/api/v1/trip")
public class TripController {
    @Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		List<TripRequest> dataArrResult = new ArrayList<>() ;
		for(Trip dataArr : tripRepository.findAll()){
			dataArrResult.add(new TripRequest(dataArr.getId(), dataArr.getFare(), dataArr.getJourneyTime(), dataArr.getAgency().getId(), dataArr.getBus().getId(), dataArr.getSourceStop().getId(), dataArr.getDestStop().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<TripRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {
		Agency agency = agencyRepository.findById(tripRequest.getAgency()).get();
		Bus bus = busRepository.findById(tripRequest.getBus()).get();
		Stop sourceStop = stopRepository.findById(tripRequest.getSourceStop()).get();
		Stop destStop = stopRepository.findById(tripRequest.getDestStop()).get();
		Trip trip  = new Trip(tripRequest.getFare(), tripRequest.getJourney_time(), sourceStop, destStop, bus, agency);
        return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Adding Data", tripRepository.save(trip)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTrip(@PathVariable(value="id") String id, @Valid @RequestBody TripRequest tripDetail){
		Agency agency = agencyRepository.findById(tripDetail.getAgency()).get();
		Bus bus = busRepository.findById(tripDetail.getBus()).get();
		Stop sourceStop = stopRepository.findById(tripDetail.getSourceStop()).get();
		Stop destStop = stopRepository.findById(tripDetail.getDestStop()).get();
		Trip trip = tripRepository.findById(id).get();
		if(trip == null) {
			return ResponseEntity.notFound().build();
		}
        trip.setFare(tripDetail.getFare());
        trip.setJourneyTime(tripDetail.getJourney_time());
        trip.setAgency(agency);
        trip.setBus(bus);
        trip.setDestStop(destStop);
        trip.setSourceStop(sourceStop);

        Trip updatedTrip = tripRepository.save(trip);

        return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Updating Data", updatedTrip));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTrip(@PathVariable(value="id") String id) {
		String result = "";
		try {
            tripRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            tripRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse<Trip>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<Trip>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripById(@PathVariable(value="id") String id){
        Trip trip = tripRepository.findById(id).get();
		if(trip == null) {
			return ResponseEntity.notFound().build();
		} else {
			TripRequest dataResult = new TripRequest(trip.getId(), trip.getFare(), trip.getJourneyTime(), trip.getAgency().getId(), trip.getBus().getId(), trip.getSourceStop().getId(), trip.getDestStop().getId());
            return ResponseEntity.ok(new MessageResponse<TripRequest>(true, "Success Retrieving Data", dataResult));
		}
	}
}
