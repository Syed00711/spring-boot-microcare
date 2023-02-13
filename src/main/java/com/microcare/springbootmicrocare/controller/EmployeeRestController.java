package com.microcare.springbootmicrocare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microcare.springbootmicrocare.dao.DatabaseConnection;
import com.microcare.springbootmicrocare.pojo.Employee;

@RestController("/api")
public class EmployeeRestController {
	
	@Autowired
	DatabaseConnection db;
	
	 @GetMapping("/employee/{id}")
	 public  Employee getEmployee(@PathVariable int id) {
	    
	    return db.getEmployee(id);
	  }
	 
	 @GetMapping("/employees")
	 public  List<Employee> getEmployee() {
	    
	    return db.getEmployees();
	  }

}
