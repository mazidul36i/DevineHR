package com.divinehr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;
import com.divinehr.utility.DBUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public boolean registerEmployee(Employee emp) throws EmployeeException {
		
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Employee (name, address, email, password, salary, role) VALUES (?, ?, ?, ?, ?, 'Employee')");
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getAddress());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getPassword());
			ps.setInt(5, emp.getSalary());
			
			int x = ps.executeUpdate();
			
			if (x > 0) return true;
			else 
				throw new EmployeeException("Failed to add Employee..!");
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
		
	}

	@Override
	public boolean assignDepartment(int eid, int deptId) throws EmployeeException, DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps1 = conn.prepareStatement("SELECT id FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM Department WHERE id= ?");
			ps2.setInt(1, deptId);
			ResultSet rs2 = ps2.executeQuery();
			if (!rs2.next()) {
				throw new EmployeeException("No department found with Id: " + deptId);
			}
			
			
			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET deptId= ? WHERE id= ?");
			ps.setInt(1, deptId);
			ps.setInt(2, eid);
			
			int x = ps.executeUpdate();
			
			if (x > 0) return true;
			else 
				throw new EmployeeException("Failed to assign department to Employee with ID " + eid+ "!");
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public boolean transferEmployee(int eid, int deptId) throws EmployeeException, DepartmentException {
		try (Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps1 = conn.prepareStatement("SELECT id FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM Department WHERE id= ?");
			ps2.setInt(1, deptId);
			ResultSet rs2 = ps2.executeQuery();
			if (!rs2.next()) {
				throw new EmployeeException("No department found with Id: " + deptId);
			}
			
			
			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET deptId= ? WHERE id= ?");
			ps.setInt(1, deptId);
			ps.setInt(2, eid);
			
			int x = ps.executeUpdate();
			
			if (x > 0) return true;
			else 
				throw new EmployeeException("Failed to assign department to Employee with ID " + eid+ "!");
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployee(int eid) throws EmployeeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateName(int eid, String name) throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAddress(int eid, String newAddress) throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeEmail(int eid, String newEmail) throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(int eid, String newPassord) throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestLeave(int eid, Leave leave) throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean leaveApproval(Leave leave) throws LeaveException {
		// TODO Auto-generated method stub
		return false;
	}

}
