package com.divinehr.model;

public class Employee {
	private int id, salary, deptId;
	private String name, email, password, address, role;
	
	public Employee() {}

	public Employee(int id, int salary, int deptId, String name, String email, String password, String address,
			String role) {
		super();
		this.id = id;
		this.salary = salary;
		this.deptId = deptId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		String dept = "";
		if (deptId != 0) {
			dept = "\nEmployee department ID: " + deptId;
		} else {
			dept = "\nDepartment not assigned";
		}
		return "Employee ID: " + id
				+ "\nEmployee name: " + name
				+ "\nEmployee email: " + email
				+ "\nEmployee address: " + address
				+ "\nEmployee salary: " + salary
				+ "\nEmployee role: " + role
				+ dept;
	}
	
}
