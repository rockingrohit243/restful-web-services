package com.Restwebservices.in.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Restwebservices.in.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	

}
