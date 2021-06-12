package com.finalproject.backend.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.models.transportation.Agency;
import com.finalproject.backend.models.user.ERole;
import com.finalproject.backend.models.user.Role;
import com.finalproject.backend.models.user.User;
import com.finalproject.backend.payload.request.ChangePasswordRequest;
import com.finalproject.backend.payload.request.SignupAgencyRequest;
import com.finalproject.backend.payload.request.SignupRequest;
import com.finalproject.backend.payload.request.UserRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.payload.response.UserResponse;
import com.finalproject.backend.repository.AgencyRepository;
import com.finalproject.backend.repository.RoleRepository;
import com.finalproject.backend.repository.UserRepository;
import com.finalproject.backend.service.TokenHolder;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
	UserRepository userRepository;

    @Autowired
	RoleRepository roleRepository;

	@Autowired
	AgencyRepository agencyRepository;

    @Autowired
	PasswordEncoder encoder;

	TokenHolder tokenHolder = new TokenHolder();

    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse<>(false, "Error: Email is already taken!", null));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFirstName(),
							 signUpRequest.getLastName(),
							 signUpRequest.getMobileNumber());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse<>(true, "User registered successfully!", null));
	}

	@PostMapping("/agency/signup")
	public ResponseEntity<?> registerAgency(@Valid @RequestBody SignupAgencyRequest signUpAgencyRequest) {
		if (userRepository.existsByUsername(signUpAgencyRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse<>(false, "Error: Email is already taken!", null));
		}

		// Create new user's account
		User user = new User(signUpAgencyRequest.getUsername(),
							encoder.encode(signUpAgencyRequest.getPassword()),
							 signUpAgencyRequest.getFirstName(),
							 signUpAgencyRequest.getLastName(),
							 signUpAgencyRequest.getMobileNumber());

		Set<String> strRoles = signUpAgencyRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "agency":
					Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(moderatorRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		Agency agency = new Agency(signUpAgencyRequest.getCode(), signUpAgencyRequest.getName(), signUpAgencyRequest.getDetails(), user);

		agencyRepository.save(agency);

		return ResponseEntity.ok(new MessageResponse<>(true, "User Agency registered successfully!", null));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable(value="id") String id){
        User user = userRepository.findById(id).get();
		if(user == null) {
			return ResponseEntity.notFound().build();
		} else {
			UserResponse dataResult = new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getMobileNumber());
            return ResponseEntity.ok(new MessageResponse<UserResponse>(true, "Success Retrieving Data", dataResult));
		}
	}

	@GetMapping("/user")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(){
        User user = userRepository.findById(tokenHolder.getIdUserFromToken()).get();
		if(user == null) {
			return ResponseEntity.notFound().build();
		} else {
			UserResponse dataResult = new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getMobileNumber());
            return ResponseEntity.ok(new MessageResponse<UserResponse>(true, "Success Retrieving Data", dataResult));
		}
	}

	@PutMapping("/")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userDetail){
		User user = userRepository.findById(tokenHolder.getIdUserFromToken()).get();
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
        user.setFirstName(userDetail.getFirst_name());
		user.setLastName(userDetail.getLast_name());
		user.setMobileNumber(userDetail.getMobile_number());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse<User>(true, "Success Updating Data", updatedUser));
	}

	@PutMapping("/changepassword")
	@ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest passwordRequest){
		User user = userRepository.findById(tokenHolder.getIdUserFromToken()).get();
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
        user.setPassword(encoder.encode(passwordRequest.getPassword()));

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse<User>(true, "Success Change Password", updatedUser));
	}
}
