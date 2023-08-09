package com.edu.dao;

import org.springframework.data.repository.CrudRepository;

import com.edu.entities.Employees;

public interface EmpRepository extends CrudRepository<Employees, Long>{

}
