package com.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edu.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

	public static void getProfileById(ProfileRepository prepo, int pid) {
		Optional<Profile> id = prepo.findById(pid);
		System.out.println(id);
	}

	public static void getAllProfile(ProfileRepository prepo) {
		Iterable<Profile> id = prepo.findAll();
		id.forEach(e -> {
			System.out.println(e);
		});
	}

	public static void updateProfile(ProfileRepository prepo, int pid, String firstname) {
		if (prepo.existsById(pid)) {
			Optional<Profile> id = prepo.findById(pid);
			Profile profile = id.get();
			profile.setFirstName(firstname);
			Profile save = prepo.save(profile);
			System.out.println(save);
		} else {
			System.out.println("User with id " + pid + " not found");
		}
	}

	public static void deleteProfileById(ProfileRepository prepo, int pid) {
		if (prepo.existsById(pid)) {
			prepo.deleteById(pid);
			System.out.println("User with id " + pid + " deleted successfully");
		} else {
			System.out.println("User with id " + pid + " not found");
		}
	}

	public static void deleteAllProfile(ProfileRepository prepo) {
		prepo.deleteAll();
		System.out.println(" Deleted successfully");
	}

	public static void saveProfile(ProfileRepository prepo) {
		Profile p1 = new Profile("Lavanya", "Motilal", "lava@gmail.com", "1234567891", "Noida Delhi", "01-02-1999",
				"ISRO Scientist");

		Profile p2 = new Profile("Lavanya", "Motilal", "lava@gmail.com", "1234567891", "Noida Delhi", "01-02-1999",
				"ISRO Scientist");

		Profile p3 = new Profile("Lavanya", "Motilal", "lava@gmail.com", "1234567891", "Noida Delhi", "01-02-1999",
				"ISRO Scientist");

		Profile p4 = new Profile("Lavanya", "Motilal", "lava@gmail.com", "1234567891", "Noida Delhi", "01-02-1999",
				"ISRO Scientist");

		Profile p5 = new Profile("Lavanya", "Motilal", "lava@gmail.com", "1234567891", "Noida Delhi", "01-02-1999",
				"ISRO Scientist");

		List<Profile> plist = new ArrayList<>();
		plist.add(p1);
		plist.add(p2);
		plist.add(p3);
		plist.add(p4);
		plist.add(p5);

		Iterable<Profile> save = prepo.saveAll(plist);
		save.forEach(e -> {
			System.out.println(e);
		});
	}
}
