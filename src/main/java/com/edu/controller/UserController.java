package com.edu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.dao.UserRepository;
import com.edu.entities.User;

@RestController
public class UserController {

	@Autowired
	private UserRepository urepo;

	@GetMapping("/user")
	public ResponseEntity<Iterable<User>> getUser() {
		Iterable<User> users = urepo.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		return urepo.findById(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = this.urepo.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
	    Optional<User> userOptional = urepo.findById(id);
	    
	    if (!userOptional.isPresent()) {
	        // Handle the situation where the user doesn't exist. You might want to log this and/or return a ResponseEntity with an error status.
	        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	    }

	    urepo.delete(userOptional.get());
	    return ResponseEntity.ok().build();
	}


	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable("id") int id, @RequestBody User userDetails) {
		Optional<User> uid = urepo.findById(id);

		if (uid.isPresent()) {
			User user = uid.get();
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setEmail(userDetails.getEmail());
			user.setPhoneNumber(userDetails.getPhoneNumber());
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setAccountType(userDetails.getAccountType());
			urepo.save(user);
			return user;
		} else {
			throw new RuntimeException("User not found for id : " + id);
		}
	}

	@PatchMapping("/user/{id}")
	public User patchUser(@PathVariable("id") int id, @RequestBody User userDetails) {
		Optional<User> optionalUser = urepo.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (userDetails.getFirstName() != null) {
				user.setFirstName(userDetails.getFirstName());
			}
			if (userDetails.getLastName() != null) {
				user.setLastName(userDetails.getLastName());
			}
			if (userDetails.getEmail() != null) {
				user.setEmail(userDetails.getEmail());
			}
			if (userDetails.getPhoneNumber() != null) {
				user.setPhoneNumber(userDetails.getPhoneNumber());
			}
			if (userDetails.getUsername() != null) {
				user.setUsername(userDetails.getUsername());
			}
			if (userDetails.getPassword() != null) {
				user.setPassword(userDetails.getPassword());
			}
			if (userDetails.getAccountType() != null) {
				user.setAccountType(userDetails.getAccountType());
			}
			urepo.save(user);
			return user;
		} else {
			throw new RuntimeException("User not found for id : " + id);
		}
	}

}
