package com.eums.service;

import java.sql.SQLException;

public interface LoginAuthenticationService {
	String authenticateEmployee(String employeeId, String password) throws SQLException;
	String checkEmployeeType(String employeeId) throws SQLException;
}
