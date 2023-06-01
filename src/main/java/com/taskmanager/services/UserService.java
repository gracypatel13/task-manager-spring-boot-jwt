package com.taskmanager.services;

import com.taskmanager.models.payload.UserDto;


public interface UserService {

	public String addUser(UserDto dto);
	public UserDto  getUserById(int id);
}
