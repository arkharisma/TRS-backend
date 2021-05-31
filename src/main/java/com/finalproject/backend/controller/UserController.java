package com.finalproject.backend.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.backend.models.user.ERole;
import com.finalproject.backend.models.user.Role;
import com.finalproject.backend.models.user.User;
import com.finalproject.backend.payload.request.SignupRequest;
import com.finalproject.backend.payload.response.MessageResponse;
import com.finalproject.backend.repository.RoleRepository;
import com.finalproject.backend.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
	UserRepository userRepository;

    @Autowired
	RoleRepository roleRepository;

    @Autowired
	PasswordEncoder encoder;

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
}
