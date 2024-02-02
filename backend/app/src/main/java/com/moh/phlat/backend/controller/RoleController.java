package com.moh.phlat.backend.controller;

import java.util.List;
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
import com.moh.phlat.backend.model.Role;
import com.moh.phlat.backend.repository.RoleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleRepository roleRepository;

	// create new user
	@PostMapping("/add")
	public ResponseEntity<Role> addNewRole(@RequestBody @Valid Role newRole) throws HandleInternalException {
		Role role = new Role();
		role.setCode(newRole.getCode());
		role.setDescription(newRole.getDescription());

		try {
			roleRepository.save(role);
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException("Role already exists with code: " + newRole.getCode());
			} else {
				throw new HandleInternalException("Cannot add new role code: " + newRole.getCode()
						+ ". Please report this to your system administrator");
			}
		}
		return new ResponseEntity<>(role, HttpStatus.CREATED);

	}

	@GetMapping("view/all")
	public @ResponseBody Iterable<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	// view specific role

	@GetMapping("/view/{id}")
	public Role getRoleById(@PathVariable Long id) throws HandleNotFoundException {
		return roleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Role not found with id: " + id));
	}

	// update an existing role
	@PutMapping("/update/{id}")
	public ResponseEntity<Role> updateRoleById(@RequestBody @Valid Role requestRole, @PathVariable Long id)
			throws HandleNotFoundException, HandleInternalException {
		Role existingRole = roleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Role not found with id: " + id));

		existingRole.setCode(requestRole.getCode());
		existingRole.setDescription(requestRole.getDescription());

		try {
			roleRepository.save(existingRole);
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException("Role already exists with code: " + requestRole.getCode());
			} else if (e.getMessage().contains("foreign key constraint")) {
				throw new HandleInternalException(
						"Can not change the existing role to foreign key contraints on User Role.");
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException("Cannot change Role with code: " + requestRole.getCode()
						+ ". Please report this to your system administrator.");
			}
		}
		return new ResponseEntity<>(existingRole, HttpStatus.OK);

	}

	// delete a role
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Role> deleteRoleById(@PathVariable("id") Long id)
			throws HandleNotFoundException, HandleInternalException {
		Role existingRole = roleRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Role not found with id: " + id));

		try {
			roleRepository.deleteById(id);
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key constraint")) {
				throw new HandleInternalException(
						"Can not delete the existing role due to foreign key contraints on User Role.");
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException(
						"Cannot delete Role with id: " + id + ". Please report this to your system administrator.");
			}
		}

		return ResponseEntity.ok().build();
	}
}
