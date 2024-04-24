package com.dish.roles.entity;

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
@Table(name = "role_table", uniqueConstraints = { @UniqueConstraint(columnNames = { "role_code"}) })
public class RoleEntity extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "role_id")
	private long roleId;
	
	@Column(name = "role_code")
	private String roleCode;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_description")
	private String roleDescription;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_roleAuthority",referencedColumnName = "role_id")
	private List<Authorities> authorities;
	

}
