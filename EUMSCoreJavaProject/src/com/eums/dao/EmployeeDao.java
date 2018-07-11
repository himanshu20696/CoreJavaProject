package com.eums.dao;

import java.sql.SQLException;
import java.util.List;

import com.eums.beans.Employee;

public interface EmployeeDao {
	
	List<Employee> listAllRecords() throws SQLException;
	Employee searchRecord(int employeeId) throws SQLException;
	boolean insertRecord(Employee employee) throws SQLException;
	boolean deleteRecord(int employeeId) throws SQLException;
	boolean updateRecord(int employeeId,Employee newEmployee) throws SQLException;

}
