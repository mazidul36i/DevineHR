package com.divinehr.model;

/**
 * 
 * Department model class<br>
 * No need to specify id as it is auto-generated by SQL
 */
public class Department {

	private int id;
	private String name, location;

	public Department() {
	}

	public Department(int id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department ID: " + id + "\nDepartment name: " + name + "\nDepartment location: " + location;
	};

}
