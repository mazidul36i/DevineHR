package com.divinehr.dao;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;

public interface EmployeeDao {
	
	public boolean registerEmployee(Employee emp) throws EmployeeException;
	
	public boolean assignDepartment(int eid, int deptId) throws EmployeeException, DepartmentException;
	
	public boolean transferEmployee(int eid, int deptId) throws EmployeeException, DepartmentException;
	
	public Employee getEmployee(int eid) throws EmployeeException;
	
	public boolean updateName(int eid, String name) throws EmployeeException;
	
	public boolean updateAddress(int eid, String newAddress) throws EmployeeException;
	
	public boolean changeEmail(int eid, String newEmail) throws EmployeeException;
	
	public boolean changePassword(int eid, String newPassord) throws EmployeeException;
	
	public boolean requestLeave(int eid, Leave leave) throws EmployeeException; 
	
	public boolean leaveApproval(Leave leave) throws LeaveException;
	
}
