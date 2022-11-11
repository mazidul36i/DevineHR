package com.divinehr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.model.Department;
import com.divinehr.utility.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public boolean addDepartment(Department dept) throws DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Department (name, location) VALUES (?, ?)");
			ps.setString(1, dept.getName());
			ps.setString(2, dept.getLocation());
			
			int x = ps.executeUpdate();
			
			if (x > 0) {
				return true;
			} else throw new DepartmentException("Failed to insert new department..!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
	}

	@Override
	public boolean deleteDepartment(int deptId) throws DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Department WHERE id= ?");
			ps.setInt(1, deptId);
			
			int x = ps.executeUpdate();
			
			if (x > 0) {
				return true;
			} else throw new DepartmentException("Failed to delete department with ID " + deptId + "!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
	}

	@Override
	public Department getDepartment(int deptId) throws DepartmentException {
		Department dept = null;
		
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Department WHERE id= ?");
			ps.setInt(1, deptId);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				dept = new Department();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setLocation(rs.getString("location"));
				
			} else throw new DepartmentException("No department found with ID " + deptId + "!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
		
		return dept;
	}

	@Override
	public List<Department> getDepartmentList() throws DepartmentException {
		List<Department> depList = new ArrayList<>();
		
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Department");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				Department dept = new Department();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setLocation(rs.getString("location"));
				
				depList.add(dept);
			} 
			
			if (depList.size() == 0) 
				throw new DepartmentException("No department found to be load!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
		
		return depList;
	}

	@Override
	public boolean changeDeptName(int deptId, String newName) throws DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("UPDATE Department SET name= ? WHERE id= ?");
			ps.setString(1, newName);
			ps.setInt(2, deptId);
			
			int x = ps.executeUpdate();
			
			if (x > 0) {
				return true;
			} else throw new DepartmentException("Failed to update department name with ID " + deptId + "!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
	}

	@Override
	public boolean changeDeptLocation(int deptId, String newLocation) throws DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("UPDATE Department SET location= ? WHERE id= ?");
			ps.setString(1, newLocation);
			ps.setInt(2, deptId);
			
			int x = ps.executeUpdate();
			
			if (x > 0) {
				return true;
			} else throw new DepartmentException("Failed to update department name with ID " + deptId + "!");
			
		} catch (SQLException e) {
			throw new DepartmentException(e.getMessage());
		}
	}

}
