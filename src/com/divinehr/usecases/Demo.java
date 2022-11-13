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

public class Demo {

	static Employee employee = null;
	static int timer = 100;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Hi there! ");
		DivineUtil.sleep(300);
		System.out.println("Welcome to Divine HR!");
		DivineUtil.sleep(timer);
		System.out.println("===============================\n");
		DivineUtil.sleep(500);
		System.out.println("Please login to continue...");
		
		DivineUtil.sleep(timer);
		employee = askLogin(sc);
		if (employee == null ) {
			System.out.println("Failed to login..!");
			DivineUtil.sleep(timer);
			System.out.println("\nProgramm closed!");
			DivineUtil.sleep(timer);
			System.out.println("Thanks for using \"Divine HR\"");
			return;
		}
		
		System.out.println("Logged in sucessfully");
		try {
			if (employee.getRole().equals("Admin")) {
				adminTrack(sc, employee);
			} else {
				employeeTrack(sc, employee);
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			closeProgramm();
		}
	}
	
	static int tryAgain(Scanner sc) {
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
	
	public static void closeProgramm() {
		System.out.println("Programm closed!");
		DivineUtil.sleep(timer);
		System.out.println("Thanks for using \"Divine HR\"");
		Thread.currentThread().stop();
	}
	
	static Employee askLogin(Scanner sc) {
		
		Employee emp = null;
		
		System.out.println("Enter your email id: ");
		String email = sc.next();
		if (!DivineUtil.validateEmail(email)) {
			DivineUtil.sleep(timer);
			System.out.println("Invalid email: " + email);
			
			System.out.println();
			int choice = tryAgain(sc);
			if (choice == 1) {
				emp = askLogin(sc);
			} else if (choice == 2) {
				return null;
			} else {
				System.out.println("Unknown option: " + choice + " selected..!");
				return null;
			}
		}
		System.out.println("Enter your password: ");
		sc.nextLine();
		String pass = sc.nextLine();

		EmployeeDao ed = new EmployeeDaoImpl();
		
		try {
			System.out.println("\nProcessing credential...");
			emp = ed.login(email, pass);
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
			System.out.println();
			
			int choice = tryAgain(sc);
			if (choice == 1) {
				emp = askLogin(sc);
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
	 * Admin track
	 */
	static void adminTrack(Scanner sc, Employee user) throws Exception {
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
			System.out.println("1 : Manage Employees.");
			DivineUtil.sleep(timer);
			System.out.println("2 : Manage Departments.");
			DivineUtil.sleep(timer);
			System.out.println("3 : Update your profile.");
			DivineUtil.sleep(timer);
			System.out.println("4 : Show profile.");
			DivineUtil.sleep(timer);
			System.out.println("5 : Exit.");;
			
			int choice = sc.nextInt(); 
			if (choice == 1) {
				manageEmployee(sc, user);
			} else if (choice == 2) {
				manageDepartment(sc, user);
			} else if (choice == 3) {
				updateProfile(sc, user);
			} else if (choice == 4) {
				displayProfile(user);
			} else if (choice == 5) {
				DivineUtil.sleep(timer);
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain(sc);
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
	 * Manage Employee
	 * Only Admins can access
	 */
	private static void manageEmployee(Scanner sc, Employee user) throws Exception {
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
			
			System.out.println("8 : Back.");
			
			int choice = sc.nextInt();
			if (choice == 1) {
				addNewEmployee(sc, user);
			} else if (choice == 2) {
				assignDepartment(sc, user);
			} else if (choice == 3) {
				transferEmployee(sc, user);
			} else if (choice == 4) {
				getEmployeeList(sc, user);
			} else if (choice == 5) {
				getEmployeeDetails(sc, user);
			} else if (choice == 6) {
				getLeaveRequstList(sc, user);
			} else if (choice == 7) {
				
			} else if (choice == 8) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain(sc);
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
	
	public static void addNewEmployee(Scanner sc, Employee user) throws Exception {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		Employee emp = new Employee();
		
		System.out.println("Please enter employee details:");
		DivineUtil.sleep(timer);
		
		System.out.println("Email: ");
		String email = sc.next();
		if (!DivineUtil.validateEmail(email)) {
			DivineUtil.sleep(timer);
			System.out.println("Invalid email: " + email);
			
			int choice = tryAgain(sc);
			if (choice == 1) {
				addNewEmployee(sc, user);
				return;
			} else if (choice == 2) {
				return;
			} else {
				throw new Exception("Unknown option selected...!");
			}
		}
		emp.setEmail(email);
		System.out.println("Password: ");
		sc.nextLine();
		emp.setPassword(sc.nextLine());
		System.out.println("Emplyee name: ");
		emp.setName(sc.nextLine());
		System.out.println("Employee address: ");
		emp.setAddress(sc.nextLine());
		System.out.println("Salary: ");
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
	
	public static void assignDepartment(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("Enter employee ID: ");
		int eid = sc.nextInt();
		System.out.println("Enter department ID: ");
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
	
	public static void transferEmployee(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter employee ID: ");
		int eid = sc.nextInt();
		System.out.println("Enter new department ID: ");
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
	
	public static void getEmployeeList(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}

		System.out.println("\nPlease wait...");
		
		EmployeeDao ed = new EmployeeDaoImpl();
		try {
			List<Employee> employees = ed.getEmployeeList();
			System.out.println("========== Employees =========");
			employees.forEach(emp -> {
				System.out.println(emp);
				DivineUtil.sleep(timer);
			});
			System.out.println("===============================");
			
		} catch (EmployeeException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	public static void getEmployeeDetails(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter employee ID: ");
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
	
	public static void getLeaveRequstList(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("\nPlease wait...");
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			List<Leave> leaves = ed.getLeaveRequests();
			leaves.forEach(System.out::println);
		} catch (LeaveException e) {
			System.out.println("Something went you wrong..!");
			System.out.println(e.getMessage());
		}
		System.out.println();
		
	}
	
	public static void approveLeaveRequst(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please leave request ID: ");
		int lid = sc.nextInt();
		
		System.out.println("Please select an option: ");
		DivineUtil.sleep(timer);
		System.out.println("1 : Approve");
		DivineUtil.sleep(timer);
		System.out.println("2 : Cancel");
		DivineUtil.sleep(timer);
		System.out.println("3 : Back.");
		
		int x = sc.nextInt();
		
		if (x != 1 && x != 2) return;
		
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
	 * Manage Departments
	 * Only Admins can access
	 */
	private static void manageDepartment(Scanner sc, Employee user) throws EmployeeException {
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
			System.out.println("6 : Back.");
			
			int choice = sc.nextInt();
			if (choice == 1) {
				addNewDepartment(sc, user);
			} else if (choice == 2) {
				deleteDepartment(sc, user);
			} else if (choice == 3) {
				getDepartment(sc, user);
			} else if (choice == 4) {
				getDepartmentList(sc, user);
			} else if (choice == 5) {
				updateDeptDetails(sc, user);
			} else if (choice == 6) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain(sc);
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
	
	static void addNewDepartment(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please enter Department details: ");
		Department dept = new Department();
		
		System.out.println("Department name: ");
		sc.nextLine();
		dept.setName(sc.nextLine());
		System.out.println("Department location: ");
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
	
	static void deleteDepartment(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter Department ID to delete: ");
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
	
	static void getDepartment(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		try {
			System.out.println("Enter Department ID: ");
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
	
	static void getDepartmentList(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		DepartmentDao dd = new DepartmentDaoImpl();
		try {
			List<Department> departments = dd.getDepartmentList();
			System.out.println("\n========= Departments ==========");
			departments.forEach(System.out::println);
			System.out.println("================================");
		} catch (DepartmentException e) {
			System.out.println("Something went you wrong!");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	
	private static void updateDeptDetails(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please select an option: ");
		DivineUtil.sleep(timer);
		System.out.println("1 : Update Department name");
		DivineUtil.sleep(timer);
		System.out.println("2 : Update Department location");
		DivineUtil.sleep(timer);
		System.out.println("3 : Back.");
		
		int choice = sc.nextInt();
		if (choice == 1) {
			
		} else if (choice == 2) {
			
		} else if (choice == 3) {
			return;
		} else {
			System.out.println("Unknown option selected..!");
			int choise = tryAgain(sc);
			if (choise == 1) {
				updateDeptDetails(sc, user);
			} else if (choise == 2) {
				DivineUtil.sleep(timer);
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				closeProgramm();
			}
		}
		
	}
	
	static void updateDeptName(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please enter Department ID: ");
		int deptId = sc.nextInt();
		System.out.println("Enter new Department name: ");
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
	
	static void updateDeptLoaction(Scanner sc, Employee user) throws EmployeeException {
		if (!user.getRole().equals("Admin")) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please enter Department ID: ");
		int deptId = sc.nextInt();
		System.out.println("Enter new Department location: ");
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
	 * Update profile
	 * Accessible to everyone who is logged in
	 */
	private static void updateProfile(Scanner sc, Employee user) throws EmployeeException{
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
			System.out.println("5 : Back.");
			DivineUtil.sleep(timer);
			
			int choice = sc.nextInt();
			if (choice == 1) {
				updateName(sc, user);
			} else if (choice == 2) {
				updateAddress(sc, user);
			} else if (choice == 3) {
				changeEmail(sc, user);
			} else if (choice == 4) {
				changePassword(sc, user);
			} else if (choice == 5) {
				return;
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain(sc);
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
	
	static void updateName(Scanner sc, Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter your name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.updateName(user.getId(), name);
			System.out.println("Name updated successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void updateAddress(Scanner sc, Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter your new address: ");
		sc.nextLine();
		String address = sc.nextLine();
		
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.updateAddress(user.getId(), address);
			System.out.println("Address updated successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void changeEmail(Scanner sc, Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter your new email: ");
		String email = sc.next();
		
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.changeEmail(user.getId(), email);
			System.out.println("Email changed successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void changePassword(Scanner sc, Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Enter your new password: ");
		sc.nextLine();
		String pass = sc.nextLine();
		
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			ed.changePassword(user.getId(), pass);
			System.out.println("Password changed successfully!");
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Employee track
	 */
	private static void employeeTrack(Scanner sc, Employee user) throws EmployeeException{
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
			System.out.println("4 : Exit.");
			
			int choice = sc.nextInt();
			if (choice == 1) {
				displayProfile(user);
			} else if (choice == 2) {
				updateProfile(sc, user);
			} else if (choice == 3) {
				
			} else if (choice == 4) {
				closeProgramm();
			} else {
				System.out.println("Unknown option selected..!");
				int choise = tryAgain(sc);
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
	
	static void requestLeave(Scanner sc, Employee user) throws EmployeeException {
		if (user == null) {
			throw new EmployeeException("Access denied!");
		}
		
		System.out.println("Please write a leave message in 1 line: ");
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
	
	public static void displayProfile(Employee emp) {
		DivineUtil.sleep(timer);
		System.out.println("============== Your Profile =============");
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
			System.out.println("You are not assigned to any department!\n");
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

