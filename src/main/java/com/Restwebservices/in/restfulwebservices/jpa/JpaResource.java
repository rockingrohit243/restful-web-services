package com.Restwebservices.in.restfulwebservices.jpa;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Restwebservices.in.restfulwebservices.user.User;
import com.Restwebservices.in.restfulwebservices.user.UserDaoServices;

import jakarta.validation.Valid;

@RestController
public class JpaResource {
	
	private UserDaoServices service;
	private UserRepository repository;
	public JpaResource(UserDaoServices service,UserRepository repository) {
		this.service=service;
		this.repository=repository;
		
	}
	//method for retrieving all users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return repository.findAll();
	}
	
	//method for a specific users
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id)
	{
		return repository.findById(id);
	}
	
	

	/*//creating users
	@PostMapping("/users")
	public void createUser(@RequestBody User user)
	{
		service.save(user);
	}
	*/
	
	//validating
	
	@PostMapping("/jpa/users")
	public  ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{User savedUser=service.save(user);
	URI location=ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	
	
		//service.save(user);
	return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		service.deleteById(id);
	}
	

}

	
	


