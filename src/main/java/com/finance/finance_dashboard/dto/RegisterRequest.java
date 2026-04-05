package com.finance.finance_dashboard.dto;

import com.finance.finance_dashboard.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

	private String name;
	@Email(message = "Invalid email format")
	@NotBlank
	private String email;
	@Pattern(
			 regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
			 message = "Password must contain uppercase, lowercase, number and be 8+ characters"
			)
			private String password;
	private Role role;

}
