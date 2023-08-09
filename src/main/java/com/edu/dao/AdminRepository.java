package com.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.edu.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	// Native Query
	@Query(value = "select * from admin", nativeQuery = true)
	public List<Admin> getAdmin();

	// HQL/JPQL(Java Persistance Query Langauge) Query
	@Query("select a from Admin a")
	public List<Admin> getAllAdmin();

	@Query("select a FROM Admin a WHERE a.firstName=:n")
	public List<Admin> getAdminByFirstName(@Param("n") String firstName);

	public static void saveAdmin(AdminRepository arepo) {

		Admin a1 = new Admin("admin", "admin", "admin@gmail.com", "admin", "super admin");
		Admin a2 = new Admin("admin", "admin", "admin@gmail.com", "admin", "admin");
		Admin a3 = new Admin("admin", "admin", "admin@gmail.com", "admin", "admin");
		List<Admin> adminList = new ArrayList<>();
		adminList.add(a1);
		adminList.add(a2);
		adminList.add(a3);

		Iterable<Admin> saveAll = arepo.saveAll(adminList);
		saveAll.forEach(e -> {
			System.out.println(e);
		});
	}

	public static void getAdminById(AdminRepository arepo, int aid) {
		Optional<Admin> id = arepo.findById(aid);
		System.out.println(id);
	}

	public static void getAllAdmin(AdminRepository arepo) {
		Iterable<Admin> id = arepo.findAll();
		id.forEach(e -> {
			System.out.println(e);
		});
	}

	public static void updateAdmin(AdminRepository arepo, int aid, String firstname) {
		if (arepo.existsById(aid)) {
			Optional<Admin> id = arepo.findById(aid);
			Admin admin = id.get();
			admin.setFirstName(firstname);
			Admin save = arepo.save(admin);
			System.out.println(save);
		} else {
			System.out.println("Admin with id " + aid + " is not found");
		}
	}

	public static void deleteAdminById(AdminRepository arepo, int aid) {
		if (arepo.existsById(aid)) {
			arepo.deleteById(aid);
			System.out.println("Admin with id " + aid + " is deleted");
		} else {
			System.out.println("Admin with id " + aid + " is not found");
		}
	}

	public static void deleteAllAdmin(AdminRepository arepo) {
		if (arepo.count() > 0) {
			arepo.deleteAll();
			System.out.println("All admins have been deleted");
		} else {
			System.out.println("No data found to delete");
		}
	}
}
