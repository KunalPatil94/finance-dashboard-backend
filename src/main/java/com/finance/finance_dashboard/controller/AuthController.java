package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.LoginRequest;
import com.finance.finance_dashboard.dto.LoginResponse;
import com.finance.finance_dashboard.dto.RegisterRequest;
import com.finance.finance_dashboard.dto.UserResponse;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.repo.UserRepository;
import com.finance.finance_dashboard.security.JwtService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthController(UserRepository userRepository, JwtService jwtService) {

		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	@PostMapping("/register")
	public UserResponse register(@Valid @RequestBody RegisterRequest request) {

		User user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();

		User savedUser = userRepository.save(user);

	    return new UserResponse(
	            savedUser.getId(),
	            savedUser.getName(),
	            savedUser.getEmail());
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {

	    User user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    String token = jwtService.generateToken(user.getEmail());

	    return new LoginResponse(token);
	}

}