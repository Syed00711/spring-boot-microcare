package com.microcare.secondspringbootmicrocare;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class EmployeeRestController {
	
	@Autowired
	DatabaseConnection db;
	
	@GetMapping("/login")
	 public String login(HttpServletRequest request, HttpSession session) {
		 session.setAttribute(
		         "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
		      ); 
	 		return "login";
	 	 }
	
	
	@GetMapping("/signup")
	 public String signup(Model model) {
		Login signup =new Login();
		
		model.addAttribute("signup", signup); 
	 		return "signup";
	 	 }
	
	@PostMapping("/signup")
	 public String signup(@ModelAttribute("signup") Login signup,Model model) {
		
		System.out.println(signup.toString());
		db.createUser(signup);
		model.addAttribute("success","User Created Successfully");
		return "signup";
	 	 }
	
	 
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
	 

	 @GetMapping("/viewemployee/{id}")
	 public  String viewEmployee(@PathVariable int id,Model model) {
			Employee employee =db.getEmployee(id);
			
		 	model.addAttribute("employee", employee);

	     return "update";
	  }
	 
	 @GetMapping("/employees")
	 public  String getEmployees(Model model) {
		 List<Employee> listemp=db.getEmployees();
		 listemp.sort((o1, o2)
                 -> o1.getEmployee_id().compareTo(
                     o2.getEmployee_id()));
	    model.addAttribute("listemp", listemp);
	    return "employees";
	  }
	
	 
	 @PostMapping("/updateemployee")
public String updateEmployee(@ModelAttribute("employee") Employee emp) {
		 
		System.out.println(emp.toString());
		 db.updateEmployee(emp);
		 
		 return "redirect:/employees";
	 }
	 
	 @PostMapping("/employee")
	 public String createEmployee(@ModelAttribute("employee") Employee emp,Model model) {
	 		 
	 		
	 		if(db.insertEmployee(emp)>=1)
	 			model.addAttribute("message", "Employee Added");
	 		else
	 			model.addAttribute("message", "Employee Failed");
	 		
	 		return "redirect:/employees";
	 		
	 		
	 	 }
	 
	 @GetMapping("/deleteemployee/{email}")
	 public String deleteEmployee(@ModelAttribute("email")  String email) {
	 		 
	 	
	 		db.deleteEmployee(email);
	 		
	 		return "redirect:/employees";
	 	 }
	 
	 @PostMapping("/employees")
	 public int createEmployees(@RequestBody List<Employee> emp) { 		 
	 		return db.insertEmployees(emp);
	 	 }
	 
	 
	 @GetMapping("/upload")
	 public String Upload( Model model) {
		 Employee_CV employee_cv =new Employee_CV();	 
	  model.addAttribute("employee_cv", employee_cv);
	  return "uploadfile";
	  
	 }
	 
	 @PostMapping("/uploadfile")
	 public String fileUpload(@ModelAttribute("employee_cv") Employee_CV cv,  Model model) throws IOException {
		
		cv.setFileName(cv.getFile_content().getOriginalFilename());
		cv.setContent_type(cv.getFile_content().getContentType());
		cv.setFile_size(cv.getFile_content().getSize());
	 db.insertEmployeeCV(cv);
	 
	  model.addAttribute("success", "File Uploaded Successfully!!!");
	  return "uploadfile";
	  
	 }
	 
	 @GetMapping("/downloadfile/{employee_id}")
	 public void downloadFile(@ModelAttribute("employee_id")int  employee_id , Model model, HttpServletResponse response) throws IOException {
	

        Employee_CV cv=db.getCV(employee_id);
	   response.setContentType("application/octet-stream");
	   String headerKey = "Content-Disposition";
	   String headerValue = "attachment; filename = "+cv.getFileName();
	   response.setHeader(headerKey, headerValue);
	   ServletOutputStream outputStream = response.getOutputStream();
	   outputStream.write(cv.getContent());
	   outputStream.close();
	  
	 }
	 
	 private String getErrorMessage(HttpServletRequest request, String key) {
	      Exception exception = (Exception) request.getSession().getAttribute(key); 
	      String error = ""; 
	      if (exception instanceof BadCredentialsException) { 
	         error = "Invalid username and password!"; 
	      } else if (exception instanceof LockedException) { 
	         error = exception.getMessage(); 
	      } else { 
	         error = "Invalid username and password!"; 
	      } 
	      return error;
	   }
	 
	 
}