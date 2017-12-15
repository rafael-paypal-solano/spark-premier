package com.apache.spark.format;

import java.io.Serializable;
import java.sql.Timestamp;

public class PersonDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6688616256958040801L;

	private Integer cid;
	private String county;
	private String firstName;
	private String sex;
	private int year;
	private Timestamp dateOfBirth;
	
	public PersonDetails(Integer cid, String county, String firstName, String sex, int year, Timestamp dateOfBirth) {
		super();
		this.cid = cid;
		this.county = county;
		this.firstName = firstName;
		this.sex = sex;
		this.year = year;
		this.dateOfBirth = dateOfBirth;
	}
	
	public PersonDetails() {
		
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "PersonDetails [cid=" + cid + ", county=" + county + ", firstName=" + firstName + ", sex=" + sex
				+ ", year=" + year + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
