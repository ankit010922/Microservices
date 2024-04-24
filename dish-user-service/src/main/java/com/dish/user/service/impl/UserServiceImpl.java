package com.dish.user.service.impl;

import static com.dish.user.constant.UserConstants.REQUEST_ID;

import com.dish.user.fiegnclients.resolvers.AuthServiceClientResolver;
import com.dish.user.fiegnclients.resolvers.RoleServClientRespResolver;
import com.dish.user.request.dto.AuthDto;
import com.dish.user.request.dto.RoleDto;
import com.dish.user.response.APIResponse;
import com.dish.user.utills.ApiResponseUtil;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dish.user.config.NotificationEventProducer;
import com.dish.user.entity.User;
import com.dish.user.exception.DuplicateException;
import com.dish.user.form.RegisterUserForm;
import com.dish.user.repository.UserRepository;
import com.dish.user.response.UserResponse;
import com.dish.user.service.UserService;
import com.dish.user.utills.GenerateUUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownHostException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final NotificationEventProducer notificationEventProducer;

	private final RoleServClientRespResolver roleServClientRespResolver;
	private final AuthServiceClientResolver authServiceClientResolver;
	private final ApiResponseUtil apiResponseUtil;

	@Transactional
	public APIResponse registerUser(RegisterUserForm registerUserForm) {
		log.info("********************* Register User *********************");
		String requestId = GenerateUUID.generateUUID();
		log.debug(REQUEST_ID, requestId);

		try {
			User user = User.convertToUser(registerUserForm);
			Optional<Object> roleDetail = roleServClientRespResolver.getRoleDto(registerUserForm.getRoleId());

			if (!roleDetail.isPresent()) {
				return apiResponseUtil.buildErrorResponse(requestId, "Invalid Role ID");
			}

			Optional<APIResponse> response = authServiceClientResolver.saveAuth(new AuthDto(registerUserForm.getEmail(), registerUserForm.getPassword()));

			if (!response.isPresent() || !response.get().getStatus()) {
				return apiResponseUtil.buildErrorResponse(requestId, response.get().getMessage());
			}

			userRepository.save(user);
//			notificationEventProducer.sendMessage(new EmailNotificationRequest(registerUserForm.getEmail()));

			return apiResponseUtil.buildSuccessResponse(requestId, "User registered successfully");

		}
		catch(RetryableException ex){
			return apiResponseUtil.buildErrorResponse(requestId,"Failed to access feign client"+ex.getMessage());
		}
		catch (DataIntegrityViolationException e) {
			throw new DuplicateException("User Already Exist : " + registerUserForm.getName());
		} catch (Exception e) {
			log.error("Exception occurred while registering user: {}", e);
			return apiResponseUtil.buildErrorResponse(requestId, "Failed to register user");
		}
	}
	@Override
	public APIResponse getUserDetails(Long id) {
		log.info("********************* Fetching User *********************");
		String requestId = GenerateUUID.generateUUID();
		log.debug(REQUEST_ID, requestId);
		try {
			 UserResponse userResponse = new UserResponse();
			 User users =  userRepository.findById(id).get();
			 BeanUtils.copyProperties(users, userResponse);
//			 userResponse.setRoleCode(users.getRole().getRoleCode());
//			 userResponse.setRoleId(users.getRole().getRoleId());
			return apiResponseUtil.buildSuccessResponse(requestId,"User registered successfully");
		}catch (Exception e) {
			log.error("Exception occured while fetching user details: {}", e);
			return apiResponseUtil.buildErrorResponse(requestId,"Failed to fetch user details");
		}
	}

	
}
