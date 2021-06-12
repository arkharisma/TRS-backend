package com.finalproject.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Agency;
import com.finalproject.backend.models.user.User;
import com.finalproject.backend.payload.request.AgencyRequest;
import com.finalproject.backend.payload.request.UpdateAgencyRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.AgencyRepository;
import com.finalproject.backend.repository.UserRepository;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/agency")
public class AgencyController {
    @Autowired
	AgencyRepository agencyRepository;

	@Autowired
	UserRepository userRepository;

	TokenHolder tokenHolder = new TokenHolder();
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		List<AgencyRequest> dataArrResult = new ArrayList<>() ;
		for(Agency dataArr : agencyRepository.findAll()){
			dataArrResult.add(new AgencyRequest(dataArr.getId(), dataArr.getCode(), dataArr.getName(), dataArr.getDetails(), dataArr.getOwner().getId()));
		}
        return ResponseEntity.ok(new MessageResponse<AgencyRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAgency(@Valid @RequestBody AgencyRequest agencyRequest) {
		User user = userRepository.findById(agencyRequest.getOwner()).get();
		Agency agency = new Agency(agencyRequest.getCode(), agencyRequest.getName(), agencyRequest.getDetails(), user);
        return ResponseEntity.ok(new MessageResponse<Agency>(true, "Success Adding Data", agencyRepository.save(agency)));
	}
	
	@PutMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateAgency(@Valid @RequestBody UpdateAgencyRequest agencyDetail){
		Agency agency = agencyRepository.findByOwnerId(tokenHolder.getIdUserFromToken());
		if(agency == null) {
			return ResponseEntity.notFound().build();
		}
        agency.setDetails(agencyDetail.getDetails());
        agency.setName(agencyDetail.getName());

        Agency updatedAgency = agencyRepository.save(agency);

        return ResponseEntity.ok(new MessageResponse<Agency>(true, "Success Updating Data", updatedAgency));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAgency(@PathVariable(value="id") String id) {
		String result = "";
		try {
            agencyRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            agencyRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse<Agency>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<Agency>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAgencyById(@PathVariable(value="id") String id){
        Agency agency = agencyRepository.findById(id).get();
		if(agency == null) {
			return ResponseEntity.notFound().build();
		} else {
			AgencyRequest dataResult = new AgencyRequest(agency.getId(), agency.getCode(), agency.getName(), agency.getDetails(), agency.getOwner().getId());
            return ResponseEntity.ok(new MessageResponse<AgencyRequest>(true, "Success Retrieving Data", dataResult));
		}
	}

	@GetMapping("/user")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAgencyByUserId(){
        Agency agency = agencyRepository.findByOwnerId(tokenHolder.getIdUserFromToken());
		if(agency == null) {
			return ResponseEntity.notFound().build();
		} else {
			AgencyRequest dataResult = new AgencyRequest(agency.getId(), agency.getCode(), agency.getName(), agency.getDetails(), tokenHolder.getIdUserFromToken());
            return ResponseEntity.ok(new MessageResponse<AgencyRequest>(true, "Success Retrieving Data", dataResult));
		}
	}
}
