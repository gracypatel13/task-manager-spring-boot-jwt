package com.taskmanager.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
private int id;
	
	private String Name;
	private String password;
	private String userName;
	private String profilePicture;
}
