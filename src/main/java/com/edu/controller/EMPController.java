package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.dao.EmpRepository;
import com.edu.entities.Employees;

import java.util.List;
import java.util.Optional;

@RestController
public class EMPController {

    @Autowired
    private EmpRepository erepo;

    @PostMapping("/employees")
    public ResponseEntity<Employees> saveEmp(@RequestBody Employees employee) {
        try {
            Employees savedEmployee = erepo.save(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getEmp() {
        try {
            List<Employees> employees = (List<Employees>) erepo.findAll();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employees> getEmpById(@PathVariable("id") long id) {
        Optional<Employees> employeeData = erepo.findById(id);
        return employeeData.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employees> updateEmp(@PathVariable("id") long id, @RequestBody Employees employee) {
        Optional<Employees> employeeData = erepo.findById(id);
        if (employeeData.isPresent()) {
            Employees updatedEmployee = employeeData.get();
            //update the fields of updatedEmployee here
            //...
            erepo.save(updatedEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable("id") long id) {
        try {
            erepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
