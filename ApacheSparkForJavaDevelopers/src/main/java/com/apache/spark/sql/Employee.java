package com.apache.spark.sql;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5155883787778690534L;
	Integer id;
	String name; 
	String department;
	
	
	public Employee(Integer id, String name, String department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}
	
	public Employee() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
}
