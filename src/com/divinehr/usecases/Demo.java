package com.divinehr.usecases;

import java.util.Scanner;

import com.divinehr.dao.EmployeeDao;
import com.divinehr.dao.EmployeeDaoImpl;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.model.Employee;
import com.divinehr.utility.DivineUtil;

public class Demo {

	static Employee employee = null;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Hi there! ");
		DivineUtil.sleep(300);
		System.out.println("Welcome to Divine HR!");
		DivineUtil.sleep(200);
		System.out.println("===============================\n");
		DivineUtil.sleep(500);
		System.out.println("Please login to continue...");
		
		DivineUtil.sleep(200);
		employee = askLogin(sc);
		if (employee == null ) {
			System.out.println("Failed to login..!");
			DivineUtil.sleep(200);
			System.out.println("\nProgramm closed!");
			DivineUtil.sleep(200);
			System.out.println("Thanks for using \"Divine HR\"");
			return;
		}
		
		System.out.println("Logged in sucessfully");
		
		if (employee.getRole().equals("Admin")) 
			adminTrack(sc);
		else 
			employeeTrack(sc);
		
	}
	
	static int tryAgain(Scanner sc) {
		try {
			System.out.println("Please select an option:");
			DivineUtil.sleep(200);
			System.out.println("1 : Try again...");
			DivineUtil.sleep(200);
			System.out.println("2 : Exit.");
			return sc.nextInt();
		} catch (Exception e) {
			return -1;
		}
	}
	
	static Employee askLogin(Scanner sc) {
		
		Employee emp = null;
		
		System.out.println("Enter your email id: ");
		String email = sc.next();
		if (!DivineUtil.validateEmail(email)) {
			DivineUtil.sleep(200);
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
		String pass = sc.next();

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
	
	static void adminTrack(Scanner sc) {
		System.out.println("Welcome " + employee.getName() + " to your admin panel!");
		DivineUtil.sleep(200);
		System.out.println("===============================\n");
		
		while (true) {
			
			DivineUtil.sleep(200);
//			TODO to be continued
			
		}
	}
	
	static void employeeTrack(Scanner sc) {
		System.out.println("Welcome " + employee.getName() + " to your dashboard!");
		DivineUtil.sleep(200);
		System.out.println("===============================\n");
		
		while (true) {
			
			DivineUtil.sleep(200);
//			TODO to be continued
			
		}
	}
	
}
