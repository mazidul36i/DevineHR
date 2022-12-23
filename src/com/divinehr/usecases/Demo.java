package com.divinehr.usecases;

import java.util.Scanner;

import com.divinehr.dao.EmployeeDao;
import com.divinehr.dao.EmployeeDaoImpl;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.model.Employee;
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
				new AdminService(employee).adminTrack(employee); // If logged in user is an admin, then take him/her
																	// throw admin track
			} else {
				new EmployeeService(employee).employeeTrack(employee); // If logged in user is an employee, then take
																		// him/her throw employee track
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
	public static int tryAgain() {
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
	public static void closeProgramm() {
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

}
