package com.finance.finance_dashboard.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false,unique=true)
	private String email;

	@Column(nullable=false)
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	private boolean active = true;

	private LocalDateTime createdAt = LocalDateTime.now();
}
