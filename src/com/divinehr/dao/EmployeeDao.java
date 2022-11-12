package com.divinehr.dao;

import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;

public interface EmployeeDao {
	
	public Employee login(String email, String password)throws EmployeeException;
	
	public boolean registerEmployee(Employee emp) throws EmployeeException;
	
	public boolean assignDepartment(int eid, int deptId) throws EmployeeException, DepartmentException;
	
	public boolean transferEmployee(int eid, int deptId) throws EmployeeException, DepartmentException;
	
	public Employee getEmployee(int eid) throws EmployeeException;
	
	public boolean updateName(int eid, String name) throws EmployeeException;
	
	public boolean updateAddress(int eid, String newAddress) throws EmployeeException;
	
	public boolean changeEmail(int eid, String newEmail) throws EmployeeException;
	
	public boolean changePassword(int eid, String newPassord) throws EmployeeException;
	
	public boolean requestLeave(Leave leave) throws EmployeeException; 
	
	public List<Leave> getLeaveRequests() throws LeaveException;
	
	public boolean leaveApproval(int id, boolean isApproaved) throws LeaveException;
	
}
