package com.divinehr.dao;

import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;

/**
 * @author Mazidul Islam <br>
 *         Employee DAO to manipulate data of an employee in the database.
 */

public interface EmployeeDao {

	/**
	 * @param It takes Email and password to login a user
	 * @return Employee object on successful login
	 * @throw Throws EmployeeException with proper message when there is a mismatch
	 */
	public Employee login(String email, String password) throws EmployeeException;

	/**
	 * @param It takes employee object without id and department as 0 (not assigned)
	 * @return true when successfully registered an employee
	 * @throws EmployeeException with proper message
	 */
	public boolean registerEmployee(Employee emp) throws EmployeeException;

	/**
	 * 
	 * @param It takes employee Id and department Id
	 * @return Returns boolean value(true) when an employee successfully registered
	 * @throws EmployeeException,   if no employee found with the given id or failed
	 *                              to assign department
	 * @throws DepartmentException, if no department exists with the given
	 *                              department id
	 */
	public boolean assignDepartment(int eid, int deptId) throws EmployeeException, DepartmentException;

	/**
	 * 
	 * @param It takes employee Id and new department Id to transfer and employee
	 *           one department to the other.
	 * @return Returns boolean value(true) when an employee transfered successfully.
	 * @throws EmployeeException,   if no employee found with the given id or failed
	 *                              to transfer department
	 * @throws DepartmentException, if no department exists with the given
	 *                              department id
	 */
	public boolean transferEmployee(int eid, int deptId) throws EmployeeException, DepartmentException;

	/**
	 * 
	 * @param It takes employee id to get employee details
	 * @return Returns Employee object on success
	 * @throws EmployeeException, if no employee exists with the given employee id
	 */
	public Employee getEmployee(int eid) throws EmployeeException;

	/**
	 * @param It doesn't take any input from user
	 * @return Returns list of employee
	 * @throws EmployeeException, if no employee found on the database
	 */
	public List<Employee> getEmployeeList() throws EmployeeException;

	/**
	 * 
	 * @param It takes employee id and corrected employee name to update
	 * @return Boolean value(true) when name updated successfully.
	 * @throws EmployeeException, if no employee exists or failed to update name
	 */
	public boolean updateName(int eid, String name) throws EmployeeException;

	/**
	 * 
	 * @param It takes employee id and new/corrected address to update
	 * @return Boolean value(true) when address updated successfully.
	 * @throws EmployeeException, if no employee exists or failed to update address
	 */
	public boolean updateAddress(int eid, String newAddress) throws EmployeeException;

	/**
	 * 
	 * @param It takes employee id and new/corrected email to update
	 * @return Boolean value(true) when email updated successfully.
	 * @throws EmployeeException, if no employee exists with the id or failed to
	 *                            update email
	 */
	public boolean changeEmail(int eid, String newEmail) throws EmployeeException;

	/**
	 * 
	 * @param It takes employee id and new password to be changed
	 * @return Boolean value(true) when password changed successfully.
	 * @throws EmployeeException, if no employee exists with the id or failed to
	 *                            update password
	 */
	public boolean changePassword(int eid, String newPassord) throws EmployeeException;

	/**
	 * 
	 * @param it takes Leave model object without id
	 * @return Boolean value(true) when leave request sent successfully.
	 * @throws EmployeeException, if no employee exists with the id or failed to
	 *                            raise a leave request
	 */
	public boolean requestLeave(Leave leave) throws EmployeeException;

	/**
	 * @param It takes no inputs from user
	 * @return List of leave requests that are made my employees
	 * @throws LeaveException, if no leave request found to load.
	 */
	public List<Leave> getLeaveRequests() throws LeaveException;

	/**
	 * 
	 * @param It takes leave id and a boolean that says if the leave request
	 *           approved or denied
	 * @return Boolean value(true) when is request is approved or denied
	 *         successfully
	 * @throws LeaveException, if no leave request exists with the id
	 */
	public boolean leaveApproval(int id, boolean isApproaved) throws LeaveException;

}
