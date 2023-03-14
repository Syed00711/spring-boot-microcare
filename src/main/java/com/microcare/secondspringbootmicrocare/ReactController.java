package com.microcare.secondspringbootmicrocare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactController {
	
	@Autowired
	DatabaseConnection db;
	
	 @GetMapping("/reactemployees")
	 public  List<Employee> getJsonEmployees() {
		 List<Employee> listemp=db.getJsonEmployees();
	   
	    return listemp;
	  }
	 
	 @PostMapping("/reactemployees")
	 public String createEmployee(@RequestBody Employee emp) {
	 		 String message ;
	 		
	 		if(db.insertEmployee(emp)>=1)
	 			message ="Employee Added";
	 		else
	 			message= "Employee Failed";
	 		
	 		return message;
	 		
	 		
	 	 }
	 
	 @GetMapping("/reactdeleteemployee/{email}")
	 public int deleteEmployee(@PathVariable String email) {
	 		 
	 	
	 		return db.deleteEmployee(email);
	 		
	 		
	 	 }
	 
	 @PostMapping("/reactupdateemployee")
	 public String updateEmployee(@RequestBody Employee emp) {
		 String message ;
	 		System.out.println(emp.toString());
	 		if( db.updateEmployee(emp)>=1)
	 		message ="Employee Added";
	 		else
	 			message= "Employee Failed";
	 		
	 		return message;

	 	 }

}
