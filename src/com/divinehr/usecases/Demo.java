package com.divinehr.usecases;

import java.util.List;
import java.util.Scanner;

import com.divinehr.dao.DepartmentDao;
import com.divinehr.dao.DepartmentDaoImpl;
import com.divinehr.dao.EmployeeDao;
import com.divinehr.dao.EmployeeDaoImpl;
import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.exceptions.LeaveException;
import com.divinehr.model.Department;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;
import com.divinehr.utility.DivineUtil;

/**
 * 
 * Use-case flow of the application. It takes input from console as well as
 * print details in the console
 */
public class Demo {

	private static Employee employee = null;
	private static int timer = 100;
	private static Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * Starting of the demo application
	 */
	public static void main(String[] args) {

		System.out.println("\u001B[92m****************************************");
		DivineUtil.sleep(timer);
		System.out.println("          Welcome to Divine HR!");
		DivineUtil.sleep(timer);
		System.out.println("****************************************\u001B[0m");
		DivineUtil.sleep(500);
		System.out.println("\nPlease login to continue...");

		DivineUtil.sleep(timer);
		employee = askLogin(); // Ask for login via email and password
		if (employee == null) { // If login failed
			System.out.println("Failed to login..!");
			DivineUtil.sleep(timer);
			System.out.println("\nProgramm closed!");
			DivineUtil.sleep(timer);
			System.out.println("Thanks for using \"Divine HR\"");
			return;
		}

		System.out.println("Logged in sucessfully\n"); // Successful login message
		try {
			if (employee.getRole().equals("Admin")) {
				adminTrack(employee); // If logged in user is an admin, then take him/her throw admin track
			} else {
				employeeTrack(employee); // If logged in user is an employee, then take him/her throw employee track
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			closeProgramm();
		}
	}

	/**
	 * 
	 * When something goes wrong with the flow or user input, it gives a chance to
	 * try again and handles the exception
	 * 
	 * @return Integer value, either 1, 2, 3, or -1 (failed/invalid input);
	 */
	private static int tryAgain() {
		try {
			System.out.println("Please select an option:");
			DivineUtil.sleep(timer);
			System.out.println("1 : Try again...");
			DivineUtil.sleep(timer);
			System.out.println("2 : Exit.");
			return sc.nextInt();
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * It closes/stops the application forcefully by stopping the main thread.
	 */
	private static void closeProgramm() {
		System.out.println("Programm closed!");
		DivineUtil.sleep(timer);
		System.out.println("Thanks for using \"Divine HR\"");
		Thread.currentThread().stop();
	}

	/**
	 * This method is responsible for login a user
	 * 
	 * @return Employee object
	 */
	private static Employee askLogin() {

		Employee emp = null;

		System.out.println("\u001B[93mEnter your email id:\u001B[0m ");
		String email = sc.next();
		if (!DivineUtil.validateEmail(email)) {
			DivineUtil.sleep(timer);
			System.out.println("Invalid email: " + email);

			System.out.println();
			int choice = tryAgain();
			if (choice == 1) {
				emp = askLogin();
				return emp;
			} else if (choice == 2) {
				return null;
			} else {
				System.out.println("Unknown option: " + choice + " selected..!");
				return null;
			}
		}
		System.out.println("\u001B[93mEnter your password:\u001B[0m ");
		sc.nextLine();
		String pass = sc.nextLine();

		EmployeeDao ed = new EmployeeDaoImpl();

		try {
			System.out.println("\nProcessing credential...");
			emp = ed.login(email, pass);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
			System.out.println();

			int choice = tryAgain();
			if (choice == 1) {
				emp = askLogin();
			} else if (choice == 2) {
				return null;
			} else {
				System.out.println("Unknown option: " + choice + " selected..!");
				return null;
			}
		}
		return emp;
	}

	/**
	 * @param It takes Employee object as it's parameter
	 * @throws Exception, if the user is not an amin and still trying to access
	 *                    admin track, it throws an exception
	 */
	private static void adminTrack(Employee user) throws Exception {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("Welcome " + employee.getName() + " to your admin panel!");
		DivineUtil.sleep(timer);
		System.out.println("===============================\n");

		while (true) {

			DivineUtil.sleep(timer);
			System.out.println("Please select an option:");
			DivineUtil.sleep(timer);
			System.out.println("1 : Manage Employees");
			DivineUtil.sleep(timer);
			System.out.println("2 : Manage Departments");
			DivineUtil.sleep(timer);
			System.out.println("3 : Update your profile");
			DivineUtil.sleep(timer);
			System.out.println("4 : Show profile");
			DivineUtil.sleep(timer);
			System.out.println("\u001B[91m5 : Log out.");
			DivineUtil.sleep(timer);
			System.out.println("6 : Exit.\u001B[0m");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception ignore) {
			}

			if (choice == 1) {
				manageEmployee(user);
			} else if (choice == 2) {
				manageDepartment(user);
			} else if (choice == 3) {
				updateProfile(user);
			} else if (choice == 4) {
				displayProfile(user);
			} else if (choice == 5) {
				employee = null;
				System.out.println("Logged out successfully!\n\n");
				main(null);
				return;
			} else if (choice == 6) {
				DivineUtil.sleep(timer);
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					closeProgramm();
				} else {
					throw new Exception("Unknown option selected..!");
				}

			}

		}
	}

	/**
	 * Manage Employee Only Admins can access
	 */
	private static void manageEmployee(Employee user) throws Exception {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		while (true) {
			System.out.println("Please select an option:");
			DivineUtil.sleep(timer);
			System.out.println("1 : Add new Employee");
			DivineUtil.sleep(timer);
			System.out.println("2 : Assign department to an Employee");
			DivineUtil.sleep(timer);
			System.out.println("3 : Transfer an Employee");
			DivineUtil.sleep(timer);
			System.out.println("4 : Get Employee list");
			DivineUtil.sleep(timer);
			System.out.println("5 : Get Employee details");
			DivineUtil.sleep(timer);

			System.out.println("6 : Get Leave requat list");
			DivineUtil.sleep(timer);
			System.out.println("7 : Approve a leave request");
			DivineUtil.sleep(timer);

			System.out.println("\u001B[94m8 : Back.\u001B[0m");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception ignore) {
			}
			if (choice == 1) {
				addNewEmployee(user);
			} else if (choice == 2) {
				assignDepartment(user);
			} else if (choice == 3) {
				transferEmployee(user);
			} else if (choice == 4) {
				getEmployeeList(user);
			} else if (choice == 5) {
				getEmployeeDetails(user);
			} else if (choice == 6) {
				getLeaveRequstList(user);
			} else if (choice == 7) {

			} else if (choice == 8) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					throw new Exception();
				} else {
					throw new Exception("Unknown option selected..!");
				}
			}
		}

	}

	private static void addNewEmployee(Employee user) throws Exception {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		Employee emp = new Employee();

		System.out.println("Please enter employee details:");
		DivineUtil.sleep(timer);

		System.out.println("\u001B[93mEmail:\u001B[0m ");
		String email = sc.next();
		if (!DivineUtil.validateEmail(email)) {
			DivineUtil.sleep(timer);
			System.out.println("Invalid email: " + email);

			int choice = tryAgain();
			if (choice == 1) {
				addNewEmployee(user);
				return;
			} else if (choice == 2) {
				return;
			} else {
				throw new Exception("Unknown option selected...!");
			}
		}
		emp.setEmail(email);
		System.out.println("\u001B[93mPassword:\u001B[0m ");
		sc.nextLine();
		emp.setPassword(sc.nextLine());
		System.out.println("\u001B[93mEmplyee name:\u001B[0m ");
		emp.setName(sc.nextLine());
		System.out.println("\u001B[93mEmployee address:\u001B[0m ");
		emp.setAddress(sc.nextLine());
		System.out.println("\u001B[93mSalary:\u001B[0m ");
		emp.setSalary(sc.nextInt());

		EmployeeDao ed = new EmployeeDaoImpl();
		System.out.println("\nPlease wait...");
		if (ed.registerEmployee(emp)) {
			System.out.println("Employee registered successfully!");
		} else {
			System.out.println("Failed to register new employee..!");
		}
		System.out.println();
	}

	private static void assignDepartment(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter employee ID:\u001B[0m ");
		int eid = sc.nextInt();
		System.out.println("\u001B[93mEnter department ID:\u001B[0m ");
		int deptId = sc.nextInt();

		EmployeeDao ed = new EmployeeDaoImpl();
		System.out.println("Please wait...");

		try {
			ed.assignDepartment(eid, deptId);
			System.out.println("Department assigned successfully!");
		} catch (EmployeeException | DepartmentException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
			System.out.println();
		}
		System.out.println();
	}

	private static void transferEmployee(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter employee ID:\u001B[0m ");
		int eid = sc.nextInt();
		System.out.println("\u001B[93mEnter new department ID:\u001B[0m ");
		int deptId = sc.nextInt();

		EmployeeDao ed = new EmployeeDaoImpl();
		System.out.println("Please wait...");

		try {
			ed.assignDepartment(eid, deptId);
			System.out.println("Employee transferred successfully!");
		} catch (EmployeeException | DepartmentException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

	private static void getEmployeeList(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\nPlease wait...");

		EmployeeDao ed = new EmployeeDaoImpl();
		try {
			List<Employee> employees = ed.getEmployeeList();
			System.out.println("================== Employees ==================");
			employees.forEach(emp -> {
				System.out.println(emp);
				System.out.println("=====================================");
				DivineUtil.sleep(timer);
			});

		} catch (EmployeeException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void getEmployeeDetails(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter employee ID:\u001B[0m ");
		int eid = sc.nextInt();

		System.out.println("\nPlease wait...");
		EmployeeDao ed = new EmployeeDaoImpl();
		try {
			Employee emp = ed.getEmployee(eid);
			System.out.println(emp);
		} catch (EmployeeException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();

	}

	private static void getLeaveRequstList(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\nPlease wait...");
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			List<Leave> leaves = ed.getLeaveRequests();
			System.out.println("================= Leave Requests =================");
			leaves.forEach(leave -> {
				System.out.println(leave);
				System.out.println("=====================================");
				DivineUtil.sleep(timer);
			});
		} catch (LeaveException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();

	}

	public static void approveLeaveRequst(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mPlease leave request ID:\u001B[0m ");
		int lid = sc.nextInt();

		System.out.println("\u001B[93mPlease select an option:\u001B[0m ");
		DivineUtil.sleep(timer);
		System.out.println("1 : Approve");
		DivineUtil.sleep(timer);
		System.out.println("2 : Cancel");
		DivineUtil.sleep(timer);
		System.out.println("\u001B[94m3 : Back.\u001B[0m");

		int x = sc.nextInt();

		if (x != 1 && x != 2)
			return;

		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.leaveApproval(lid, x == 1);
			if (x == 1)
				System.out.println("Leave accepted successfully!");
			else
				System.out.println("Leave denied successfully!");

		} catch (LeaveException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/**
	 * Manage Departments Only Admins can access
	 */
	private static void manageDepartment(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		while (true) {
			System.out.println("Please select an option: ");
			DivineUtil.sleep(timer);
			System.out.println("1 : Add new Department");
			DivineUtil.sleep(timer);
			System.out.println("2 : Delete a Department");
			DivineUtil.sleep(timer);
			System.out.println("3 : Get Department details");
			DivineUtil.sleep(timer);
			System.out.println("4 : Get Department list");
			DivineUtil.sleep(timer);
			System.out.println("5 : Update Department details");
			DivineUtil.sleep(timer);
			System.out.println("\u001B[94m6 : Back.\u001B[0m");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception ignore) {
			}
			if (choice == 1) {
				addNewDepartment(user);
			} else if (choice == 2) {
				deleteDepartment(user);
			} else if (choice == 3) {
				getDepartment(user);
			} else if (choice == 4) {
				getDepartmentList(user);
			} else if (choice == 5) {
				updateDeptDetails(user);
			} else if (choice == 6) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					closeProgramm();
				} else {
					System.out.println("Unknown option selected..!");
					closeProgramm();
				}
			}

		}
	}

	private static void addNewDepartment(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("Please enter Department details: ");
		Department dept = new Department();

		System.out.println("\u001B[93mDepartment name:\u001B[0m ");
		sc.nextLine();
		dept.setName(sc.nextLine());
		System.out.println("\u001B[93mDepartment location:\u001B[0m ");
		dept.setLocation(sc.nextLine());

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			dd.addDepartment(dept);
			System.out.println("Department added successfully!");
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void deleteDepartment(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter Department ID to delete:\u001B[0m ");
		int deptId = sc.nextInt();

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			dd.deleteDepartment(deptId);
			System.out.println("Department deleted successfully!");
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void getDepartment(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		try {
			System.out.println("\u001B[93mEnter Department ID:\u001B[0m ");
			int deptId = sc.nextInt();

			DepartmentDao dd = new DepartmentDaoImpl();
			Department dept = dd.getDepartment(deptId);
			System.out.println(dept);
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void getDepartmentList(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			List<Department> departments = dd.getDepartmentList();
			System.out.println("================== Departments ==================");
			departments.forEach(dept -> {
				System.out.println(dept);
				System.out.println("=====================================");
				DivineUtil.sleep(timer);
			});
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void updateDeptDetails(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("Please select an option: ");
		DivineUtil.sleep(timer);
		System.out.println("1 : Update Department name");
		DivineUtil.sleep(timer);
		System.out.println("2 : Update Department location");
		DivineUtil.sleep(timer);
		System.out.println("\u001B[94m3 : Back.\u001B[0m");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (Exception ignore) {
		}
		if (choice == 1) {

		} else if (choice == 2) {

		} else if (choice == 3) {
			return;
		} else {
			System.out.println("Unknown option selected..!");
			int choise = tryAgain();
			if (choise == 1) {
				updateDeptDetails(user);
			} else if (choise == 2) {
				DivineUtil.sleep(timer);
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				closeProgramm();
			}
		}

	}

	public static void updateDeptName(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mPlease enter Department ID:\u001B[0m ");
		int deptId = sc.nextInt();
		System.out.println("\u001B[93mEnter new Department name:\u001B[0m ");
		sc.nextLine();
		String name = sc.nextLine();

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			dd.changeDeptName(deptId, name);
			System.out.println("Department name updated successfully!");
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	public static void updateDeptLoaction(Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mPlease enter Department ID:\u001B[0m ");
		int deptId = sc.nextInt();
		System.out.println("\u001B[93mEnter new Department location:\u001B[0m ");
		sc.nextLine();
		String loaction = sc.nextLine();

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			dd.changeDeptLocation(deptId, loaction);
			System.out.println("Department location updated successfully!");
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	/**
	 * Update profile Accessible to everyone who is logged in
	 */
	private static void updateProfile(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		while (true) {
			System.out.println("Please select an option:");
			DivineUtil.sleep(timer);
			System.out.println("1 : Update name");
			DivineUtil.sleep(timer);
			System.out.println("2 : Update address");
			DivineUtil.sleep(timer);
			System.out.println("3 : Change email");
			DivineUtil.sleep(timer);
			System.out.println("4 : Change password");
			DivineUtil.sleep(timer);
			System.out.println("\u001B[94m5 : Back.\u001B[0m");
			DivineUtil.sleep(timer);

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception ignore) {
			}
			if (choice == 1) {
				updateName(user);
			} else if (choice == 2) {
				updateAddress(user);
			} else if (choice == 3) {
				changeEmail(user);
			} else if (choice == 4) {
				changePassword(user);
			} else if (choice == 5) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					closeProgramm();
				} else {
					System.out.println("Unknown option selected..!");
					closeProgramm();
				}
			}
		}
	}

	private static void updateName(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter your name:\u001B[0m ");
		sc.nextLine();
		String name = sc.nextLine();

		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.updateName(user.getId(), name);
			employee.setName(name);
			System.out.println("Name updated successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void updateAddress(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter your new address:\u001B[0m ");
		sc.nextLine();
		String address = sc.nextLine();

		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.updateAddress(user.getId(), address);
			employee.setAddress(address);
			System.out.println("Address updated successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void changeEmail(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter your new email:\u001B[0m ");
		String email = sc.next();

		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.changeEmail(user.getId(), email);
			employee.setEmail(email);
			System.out.println("Email changed successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void changePassword(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mEnter your new password:\u001B[0m ");
		sc.nextLine();
		String pass = sc.nextLine();

		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.changePassword(user.getId(), pass);
			employee.setPassword(pass);
			System.out.println("Password changed successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Employee track
	 */
	private static void employeeTrack(Employee user) throws EmployeeException {
		System.out.println("Welcome " + employee.getName() + " to your dashboard!");
		DivineUtil.sleep(timer);
		System.out.println("===============================\n");
		DivineUtil.sleep(500);

		while (true) {

			System.out.println("Please select an option:");
			DivineUtil.sleep(timer);
			System.out.println("1 : Show profile");
			DivineUtil.sleep(timer);
			System.out.println("2 : Update profile");
			DivineUtil.sleep(timer);
			System.out.println("3 : Request for a leave");
			DivineUtil.sleep(timer);
			System.out.println("\u001B[91m4 : Log out.");
			DivineUtil.sleep(timer);
			System.out.println("5 : Exit.\u001B[0m");

			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (Exception ignore) {
			}
			if (choice == 1) {
				displayProfile(user);
			} else if (choice == 2) {
				updateProfile(user);
			} else if (choice == 3) {
				requestLeave(user);
			} else if (choice == 4) {
				employee = null;
				System.out.println("Logged out successfully!\n\n");
				main(null);
				return;
			} else if (choice == 5) {
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					closeProgramm();
				} else {
					System.out.println("Unknown option selected..!");
					closeProgramm();
				}
			}

		}
	}

	private static void requestLeave(Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\u001B[93mPlease write a leave message in 1 line:\u001B[0m ");
		sc.nextLine();
		String msg = sc.nextLine();

		try {

			Leave leave = new Leave();
			leave.setEid(user.getId());
			leave.setDeptId(user.getDeptId());
			leave.setLeaveMsg(msg);

			EmployeeDao ed = new EmployeeDaoImpl();
			ed.requestLeave(leave);
			System.out.println("Leave request sent successfully!");

		} catch (Exception e) {
			System.out.println("Something went you wrong: " + e.getMessage());
			System.out.println("Please try again.");
		}
		System.out.println();
	}

	private static void displayProfile(Employee emp) {
		DivineUtil.sleep(timer);
		System.out.println("================== Your Profile ==================");
		DivineUtil.sleep(timer);
		System.out.println(" Name: " + emp.getName());
		DivineUtil.sleep(timer);
		System.out.println(" Email: " + emp.getEmail());
		DivineUtil.sleep(timer);
		System.out.println(" Address: " + emp.getAddress());
		DivineUtil.sleep(timer);
		System.out.println(" Role: " + emp.getRole());
		DivineUtil.sleep(timer);
		System.out.println(" UID: " + emp.getId());
		DivineUtil.sleep(timer);
		System.out.println(" Salary: " + emp.getSalary());

		DivineUtil.sleep(timer);
		if (emp.getDeptId() == 0 || emp.getDeptId() == -1) {
			System.out.println(" You are not assigned to any department!\n");
			return;
		}

		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			Department dept = dd.getDepartment(emp.getDeptId());
			System.out.println("Department Name: " + dept.getName());
			System.out.println("Department location: " + dept.getLocation());
		} catch (DepartmentException e) {
			System.out.println("Unable to load department details: " + e.getMessage());
		}
		System.out.println();
	}

}
