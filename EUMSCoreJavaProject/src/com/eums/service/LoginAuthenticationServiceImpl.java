package com.eums.service;

import java.sql.SQLException;

import com.eums.beans.Employee;
import com.eums.dao.EmployeeDao;
import com.eums.dao.EmployeeDaoImpl;

public class LoginAuthenticationServiceImpl implements LoginAuthenticationService {

	EmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public String authenticateEmployee(String employeeId, String password) throws SQLException {
		Employee employee = employeeDao.searchRecord(employeeId);
		if(employee == null)
		{
			return "Employee Not Found Please Try Again!!";
		}
		else
		{
			if(employee.getEmployeePassword().equals(password))
			{
				return "Welcome "+employee.getEmployeeName();
			}
			else
			{
				return "Incorrect Password Please Try Again";
			}
		}
	}

	@Override
	public String checkEmployeeType(String employeeId) throws SQLException {
		Employee employee = employeeDao.searchRecord(employeeId);
		return employee.getEmployeeType();
	}

}
