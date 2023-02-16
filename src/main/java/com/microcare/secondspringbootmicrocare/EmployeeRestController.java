package com.microcare.secondspringbootmicrocare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
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
	 
	 @PutMapping("/employee")
public int updateEmployee(@RequestBody Employee emp) {
		 
		 System.out.println(emp.toString());
		return db.updateEmployee(emp);
	 }
	 
	 @PostMapping("/employee")
	 public int createEmployee(@RequestBody Employee emp) {
	 		 
	 		 System.out.println(emp.toString());
	 		return db.insertEmployee(emp);
	 	 }
	 
	 @DeleteMapping("/employee/{email}")
	 public int deleteEmployee(@PathVariable String email) {
	 		 
	 	
	 		return db.deleteEmployee(email);
	 	 }
	 
	 @PostMapping("/employees")
	 public int createEmployees(@RequestBody List<Employee> emp) { 		 
	 		return db.insertEmployees(emp);
	 	 }
}