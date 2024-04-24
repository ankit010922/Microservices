package com.dish.user.service;

import com.dish.user.form.RegisterUserForm;
import com.dish.user.response.APIResponse;

public interface UserService {
	
	    APIResponse registerUser(RegisterUserForm registerUserForm);

		APIResponse getUserDetails(Long id);
}
