package com.user.request;

import com.user.repository.model.Department;

public class UserDTO {
	
	private String displayName;
	
	private Department department;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

}
