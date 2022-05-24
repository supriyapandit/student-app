package com.employee_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_app.entity.User;
import com.employee_app.exception.ResourceNotFoundException;
import com.employee_app.repository.UserRepository;

@RestController  
@RequestMapping("/users")
public class UserController {
	
	
	
	
	@Autowired
	
	private UserRepository userRepository;
	
	// Get All Users
	@GetMapping("/getAllUsers")
	public List<User> getAllUsersApi() {
		return this.userRepository.findAll();
		
	}
	
	//Get User By Id
	@GetMapping("getUserById/{id}")
	public User getUserByIdApi(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found Exception with id: " +userId));
		
	}
	
	//Create User 
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	//Update user
	@PutMapping("/updateUser/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existinguser =  this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found Exception with id: " +userId));
		existinguser.setFirstname(user.getFirstname());
		existinguser.setLastname(user.getLastname());
		existinguser.setEmail(user.getEmail());
		return this.userRepository.save(existinguser);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUserByIdApi(@PathVariable("id") long userId) {
		User existinguser =  this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found Exception with id: " +userId));
		this.userRepository.delete(existinguser);
		return ResponseEntity.ok().build();
		
	}
}
