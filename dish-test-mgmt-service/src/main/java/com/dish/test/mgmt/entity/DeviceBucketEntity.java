package com.dish.test.mgmt.entity;

import org.hibernate.annotations.GenericGenerator;

import com.dish.test.mgmt.constants.TestManagementEntityPrefixConstants;
import com.dish.test.mgmt.entity.util.Auditable;
import com.dish.test.mgmt.entity.util.IdentifiableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "device_bucket")
public class DeviceBucketEntity extends Auditable<String> implements IdentifiableEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "com.dish.test.mgmt.entity.util.CustomIdGenerator")
	private String id;

	@Override
	public String getIdPrefix() {
		return TestManagementEntityPrefixConstants.DEVICE_BUCKET_PREFIX;
	}
	
	@Column(name = "bucket_name")
	private String bucketName;
	

}
