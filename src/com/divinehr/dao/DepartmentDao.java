package com.divinehr.dao;

import java.util.List;

import com.divinehr.exceptions.DepartmentException;
import com.divinehr.model.Department;

/**
 * 
 * @author Mazidul Islam <br>
 *         Department DAO to manipulate data of an employee in the database.
 */
public interface DepartmentDao {

	/**
	 * 
	 * @param It takes Department model object without id
	 * @return boolean value(true) when department has added successfully
	 * @throws DepartmentException, if failed to add department
	 */
	public boolean addDepartment(Department dept) throws DepartmentException;

	/**
	 * 
	 * @param It takes department id to delete a department
	 * @return boolean value(true) when department is deleted successfully
	 * @throws DepartmentException, if no department exists or failed to delete
	 *                              department
	 */
	public boolean deleteDepartment(int deptId) throws DepartmentException;

	/**
	 * 
	 * @param It takes department id to get the details of a department
	 * @return Department object containing all details of a department
	 * @throws DepartmentException, if no department exists with the given id
	 */
	public Department getDepartment(int deptId) throws DepartmentException;

	/**
	 * 
	 * @param It doesn't take any input from user
	 * @return List of Department objects containing all details of a department
	 * @throws DepartmentException, if no department found to be load
	 */
	public List<Department> getDepartmentList() throws DepartmentException;

	/**
	 * 
	 * @param It takes department id and corrected/new department name to update
	 * @return boolean value(true) when department name has changed successfully
	 * @throws DepartmentException, if no department exists with the given id or
	 *                              failed to update
	 */
	public boolean changeDeptName(int deptId, String newName) throws DepartmentException;

	/**
	 * 
	 * @param It takes department id and corrected/new department location to update
	 * @return boolean value(true) when department location has changed successfully
	 * @throws DepartmentException, if no department exists with the given id or
	 *                              failed to update
	 */
	public boolean changeDeptLocation(int deptId, String newLocation) throws DepartmentException;

}
