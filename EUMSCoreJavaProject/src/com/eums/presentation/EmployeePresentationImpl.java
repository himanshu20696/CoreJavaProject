package com.eums.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.eums.beans.Feedback;
import com.eums.beans.Training;
import com.eums.helper.InputTrainingDetails;
import com.eums.service.EmployeeService;
import com.eums.service.EmployeeServiceImpl;

public class EmployeePresentationImpl implements EmployeePresentation {
	
	EmployeeService employeeService=new EmployeeServiceImpl();
	InputTrainingDetails inputTrainingDetails = new InputTrainingDetails();
	Scanner sc=new Scanner(System.in);
	
	@Override
	public void showEmployeeMenu(String employeeId) {
//		try {
//			employeeService.feedbackDisablement(employeeId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		System.out.println(" Employee MENU");
		System.out.println("=========");
		System.out.println("1. View Upcomming Trainings");
		System.out.println("2. View Enrolled Trainings");
		System.out.println("3. Fill Feedback For Training");
		System.out.println("4. Log Out");
		System.out.println("Choose Any One :- ");
		int choice=sc.nextInt();
		actionPerformed(choice, employeeId);
	}

	@Override
	public void actionPerformed(int choice, String employeeId) {
		switch(choice)
		{
		case 1: try {
			ArrayList<Training> upcommingTrainingList=new ArrayList<>();
			upcommingTrainingList=employeeService.viewUpcommingTraining();
			for(Training list:upcommingTrainingList)
			{
				System.out.println(list);
			}
			System.out.println("Do You Want To Enroll For Any Training (Yes/No)");
			String enrollChoice = sc.next();
			if(enrollChoice.equalsIgnoreCase("yes"))
			{
				System.out.println("Enter Training Id For Which You Wanna Enroll :- ");
				int trainingId=sc.nextInt();
				boolean status=employeeService.enrollForTraining(trainingId, employeeId);
				if(status)
					System.out.println("Request For Enrollment Has Been Sent To HR. You Will Be Notified Shortly");
				else
					System.out.println("Some Problem Please Try Again");
			}
			else if(enrollChoice.equalsIgnoreCase("no"))
			{
				showEmployeeMenu(employeeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		break;
		
		case 2: 
			inputTrainingDetails.showEnrolledTrainings(employeeId);
		break;
		
		case 3:
			inputTrainingDetails.showEnrolledTrainings(employeeId);
			System.out.println("Enter Training Id For Feedback :- ");
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

			Feedback feedback=new Feedback(employeeId, tId, coverageoftopics, effectivenessofcource, presentationstyle, paceofdelivery, courceoverall, traineroverall);
			try {
				boolean status=employeeService.feedbackFilling(feedback);
				if(status)
					System.out.println("Feedback Submitted");
				else
					System.out.println("Feedback not submitted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		break;
		
		case 4:
			System.out.println("You Have Successfully Logged Out!");
			LoginPresentation loginPresentation = new LoginPresentationImpl();
			loginPresentation.login();
		break;
		
		default: 
			System.out.println("Invalid Input !!!!");
		}

		showEmployeeMenu(employeeId);
	}

}
