package com.eums.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.eums.beans.*;
import com.eums.service.*;

public class EmployeePresentationImpl implements EmployeePresentation {
	EmployeeService employeeService=new EmployeeServiceImpl();
	Scanner sc=new Scanner(System.in);
	@Override
	public void showEmployeeMenu() {
		System.out.println("1. Upcomming trainings.");
		System.out.println("2. Show Enrolled trainings.");
		System.out.println("3. Fill Feedback. ");
		System.out.println("4. Log Out.");
		System.out.println("Choose any one :- ");
		
		int choice=sc.nextInt();
		actionPerformed(choice);
	}

	@Override
	public void actionPerformed(int choice) {
		
		switch(choice)
		{
		case 1: try {
			    System.out.println("Enter your Employee Id :- ");
			    String employeeId=sc.next();
			    ArrayList<Training> upcommingTrainingList=new ArrayList<>();
				upcommingTrainingList=employeeService.viewUpcommingTraining();
				for(Training list:upcommingTrainingList)
				{
					System.out.println(list);
				}
				System.out.println("Enter the training id :- ");
				int trainingId=sc.nextInt();
				boolean status=employeeService.enrollForTraining(trainingId, employeeId);
				if(status)
				{
					System.out.println("You are enrolled.");
				}
				else
				System.out.println("Enrollment Rejected.");
				
				break;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		case 2:ArrayList<Training> enrolledTrainingList=new ArrayList<>();
		System.out.println("Enter your Employee Id :- ");
		String employeeId=sc.next();
			try {
				enrolledTrainingList=employeeService.viewEnrolledTraining(employeeId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("You are enrolled for following trainings");
		for(Training list:enrolledTrainingList)
		{
			System.out.println(list); 
		}
			break;
		case 3:  
			System.out.println("Enter your Employee Id :- ");
			String eId=sc.next();
		System.out.println("Enter Training Id :- ");
			int tId=sc.nextInt();
			System.out.println("Enter Coverage of topics Rating(1-5) :- ");
			int coverageoftopics=sc.nextInt();
			System.out.println("Enter Effectiveness of topics Rating(1-5) :- ");
			int effectivenessofcource=sc.nextInt();
			System.out.println("Enter Presentation Style Rating(1-5) :- ");
			int presentationstyle=sc.nextInt();
			System.out.println("Enter Pace of delivery of topics Rating(1-5) :- ");
			int paceofdelivery=sc.nextInt();

			int courceoverall=(coverageoftopics+effectivenessofcource)/2;
			int traineroverall=(presentationstyle+paceofdelivery)/2;
			
			Feedback feedback=new Feedback(eId, tId, coverageoftopics, effectivenessofcource, presentationstyle, paceofdelivery, courceoverall, traineroverall);
			try {
				boolean status=employeeService.feedbackFilling(feedback);
				if(status)
					System.out.println("Feedback Submitted");
				else
					System.out.println("Feedback not submitted");
				break;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		case 4:
			//login presentation layer
		
		break;
		default: 
			System.out.println("Invalid Input !!!!");
		}
		
		showEmployeeMenu();
		
		
			

	}

}
