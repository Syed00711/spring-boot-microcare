package com.microcare.secondspringbootmicrocare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class EmployeeRestController {
	
	@Autowired
	DatabaseConnection db;
	
	 
	 @GetMapping("/")
	 public String homepage(Model model) {
	 	Employee employee =new Employee();
	 	model.addAttribute("employee", employee);
	 		return "index";
	 	 }
	
	 @GetMapping("/employee/{id}")
	 public  Employee getEmployee(@PathVariable int id) {
	    
	    return db.getEmployee(id);
	  }
	 
	 @GetMapping("/employees")
	 public  String getEmployees(Model model) {
	    model.addAttribute("listemp", db.getEmployees());
	    return "employees";
	  }
	 
	 @PutMapping("/employee")
public int updateEmployee(@RequestBody Employee emp) {
		 
		 System.out.println(emp.toString());
		return db.updateEmployee(emp);
	 }
	 
	 @PostMapping("/employee")
	 public String createEmployee(@ModelAttribute("employee") Employee emp,Model model) {
	 		 
	 		
	 		if(db.insertEmployee(emp)>=1)
	 			model.addAttribute("message", "Employee Added");
	 		else
	 			model.addAttribute("message", "Employee Failed");
	 		
	 		return "redirect:/employees";
	 		
	 		
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