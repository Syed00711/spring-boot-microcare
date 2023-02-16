package com.microcare.secondspringbootmicrocare;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DatabaseConnection {
	
	@Autowired
	DataSource dataSource;
	
	 //  private static String instemp="insert into employees values((select max(employee_id)+1 from employees),"
	    //		+ "FIRST_NAME,LAST_NAME,EMAIL,PHONE,HIRE_DATE,10,JOB_TITLE,SALARY)";
	    
    
    private static final String udpemployee ="update employees set FIRST_NAME=?,last_name=? where employee_id=?";
    private static final String deleteEmployee ="delete from employees where email=?";
    

    
    
public int insertEmployees(List<Employee> emps) {
    	
    	int result=0;
try {
	for(Employee emp:emps) {
		String instemp="insert into employees values((select max(employee_id)+1 from employees),"
	    		+ "FIRST_NAME,LAST_NAME,EMAIL,PHONE,HIRE_DATE,10,JOB_TITLE,SALARY)";
	    
		instemp=	instemp.replace("FIRST_NAME", "'"+emp.getFirst_name()+"'")
				.replace("LAST_NAME", "'"+emp.getLast_name()+"'")
				.replace("EMAIL", "'"+emp.getEmail()+"'")
				.replace("PHONE", "'"+emp.getPhone()+"'")
				.replace("HIRE_DATE","sysdate")
				.replace("JOB_TITLE", "'"+emp.getJob_title()+"'").replace("SALARY",Long.toString(emp.getSalary()));
		
		System.out.println(instemp);
		Statement stmt =  dataSource.getConnection().createStatement();
		result +=  stmt.executeUpdate(instemp);
			
	}
			
} catch (SQLException e) {
	e.printStackTrace();
}	
		return result;
    	
    }
    
    
    public int insertEmployee(Employee emp) {
    	
    	int result=0;
try {
	String instemp="insert into employees values((select max(employee_id)+1 from employees),"
    		+ "FIRST_NAME,LAST_NAME,EMAIL,PHONE,HIRE_DATE,10,JOB_TITLE,SALARY)";
    
	
	if(emp!=null) {
		instemp =instemp.replace("FIRST_NAME", "'"+emp.getFirst_name()+"'")
		.replace("LAST_NAME", "'"+emp.getLast_name()+"'")
		.replace("EMAIL", "'"+emp.getEmail()+"'")
		.replace("PHONE", "'"+emp.getPhone()+"'")
		.replace("HIRE_DATE","sysdate")
		.replace("JOB_TITLE", "'"+emp.getJob_title()+"'").replace("SALARY",Long.toString(emp.getSalary()));
		System.out.println(instemp);
		
			Statement stmt =  dataSource.getConnection().createStatement();
		result =stmt.executeUpdate(instemp);
			
	}
			
} catch (SQLException e) {
	e.printStackTrace();
}	
		return result;
    	
    }
    
    public int updateEmployee(Employee emp) {
    	int result=0;
try {
            //update employees set first_name=? where empl_id=?
			PreparedStatement preparestmt =  dataSource.getConnection().prepareStatement(udpemployee);
			//
			preparestmt.setString(1, emp.getFirst_name());
			preparestmt.setString(2, emp.getLast_name());
			//	update employees set first_name='microcare' where empl_id=?
			preparestmt.setInt(3, emp.getEmployee_id());
//				update employees set first_name='microcare' where empl_id=353523
				 result =preparestmt.executeUpdate();
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
return result;
    	
    	
    	
    }
    
    
    public int deleteEmployee(String email) {
    	int result=0;
    	try {
    				PreparedStatement preparestmt =  dataSource.getConnection().prepareStatement(deleteEmployee);
    				preparestmt.setString(1, email);
    			    result =preparestmt.executeUpdate();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    	return result;
    	    	
    	
    	
    }
    
    
    public List<Employee> getEmployees(){
    	List<Employee> employees = new ArrayList<Employee>();
          Employee emp;
		
		try {
            
			Statement stmt = dataSource.getConnection().createStatement();
		ResultSet rs =stmt.executeQuery("select * from employees");			
			while(rs.next()) {
				emp=new Employee();
				emp.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				emp.setFirst_name(rs.getString("FIRST_NAME"));
				emp.setLast_name(rs.getString("LAST_NAME"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setJob_title(rs.getString("JOB_TITLE"));
				emp.setHire_date(rs.getDate("HIRE_DATE").toLocalDate());
				emp.setManager_id(rs.getInt("MANAGER_ID"));
				employees.add(emp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
return employees;
    	
    	
    }
    
    
    
	public  Employee getEmployee(int emp_id) {
		
		Employee emp =new Employee();
		
		try {
            
			Statement stmt = dataSource.getConnection().createStatement();
		ResultSet rs =stmt.executeQuery("select * from employees where employee_id="+emp_id);			
			while(rs.next()) {
				emp.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				emp.setFirst_name(rs.getString("FIRST_NAME"));
				emp.setLast_name(rs.getString("LAST_NAME"));
				emp.setPhone(rs.getString("PHONE"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setJob_title(rs.getString("JOB_TITLE"));
				emp.setHire_date(rs.getDate("HIRE_DATE").toLocalDate());
				emp.setManager_id(rs.getInt("MANAGER_ID"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
return emp;
	}
	
	
	

	
}
