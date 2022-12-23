package com.divinehr.usecases;

import java.util.Scanner;

import com.divinehr.dao.DepartmentDao;
import com.divinehr.dao.DepartmentDaoImpl;
import com.divinehr.dao.EmployeeDao;
import com.divinehr.dao.EmployeeDaoImpl;
import com.divinehr.exceptions.DepartmentException;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.model.Department;
import com.divinehr.model.Employee;
import com.divinehr.utility.DivineUtil;

/**
 * 
 * This class contains all the common methods of both Amin and Employee
 *
 */
public class BasicUserService {

	private Scanner sc = new Scanner(System.in);
	private Employee employee;
	private int timer = 100;

	public BasicUserService(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Update profile Accessible to everyone who is logged in
	 */
	public void updateProfile(Employee user) throws EmployeeException {
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
				int choise = Demo.tryAgain();
				if (choise == 1) {
					continue;
				} else if (choise == 2) {
					DivineUtil.sleep(timer);
					Demo.closeProgramm();
				} else {
					System.out.println("Unknown option selected..!");
					Demo.closeProgramm();
				}
			}
		}
	}

	public void updateName(Employee user) throws EmployeeException {
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

	public void updateAddress(Employee user) throws EmployeeException {
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

	public void changeEmail(Employee user) throws EmployeeException {
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

	public void changePassword(Employee user) throws EmployeeException {
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

	public void displayProfile(Employee emp) {
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
