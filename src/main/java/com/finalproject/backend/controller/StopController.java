package com.finalproject.backend.controller;

import javax.validation.Valid;

import com.finalproject.backend.models.transportation.Stop;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.StopRepository;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/stop")
public class StopController {
    @Autowired
	StopRepository stopRepository;
	
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stopRepository.findAll()));
	}
	
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addStop(@Valid @RequestBody Stop stop) {
        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Adding Data", stopRepository.save(stop)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateStop(@PathVariable(value="id") String id, @Valid @RequestBody Stop stopDetail){
		Stop stop = stopRepository.findById(id).get();
		if(stop == null) {
			return ResponseEntity.notFound().build();
		}
        stop.setCode(stopDetail.getCode());
        stop.setName(stopDetail.getName());
        stop.setDetail(stopDetail.getDetail());

        Stop updatedStop = stopRepository.save(stop);

        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Updating Data", updatedStop));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteStop(@PathVariable(value="id") String id) {
		String result = "";
		try {
            stopRepository.findById(id).get();
			
			result = "Success Deleting Data with Id: " + id;
            stopRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse<Stop>(true, result));
		} catch(Exception e) {
			result = "Data with Id: " + id + " Not Found";
            return ResponseEntity.ok(new MessageResponse<Stop>(false, result));
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getStopById(@PathVariable(value="id") String id){
        Stop stop = stopRepository.findById(id).get();
		if(stop == null) {
			return ResponseEntity.notFound().build();
		} else {
            return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stop));
		}
	}
	
	@GetMapping("/code")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getStopByCode (@RequestParam(value="code") String code){
        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stopRepository.findByCode(code)));
	}
	
	@GetMapping("/name")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getStopByName(@RequestParam(value="name") String name){
        return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stopRepository.findByName(name)));
	}
}
