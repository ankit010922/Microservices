package com.dish.roles.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import com.dish.roles.configuration.MessageConfig;
import com.dish.roles.constants.Constants;
import com.dish.roles.dto.RoleDto;
import com.dish.roles.entity.Authorities;
import com.dish.roles.entity.RoleEntity;
import com.dish.roles.response.ApiResponse;
import com.dish.roles.respository.RolesRepository;
import com.dish.roles.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


	private final RolesRepository roleRepo;

private final	MessageConfig messageConfig;

	@Override
	public ApiResponse saveRole(RoleDto role, BindingResult bindingResult) {
		log.debug("*****************Save Role******************");
		String uuid = UUID.randomUUID().toString();
		log.debug(Constants.REQUEST_ID, uuid);
		try {
			if (bindingResult.hasErrors()) {
				StringBuilder errorMessage = new StringBuilder();
				bindingResult.getAllErrors().forEach(error -> {
					errorMessage.append(error.getDefaultMessage()).append("; ");
				});
				return ApiResponse.builder().data(null).status(Boolean.FALSE).HTTPstatusCode(HttpStatus.BAD_REQUEST)
						.message(errorMessage.toString()).requestId(uuid).build();

			}
			RoleEntity roleEntity = new RoleEntity();
			BeanUtils.copyProperties(role, roleEntity);
			if (!CollectionUtils.isEmpty(role.getAuthorities())) {
				List<Authorities> authorityEntities = role.getAuthorities().stream().map(authorityDto -> {
					Authorities authorityEntity = new Authorities();
					BeanUtils.copyProperties(authorityDto, authorityEntity);
					return authorityEntity;
				}).collect(Collectors.toList());
				roleEntity.setAuthorities(authorityEntities);
			}
			roleRepo.save(roleEntity);
			return ApiResponse.builder().data(null).status(Boolean.TRUE).HTTPstatusCode(HttpStatus.OK)
					.message(messageConfig.roleSaveSuccessMessage).requestId(uuid).build();
		} catch (DataIntegrityViolationException  e) {
			return ApiResponse.builder().data(null).status(Boolean.FALSE).HTTPstatusCode(HttpStatus.BAD_REQUEST)
					.message(uuid).requestId(messageConfig.roleSaveDuplicateMessage).build();
		} catch (Exception e) {
			log.error("Exception : ()", e.getMessage());
			return ApiResponse.builder().data(null).status(Boolean.FALSE)
					.HTTPstatusCode(HttpStatus.INTERNAL_SERVER_ERROR).message(messageConfig.adminContact).build();
		}
	}

	@Override
	public ApiResponse findAll() {
		log.debug("*****************Save Role******************");
		String uuid = UUID.randomUUID().toString();
		log.debug(Constants.REQUEST_ID, uuid);
		try {
			return ApiResponse.builder().data(roleRepo.findAll()).status(Boolean.TRUE).HTTPstatusCode(HttpStatus.OK)
					.message(messageConfig.successFullyFetchedAllRoles).requestId(uuid).build();
		} catch (Exception e) {
			log.error("Exception : ()", e.getMessage());
			return ApiResponse.builder().data(null).status(Boolean.FALSE)
					.HTTPstatusCode(HttpStatus.INTERNAL_SERVER_ERROR).message(messageConfig.adminContact).build();
		}
	}

	@Override
	public ApiResponse findById(Long roleId) {
		log.debug("*****************Get Role******************");
		String uuid = UUID.randomUUID().toString();
		log.debug(Constants.REQUEST_ID, uuid);
		Optional<RoleEntity> role = roleRepo.findById(roleId);
		if(role.isPresent()){
			return ApiResponse.builder().data(role).status(Boolean.TRUE).requestId(uuid).HTTPstatusCode(HttpStatus.OK).message(messageConfig.successfullyFetchedRoleById).requestId(uuid).build();
		}
		else {
			return ApiResponse.builder().data(null).status(Boolean.TRUE).requestId(uuid).HTTPstatusCode(HttpStatus.NOT_FOUND).message(messageConfig.roleByIdNotFound).build();
		}
	}

}
