package com.eums.presentation;

import java.sql.SQLException;
import java.util.Scanner;

import com.eums.service.LoginAuthenticationService;
import com.eums.service.LoginAuthenticationServiceImpl;

public class LoginPresentationImpl implements LoginPresentation {

	Scanner sc = new Scanner(System.in);
	LoginAuthenticationService loginAuthenticationService = new LoginAuthenticationServiceImpl();
	EmployeePresentation employeePresentation = new EmployeePresentationImpl();
	HRPresentation hrPresentation = new HRPresentationImpl();
	
	@Override
	public void login() {
		System.out.println("Welcome To Employee Upgradation Management System");
		System.out.println("Enter Employee ID");
		String employeeId = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();	
		try {
			String result = loginAuthenticationService.authenticateEmployee(employeeId, password);
			if(result.equals("Employee Not Found Please Try Again!!"))
			{
				login();
			}
			else if(result.equals("Incorrect Password Please Try Again"))
			{
				login();
			}
			else
			{
				System.out.println(result);
				Thread.sleep(2000);
				String etype = loginAuthenticationService.checkEmployeeType(employeeId);
				if(etype.equals("EMP"))
				{
					employeePresentation.showEmployeeMenu();
				}
				else
				{
					hrPresentation.showHRMenu();
				}
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}