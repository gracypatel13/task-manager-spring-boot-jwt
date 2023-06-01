package com.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.models.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User  findByUserName(String userName);
}
