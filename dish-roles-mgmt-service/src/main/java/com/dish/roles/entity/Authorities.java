package com.dish.roles.entity;

import com.dish.roles.enums.AccessType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "authorities_table")
public class Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authorityId;
	private String serviceName;
	private String controllerUrl;
	private AccessType accessType;

}


