package com.divinehr.usecases;

import java.util.Scanner;

import com.divinehr.dao.EmployeeDao;
import com.divinehr.dao.EmployeeDaoImpl;
import com.divinehr.exceptions.EmployeeException;
import com.divinehr.model.Employee;
import com.divinehr.model.Leave;
import com.divinehr.utility.DivineUtil;

/**
 * 
 * This is the track where a employee enters as soon they log in to the system.
 *
 */
public class EmployeeService extends BasicUserService {

	private Scanner sc = new Scanner(System.in);
	private Employee employee;
	private int timer = 100;

	public EmployeeService(Employee employee) {
		super(employee);
		this.employee = employee;
	}

	/**
	 * This is the start of this track**
	 * 
	 * @param It takes employee details
	 *
	 */
	public void employeeTrack(Employee user) throws EmployeeException {
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
				Demo.main(null);
				return;
			} else if (choice == 5) {
				Demo.closeProgramm();
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

	private void requestLeave(Employee user) throws EmployeeException {
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

}
