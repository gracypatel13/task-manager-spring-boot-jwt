package com.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.models.payload.AuthRequest;
import com.taskmanager.models.payload.UserDto;
import com.taskmanager.services.JwtService;
import com.taskmanager.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager Manager;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@GetMapping("/message")
	
	public String message() {
		return "In the user api";
	}
	
	@PostMapping("/register")
	public String addUser(@RequestBody UserDto dto) {
	
		userService.addUser(dto);
		//userService.assignRole(dto.getUserName(), "USER");
		return "Added";
	}
	
	@GetMapping("/{id}")
	public UserDto getUserById (@PathVariable int id) {
		
		return userService.getUserById(id);
	}
	 
	
	
	@PostMapping("/auth")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		//return jwtService.generateToken(authRequest.getUserName());
		System.out.println("in auth");
		System.out.println(authRequest.getUserName());
		System.out.println(authRequest.getPassword());
		Authentication authentication=Manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			System.out.println(jwtService.generateToken(authRequest.getUserName()));
		return jwtService.generateToken(authRequest.getUserName());
		}
		else {
			throw new UsernameNotFoundException("Invalid user");
		}
	}

}