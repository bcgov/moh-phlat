package com.moh.phlat.backend.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moh.phlat.backend.exception.HandleInternalException;
import com.moh.phlat.backend.exception.HandleNotFoundException;
import com.moh.phlat.backend.model.*;
import com.moh.phlat.backend.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	// create new user
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody @Valid User requestUser) throws HandleInternalException {
		User user = new User();
		user.setUserId(requestUser.getUserId());
		user.setFirstName(requestUser.getFirstName());
		user.setLastName(requestUser.getLastName());
		user.setActiveFlag(requestUser.getActiveFlag());
		user.setCreatedBy(requestUser.getCreatedBy());
		user.setCreatedAt(new Date());

		try {
			userRepository.save(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException("User already exists with userid: " + requestUser.getUserId());
			} else {
				throw new HandleInternalException("Cannot add userid: " + requestUser.getUserId()
						+ ". Please report this to your system administrator");
			}
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/view/all")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	// view specific user
	@GetMapping("/view/{id}")
	public User getUserById(@PathVariable Long id) throws HandleNotFoundException {
		return userRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User not found with id: " + id));
	}

	// update an existing
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody @Valid User requestUser, @PathVariable Long id)
			throws HandleNotFoundException, HandleInternalException {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User not found with id: " + id));

		existingUser.setUserId(requestUser.getUserId());
		existingUser.setFirstName(requestUser.getFirstName());
		existingUser.setLastName(requestUser.getLastName());
		existingUser.setActiveFlag(requestUser.getActiveFlag());
		existingUser.setUpdatedBy(requestUser.getUpdatedBy());
		existingUser.setUpdatedAt(new Date());

		try {
			userRepository.save(existingUser);
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException("User already exists with userid: " + requestUser.getUserId());
			} else if (e.getMessage().contains("foreign key constraint")) {
				throw new HandleInternalException(
						"Can not change the existing userid due to foreign key contraints on User Role.");
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException("Cannot update User with userid: " + requestUser.getUserId()
						+ ". Please report this to your system administrator.");
			}

		}
		return new ResponseEntity<>(existingUser, HttpStatus.OK);
	}

	// delete user
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id)
			throws HandleNotFoundException, HandleInternalException {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User not found with id: " + id));
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key constraint")) {
				throw new HandleInternalException(
						"Can not delete the existing user due to foreign key contraints on User Role.");
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException(
						"Cannot delete User with id: " + id + ". Please report this to your system administrator.");
			}
		}

		return ResponseEntity.ok().build();
	}
}
