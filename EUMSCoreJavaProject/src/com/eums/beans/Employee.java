package com.eums.beans;

//Employee POJO

public class Employee {
	private String employeeID;
	private String employeeName;
	private String employeeEmail;
	private String employeePassword;
	private String employeeType;
	private String employeeTeam;
	private String employeeAddress;
	private String employeeContactNo;

	public Employee() {

	}

	public Employee(String employeeID, String employeeName, String employeeEmail, String employeePassword,
			String employeeType, String employeeTeam, String employeeAddress, String employeeContactNo) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeePassword = employeePassword;
		this.employeeType = employeeType;
		this.employeeTeam = employeeTeam;
		this.employeeAddress = employeeAddress;
		this.employeeContactNo = employeeContactNo;
	}

	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getEmployeeTeam() {
		return employeeTeam;
	}
	public void setEmployeeTeam(String employeeTeam) {
		this.employeeTeam = employeeTeam;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeeContactNo() {
		return employeeContactNo;
	}
	public void setEmployeeContactNo(String employeeContactNo) {
		this.employeeContactNo = employeeContactNo;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeTeam=" + employeeTeam + ", employeeAddress=" + employeeAddress
				+ ", employeeContactNo=" + employeeContactNo + "]";
	}	
}
