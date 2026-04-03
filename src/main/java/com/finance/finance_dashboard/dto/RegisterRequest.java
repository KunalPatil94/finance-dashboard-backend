package com.finance.finance_dashboard.dto;

import com.finance.finance_dashboard.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {

	private String name;
	private String email;
	private String password;
	private Role role;

}
