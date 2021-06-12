package com.finalproject.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Agency;
import com.finalproject.backend.models.transportation.Bus;
import com.finalproject.backend.payload.request.BusRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.AgencyRepository;
import com.finalproject.backend.repository.BusRepository;
import com.finalproject.backend.service.TokenHolder;

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

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
	BusRepository busRepository;

	@Autowired
	AgencyRepository agencyRepository;

	TokenHolder tokenHolder = new TokenHolder();
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		List<BusRequest> dataArrResult = new ArrayList<>() ;
		for(Bus dataArr : busRepository.findAll()){
			dataArrResult.add(new BusRequest(dataArr.getId(), dataArr.getCapacity(), dataArr.getCode(), dataArr.getMake(), dataArr.getAgency().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<BusRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBus(@Valid @RequestBody BusRequest busRequest) {
		Agency agency = agencyRepository.findById(busRequest.getAgency()).get();
		Bus bus = new Bus(busRequest.getCode(), busRequest.getCapacity(), busRequest.getMake(), agency);
        return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Adding Data", busRepository.save(bus)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBus(@PathVariable(value="id") String id, @Valid @RequestBody BusRequest busDetail){
		Agency agency = agencyRepository.findById(busDetail.getAgency()).get();
		Bus bus = busRepository.findById(id).get();
		if(bus == null) {
			return ResponseEntity.notFound().build();
		}
        bus.setCapacity(busDetail.getCapacity());
        bus.setCode(busDetail.getCode());
        bus.setMake(busDetail.getMake());
        bus.setAgency(agency);

        Bus updatedBus = busRepository.save(bus);

        return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Updating Data", updatedBus));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteBus(@PathVariable(value="id") String id) {
		String result = "";
		try {
            busRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            busRepository.deleteById(id);
			
            return ResponseEntity.ok(new MessageResponse<Bus>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<Bus>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getBusById(@PathVariable(value="id") String id){
		Bus bus = busRepository.findById(id).get();
		if(bus == null) {
			return ResponseEntity.notFound().build();
		} else {
			BusRequest dataResult = new BusRequest(bus.getId(), bus.getCapacity(), bus.getCode(), bus.getMake(), bus.getAgency().getId());
            return ResponseEntity.ok(new MessageResponse<BusRequest>(true, "Success Retrieving Data", dataResult));
		}
	}

	@GetMapping("/list/user")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
	public ResponseEntity<?> getBusByOwnerId(){
		List<BusRequest> dataArrResult = new ArrayList<>();
		String id_agency = agencyRepository.findByOwnerId(tokenHolder.getIdUserFromToken()).getId();
		// List<Bus> busArr = busRepository.findByAgencyId(id_agency);
		for(Bus dataArr : busRepository.findByAgencyId(id_agency)){
			dataArrResult.add(new BusRequest(dataArr.getId(), dataArr.getCapacity(), dataArr.getCode(), dataArr.getMake(), dataArr.getAgency().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<BusRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/add/user")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBusByUser(@Valid @RequestBody BusRequest busRequest) {
		Agency agency = agencyRepository.findByOwnerId(tokenHolder.getIdUserFromToken());
		Bus bus = new Bus(busRequest.getCode(), busRequest.getCapacity(), busRequest.getMake(), agency);
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Adding Data", busRepository.save(bus)));
	}
}
