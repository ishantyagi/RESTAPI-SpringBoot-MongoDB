package com.user.service;

import com.user.repository.model.User;
import com.user.request.UserDTO;
import com.user.service.response.UsersResponse;


public interface IUserService {

	User saveUsers(UserDTO request, String username);

	UsersResponse getUsers();

	Boolean deleteUser(String username);

	User getUser(String username);	
	
}
