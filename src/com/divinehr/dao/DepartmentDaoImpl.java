package com.divinehr.dao;

import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public boolean addDepartment(Department dept) throws DepartmentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDepartment(Department dept) throws DepartmentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Department getDepartment(int deptId) throws DepartmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> getDepartmentList() throws DepartmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeDeptName(int deptId, String newName) throws DepartmentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDeptLocation(int deptId, String newLocation) throws DepartmentException {
		// TODO Auto-generated method stub
		return false;
	}

}
