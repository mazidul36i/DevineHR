package com.divinehr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;
import com.divinehr.utility.DBUtil;

/**
 * This class Implements the methods of EmployeeDao interface
 * @see EmployeeDao
 */

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee login(String email, String password) throws EmployeeException {

		Employee emp = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT id FROM Employee WHERE email= ?");
			ps1.setString(1, email);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No user found with email: " + email);
			}

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Employee WHERE email= ? AND password= ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setEmail(email);
				emp.setPassword(password);
				emp.setAddress(rs.getString("address"));
				emp.setSalary(rs.getInt("salary"));
				emp.setRole(rs.getString("role"));
				emp.setDeptId(rs.getInt("deptId"));

			} else
				throw new EmployeeException("Invalid password..!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		return emp;
	}

	@Override
	public boolean registerEmployee(Employee emp) throws EmployeeException {

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO Employee (name, address, email, password, salary, role) VALUES (?, ?, ?, ?, ?, 'Employee')");
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getAddress());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getPassword());
			ps.setInt(5, emp.getSalary());

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
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
				throw new DepartmentException("No department found with Id: " + deptId);
			}

			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET deptId= ? WHERE id= ?");
			ps.setInt(1, deptId);
			ps.setInt(2, eid);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to assign department to Employee with ID " + eid + "!");

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

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to assign department to Employee with ID " + eid + "!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployee(int eid) throws EmployeeException {

		Employee emp = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				emp = new Employee();
				emp.setId(eid);
				emp.setName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setAddress(rs.getString("address"));
				emp.setRole(rs.getString("role"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptId(rs.getInt("deptId"));

			} else
				throw new EmployeeException("No employee found with Id: " + eid);

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		return emp;
	}

	@Override
	public List<Employee> getEmployeeList() throws EmployeeException {
		List<Employee> employees = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee");
			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {

				Employee emp = new Employee();
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setAddress(rs.getString("address"));
				emp.setRole(rs.getString("role"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptId(rs.getInt("deptId"));

				employees.add(emp);
			}

			if (employees.size() == 0)
				throw new EmployeeException("No employee found to be load!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		return employees;
	}

	@Override
	public boolean updateName(int eid, String name) throws EmployeeException {
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}

			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET name= ? WHERE id= ?");
			ps.setString(1, name);
			ps.setInt(2, eid);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to update name with employee ID " + eid + "!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public boolean updateAddress(int eid, String newAddress) throws EmployeeException {
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}

			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET address= ? WHERE id= ?");
			ps.setString(1, newAddress);
			ps.setInt(2, eid);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to update address with employee ID " + eid + "!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public boolean changeEmail(int eid, String newEmail) throws EmployeeException {
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}

			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET email= ? WHERE id= ?");
			ps.setString(1, newEmail);
			ps.setInt(2, eid);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to change email with employee ID " + eid + "!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public boolean changePassword(int eid, String newPassord) throws EmployeeException {
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Employee WHERE id= ?");
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				throw new EmployeeException("No employee found with Id: " + eid);
			}

			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET password= ? WHERE id= ?");
			ps.setString(1, newPassord);
			ps.setInt(2, eid);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to update name with Employee ID " + eid + "!");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public boolean requestLeave(Leave leave) throws EmployeeException {
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `Leave` (eid, deptId, leaveMsg) VALUES (?, ?, ?)");
			ps.setInt(1, leave.getEid());
			ps.setInt(2, leave.getDeptId());
			ps.setString(3, leave.getLeaveMsg());

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new EmployeeException("Failed to send leave request!\nPlease try again...");

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public List<Leave> getLeaveRequests() throws LeaveException {
		List<Leave> leaves = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Leave`");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Leave leave = new Leave();
				leave.setEid(rs.getInt("eid"));
				leave.setDeptId(rs.getInt(rs.getInt("deptId")));
				leave.setLeaveMsg(rs.getString("leaveMsg"));
				leave.setDate(rs.getString("date"));
				leave.setStatus(rs.getString("status"));
				leave.setId(rs.getInt("id"));

				leaves.add(leave);
			}

			if (leaves.size() == 0)
				throw new LeaveException("No leave request found to load..!");

		} catch (SQLException e) {
			throw new LeaveException(e.getMessage());
		}

		return leaves;
	}

	@Override
	public boolean leaveApproval(int id, boolean isApproaved) throws LeaveException {

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("UPDATE `Leave` SET status= ? WHERE id= ?");
			ps.setString(0, isApproaved ? "approved" : "cancelled");
			ps.setInt(2, id);

			int x = ps.executeUpdate();

			if (x > 0)
				return true;
			else
				throw new LeaveException("No leave request found with ID " + id + "!");

		} catch (SQLException e) {
			throw new LeaveException(e.getMessage());
		}

	}

}
