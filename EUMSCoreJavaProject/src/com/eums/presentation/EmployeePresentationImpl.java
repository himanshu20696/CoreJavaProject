package com.eums.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.eums.beans.Feedback;
import com.eums.beans.Training;
import com.eums.helper.InputTrainingDetails;
import com.eums.service.EmployeeService;
import com.eums.service.EmployeeServiceImpl;
import com.eums.service.HRService;
import com.eums.service.HRServiceImpl;

public class EmployeePresentationImpl implements EmployeePresentation {
	
	EmployeeService employeeService=new EmployeeServiceImpl();
	HRService hrService = new HRServiceImpl();
	InputTrainingDetails inputTrainingDetails = new InputTrainingDetails();
	Scanner sc=new Scanner(System.in);
	
	@Override
	public void showEmployeeMenu(String employeeId) {
		LoginPresentationImpl.loginAttempt=3;
		//Auto Approve Of Mandatory Trainings
		inputTrainingDetails.autoEnrollMandatoryTrainings();
		//Feedback popup to employee on last day of training
		inputTrainingDetails.feedbackPopupToEmployee(employeeId);
		//Disabling Feedback (If Employee Does Not Fill On Last Day)
		inputTrainingDetails.feedbackDisablement(employeeId);
		//Sending Notification To User About Training (Approved/Not Approved)
		inputTrainingDetails.notificationOfEnrollmentToUser(employeeId);
		//Employee Menu
		System.out.println("Employee MENU");
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
			upcommingTrainingList=employeeService.viewUpcommingTraining(employeeId);
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
			boolean result = inputTrainingDetails.checkIfEmployeeEnrolledTraining(employeeId);
			if(result)
			{
				inputTrainingDetails.showEnrolledTrainings(employeeId);
			}
			else
			{
				System.out.println("Employee Is Not Enrolled in Any Training");
			}
		break;
		
		case 3:
			boolean result2 = inputTrainingDetails.checkIfEmployeeEnrolledTraining(employeeId);
			if(result2)
			{
				inputTrainingDetails.showEnrolledTrainings(employeeId);
				System.out.println("Enter Training Id For the Feedback to be filled :- ");
				int tId=sc.nextInt();
				int eligible=0;
				try {
					eligible=employeeService.feedbackEligibilityCheck(tId, employeeId);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(eligible==0)
					System.out.println("Invalid Training Id !");
				else if(eligible==1)
					System.out.println("Training Not Ended Yet ! Feedback can be filled only on the End Date !");
				else if(eligible==2)
					System.out.println("Feedback for the entered Training Id already exists !");
				else
				{
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
				}
			}
			else
			{
				System.out.println("Employee Is Not Enrolled in Any Training So Cant Fill Feedback");
			}
		break;
		
		case 4:
			System.out.println("You Have Successfully Logged Out!");
			LoginPresentation loginPresentation = new LoginPresentationImpl();
			loginPresentation.mainMenu();
		break;
		
		default: 
			System.out.println("Invalid Input !!!!");
		}

		showEmployeeMenu(employeeId);
	}

}
