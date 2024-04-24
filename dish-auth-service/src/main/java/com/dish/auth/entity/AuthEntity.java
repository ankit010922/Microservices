package com.dish.auth.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "auth", uniqueConstraints = { @UniqueConstraint(columnNames = { "email"}) })
public class AuthEntity extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "auth_id")
	private long authId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

}