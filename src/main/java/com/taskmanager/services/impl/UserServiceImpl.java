package com.taskmanager.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.models.entity.User;
import com.taskmanager.models.payload.UserDto;
import com.taskmanager.repositories.UserRepository;
import com.taskmanager.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private UserDto userToUserDto(User user) {
		return modelMapper.map(user, UserDto.class) ;
	}
	
	private User userDtoToUser(UserDto dto) {
		return modelMapper.map(dto, User.class);
	}
	
	@Override
	public String addUser(UserDto dto) {
		// TODO Auto-generated method stub
		User user=userDtoToUser(dto);
		userRepository.save(user);
		return "User Added";
	}

	@Override
	public UserDto getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
