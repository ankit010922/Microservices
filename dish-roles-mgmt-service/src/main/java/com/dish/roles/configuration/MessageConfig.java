package com.dish.roles.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.val;


@Component
@PropertySource("classpath:messages_en.properties")
public class MessageConfig {
	@Value("${role.save.success}")
	public String roleSaveSuccessMessage;

	@Value("${role.save.duplicate}")
	public String roleSaveDuplicateMessage;
	
	@Value("${role.save.failure}")
	public String roleSaveFailureMessage;
	

	@Value("${role.get.roles.success}")
	public String successFullyFetchedAllRoles; 
	
	@Value("${role.get.roles.failure}")
	public String failedFetchedAllRoles; 
	
	@Value("${admin.contact}")
	public String adminContact;

	@Value("${role.get.byid.success}")
	public String successfullyFetchedRoleById;

	@Value("${role.get.byid.notfound}")
	public String roleByIdNotFound;
}
