package com.divinehr.dao;

import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.model.Department;

public interface DepartmentDao {

	public boolean addDepartment(Department dept) throws DepartmentException;
	
	public boolean deleteDepartment(int deptId) throws DepartmentException;
	
	public Department getDepartment(int deptId) throws DepartmentException;
	
	public List<Department> getDepartmentList() throws DepartmentException;
	
	public boolean changeDeptName(int deptId, String newName) throws DepartmentException;
	
	public boolean changeDeptLocation(int deptId, String newLocation) throws DepartmentException;
	
}
