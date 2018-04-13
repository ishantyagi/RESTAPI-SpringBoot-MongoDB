package com.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.user.repository.UserRepository;
import com.user.repository.model.User;
import com.user.request.UserDTO;
import com.user.service.IUserService;
import com.user.service.response.UsersResponse;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public User saveUsers(UserDTO request, String username) {
		User notes	= mapUserDtoToModel(request, username);
		notes.setCreatedAt(System.currentTimeMillis());
		User user = userRepository.save(notes);
		return user;
	}


	private User mapUserDtoToModel(UserDTO request, String username) {
		User user = new User(username, request.getDisplayName(), request.getDepartment());
		return user;
	}


	@Override
	public UsersResponse getUsers() {

		UsersResponse userResponse = new UsersResponse();
		List<User> userList = userRepository.findAll();
		userResponse.setUsers(userList);
		return userResponse;
	}

	@Override
	public Boolean deleteUser(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null){
			userRepository.delete(user);
			return true;
		}else{
			return false;
		}		
	}


	@Override
	public User getUser(String username) {
		User user = userRepository.findByUsername(username);		
		return user;
	}
}
