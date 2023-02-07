package com.Restwebservices.in.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoServices service;
	public UserResource(UserDaoServices service) {
		this.service=service;
		
	}
	//method for retrieving all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return service.findAll();
	}
	
	//method for a specific users
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id)
	{
		return service.findOne(id);
	}
	
	

	/*//creating users
	@PostMapping("/users")
	public void createUser(@RequestBody User user)
	{
		service.save(user);
	}
	*/
	
	//validating
	
	@PostMapping("/users")
	public  ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{User savedUser=service.save(user);
	URI location=ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	
	
		//service.save(user);
	return ResponseEntity.created(location).build();
	}
	
	
	
	

}
