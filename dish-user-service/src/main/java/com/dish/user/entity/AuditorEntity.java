package com.dish.user.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditorEntity {
	
	@CreatedDate
	@Column
	private LocalDateTime createdOn;

	@CreatedBy
	@Column(length = 50)
	private String createdBy;

	@LastModifiedDate
	@Column
	private LocalDateTime updatedOn;

	@LastModifiedBy
	@Column(length = 50)
	private String updatedBy;

}