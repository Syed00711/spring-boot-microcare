package com.microcare.secondspringbootmicrocare;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Employee {
	
	private Integer employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hire_date;
	private int manager_id;
	private String job_title;
	private long salary;
	private boolean cv;

	
	public boolean isCv() {
		return cv;
	}
	public void setCv(boolean cv) {
		this.cv = cv;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", phone=" + phone + ", hire_date=" + hire_date + ", manager_id=" + manager_id
				+ ", job_title=" + job_title + ", salary=" + salary + "]";
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getHire_date() {
		return hire_date;
	}
	public void setHire_date(LocalDate hire_date) {
		this.hire_date = hire_date;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}


}
