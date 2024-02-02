package com.moh.phlat.backend.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moh.phlat.backend.exception.HandleInternalException;
import com.moh.phlat.backend.exception.HandleNotFoundException;
import com.moh.phlat.backend.model.UserRole;
import com.moh.phlat.backend.repository.UserRoleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userrole")
@CrossOrigin(origins = "*")
public class UserRoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRoleRepository userRoleRepository;

	// create new User Role
	@PostMapping("/add")
	public ResponseEntity<UserRole> addUseRole(@RequestBody @Valid UserRole requestUserRole)
			throws HandleInternalException {
		UserRole userRole = new UserRole();
		userRole.setUserId(requestUserRole.getUserId());
		userRole.setRoleCode(requestUserRole.getRoleCode());

		try {
			userRoleRepository.save(userRole);
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException(
						"User and role assignment already exists with userid: " + requestUserRole.getUserId());
			} else if (e.getMessage().contains("user_role_user_id_fkey")) {
				throw new HandleInternalException("Invalid Userid: " + requestUserRole.getUserId());
			} else if (e.getMessage().contains("user_role_role_code_fkey")) {
				throw new HandleInternalException("Invalid Role: " + requestUserRole.getRoleCode());
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException("Cannot add userid: " + requestUserRole.getUserId()
						+ ". Please report this to your system administrator");
			}
		}
		return new ResponseEntity<>(userRole, HttpStatus.CREATED);
	}

	@GetMapping("/view/all")
	public @ResponseBody Iterable<UserRole> getAllUserRoles() {
		return userRoleRepository.findAll();
	}

	// view specific user role
	@GetMapping("/view/{id}")
	public UserRole getUserRoleById(@PathVariable Long id) throws HandleNotFoundException {
		return userRoleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User role not found with id: " + id));
	}

	// update an existing user role
	@PutMapping("/update/{id}")
	public String update(@RequestBody UserRole updateUserRole, @PathVariable Long id) {
		return userRoleRepository.findById(id).map(userRole -> {
			userRole.setUserId(updateUserRole.getUserId());
			userRole.setRoleCode(updateUserRole.getRoleCode());

			userRoleRepository.save(userRole);
			return "User role details have been successfully updated!";
		}).orElseGet(() -> {
			return "This user role doesn't exist";
		});
	}

	public UserRole updateUserRole(@RequestBody @Valid UserRole requestUserRole, @PathVariable Long id)
			throws HandleNotFoundException {
		UserRole existingUserRole = userRoleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User role not found with id: " + id));

		existingUserRole.setUserId(requestUserRole.getUserId());
		existingUserRole.setRoleCode(requestUserRole.getRoleCode());

		return userRoleRepository.save(existingUserRole);
	}

	// delete user role
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserRole> deleteUserRoleById(@PathVariable("id") Long id) throws HandleNotFoundException {
		UserRole existingUserRole = userRoleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("User role not found with id: " + id));
		userRoleRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
