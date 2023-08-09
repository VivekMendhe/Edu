package com.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edu.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public static void getUser(UserRepository userRepo) {
		Iterable<User> get = userRepo.findAll();
		get.forEach(e -> {
			System.out.println(e);
		});
	}

	public static void getSingleUser(UserRepository userRepo, int ID) {
		Optional<User> id = userRepo.findById(ID);
		System.out.println(id);
	}

	public static void updateUser(UserRepository userRepo, int id, String firstname) {
		if (userRepo.existsById(id)) {
			Optional<User> op = userRepo.findById(id);
			User user = op.get();
			user.setFirstName(firstname);
			User save = userRepo.save(user);
			System.out.println(save);
		} else {
			System.out.println("User with id " + id + " not found");
		}
	}

	public static void deleteUser(UserRepository userRepo, int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			System.out.println("User with id " + id + " deleted successfully");
		} else {
			System.out.println("User with id " + id + " not found");
		}
	}

	public static void deleteAllUser(UserRepository userRepo) {
		userRepo.deleteAll();
		System.out.println("All users deleted successfully");
	}

	public static void saveUser(UserRepository userRepo) {

		User user1 = new User("johan0012", "Johan", "Maas", "johan@gmail.com", "123456", "1231231231", "user");
		User user2 = new User("johan0012", "Johan", "Maas", "johan@gmail.com", "123456", "1231231231", "user");
		User user3 = new User("johan0012", "Johan", "Maas", "johan@gmail.com", "123456", "1231231231", "user");
		User user4 = new User("johan0012", "Johan", "Maas", "johan@gmail.com", "123456", "1231231231", "user");
		User user5 = new User("johan0012", "Johan", "Maas", "johan@gmail.com", "123456", "1231231231", "user");

		List<User> list = new ArrayList<>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		list.add(user5);

		Iterable<User> saveAll = userRepo.saveAll(list);

		saveAll.forEach(i -> {
			System.out.println(i);
		});

	}
}
