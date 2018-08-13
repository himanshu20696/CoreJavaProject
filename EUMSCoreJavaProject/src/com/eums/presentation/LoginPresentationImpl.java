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
	static int loginAttempt=3;
	
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
				System.out.println(result);
				loginAttempt--;
				if(loginAttempt > 0)
				{
					System.out.println("You Have "+loginAttempt+" Login Attempts Left");
					login();
				}
				else
				{
					System.out.println("You Are Being Taken Back To Main Menu");
					loginAttempt=3;
					mainMenu();
				}
			}
			else if(result.equals("Incorrect Password Please Try Again"))
			{
				System.out.println(result);
				loginAttempt--;
				if(loginAttempt > 0)
				{
					System.out.println("You Have "+loginAttempt+" Login Attempts Left");
					login();
				}
				else
				{
					System.out.println("You Are Being Taken Back To Main Menu");
					loginAttempt=3;
					mainMenu();
				}
			}
			else
			{
				System.out.println(result);
				String etype = loginAuthenticationService.checkEmployeeType(employeeId);
				if(etype.equals("EMP"))
				{
					employeePresentation.showEmployeeMenu(employeeId);
				}
				else
				{
					hrPresentation.showHRMenu(employeeId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mainMenu() {
		System.out.println("Welcome To Employee Upgradation Management System");
		System.out.println("Please Choose Any Option");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1:
			login();
			break;
			
		case 2:
			System.out.println("You Selected Exit, Program Will Not Terminate Bye");
			System.exit(0);
			break;
			
		default:
			System.out.println("Invalid Choice! Please Try Again");
			break;
		}
	}
}
