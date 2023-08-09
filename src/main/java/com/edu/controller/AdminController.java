package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.dao.AdminRepository;
import com.edu.entities.Admin;

import java.util.Optional;

@RestController
public class AdminController {

	@Autowired
	private AdminRepository arepo;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        try {
            Admin savedAdmin = arepo.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/admin")
	public ResponseEntity<Iterable<Admin>> getAdmin() {
	    Iterable<Admin> admins = arepo.findAll();

	    // If no admin is found, return a 404 response
	    if (!admins.iterator().hasNext()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // If admins are found, return them with a 200 response
	    return new ResponseEntity<>(admins, HttpStatus.OK);
	}

	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id) {
        Optional<Admin> adminData = arepo.findById(id);
        if (adminData.isPresent()) {
            return new ResponseEntity<>(adminData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") int id, @RequestBody Admin admin) {
        Optional<Admin> adminData = arepo.findById(id);

        if (adminData.isPresent()) {
            Admin _admin = adminData.get();
            _admin.setFirstName(admin.getFirstName());
            _admin.setLastName(admin.getLastName());
            _admin.setEmail(admin.getEmail());
            _admin.setPassword(admin.getPassword());
            _admin.setRole(admin.getRole());
            return new ResponseEntity<>(arepo.save(_admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") int id) {
	    Optional<Admin> admin = arepo.findById(id);

	    // If no admin with the given id is found, return a 404 response
	    if (!admin.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // If an admin with the given id is found, try to delete it
	    try {
	        arepo.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
