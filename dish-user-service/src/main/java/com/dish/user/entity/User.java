package com.dish.user.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import com.dish.user.form.RegisterUserForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends AuditorEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false, unique = true)
    private String email;

	@Column(nullable = false)
	private Boolean isApproved;

	@Column(nullable = false)
	private Boolean isActive;

	@Column(nullable = false)
	private Boolean isValidated;
	
	@Column
	private Long roleId;
	
	@Column(nullable = false)
	private Boolean termsAndCondition;
		
	@Column
	private Long ActivatedBy;
	
	public static User convertToUser(RegisterUserForm registerUserForm) {
		var user = new User();
        user.setUserName(registerUserForm.getName());
        user.setEmail(registerUserForm.getEmail());
        user.setIsActive(false);
       	user.setIsApproved(false);
       	user.setIsValidated(false);
        user.setTermsAndCondition(registerUserForm.getTermsAndCondition());
		return user;
	}
	
}
