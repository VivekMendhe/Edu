package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.dao.ProfileRepository;
import com.edu.entities.Profile;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileRepository prepo;

	@PostMapping
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
		try {
			Profile savedProfile = prepo.save(profile);
			return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Profile>> getAllProfiles() {
		try {
			List<Profile> profiles = (List<Profile>) prepo.findAll();
			if (profiles.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable("id") int id) {
		Optional<Profile> profileData = prepo.findById(id);
		return profileData.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Profile> updateProfile(@PathVariable("id") int id, @RequestBody Profile profile) {
		Optional<Profile> profileData = prepo.findById(id);

		if (profileData.isPresent()) {
			Profile updatedProfile = profileData.get();
			updatedProfile.setFirstName(profile.getFirstName());
			updatedProfile.setLastName(profile.getLastName());
			updatedProfile.setEmail(profile.getEmail());
			updatedProfile.setPhoneNumber(profile.getPhoneNumber());
			updatedProfile.setAddress(profile.getAddress());
			updatedProfile.setDateOfBirth(profile.getDateOfBirth());
			updatedProfile.setBio(profile.getBio());

			prepo.save(updatedProfile);
			return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteProfile(@PathVariable("id") int id) {
		if (prepo.existsById(id)) {
			try {
				prepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
