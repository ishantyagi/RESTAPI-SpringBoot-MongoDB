package com.user.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.user.repository.UserRepository;
import com.user.request.UserDTO;

@Component
public class UserValidator {
	
	@Autowired
	private UserRepository userRepository;

	public List<String> validateUser(UserDTO request) {
		
		List<String> errors = new ArrayList<String>();
				
		if(request.getDepartment() == null ){
			errors.add("department can not be null or empty");
		}
		if(StringUtils.isEmpty(request.getDisplayName())){
			errors.add("display name can not be null or empty");
		}		
		return errors;
	}


	public Boolean checkUsernameExists(String username) {		
		if(userRepository.findByUsername(username) != null){
			return true;
		}
		return false;
	}
}
