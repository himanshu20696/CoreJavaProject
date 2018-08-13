package com.eums.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.eums.beans.Employee;
import com.eums.beans.Training;
import com.eums.helper.InputTrainingDetails;
import com.eums.service.*;
public class HRPresentationImpl implements HRPresentation {
	
	Scanner sc=new Scanner(System.in);
	HRService hrService=new HRServiceImpl();
	InputTrainingDetails inputDetails=new InputTrainingDetails();
	
	@Override
	public void showHRMenu(String employeeId) {
		LoginPresentationImpl.loginAttempt=3;
		System.out.println("HR MENU");
		System.out.println("=========");
		System.out.println("1. Create New Training");
		System.out.println("2. Modify Existing Trainings");
		System.out.println("3. View Employees Enrolled For Training");
		System.out.println("4. Approve Enrollments For Trainings");
		System.out.println("5. View Feedback");
		System.out.println("6. Log Out");

		System.out.println("Enter your choice :- ");
		int choice=sc.nextInt();
		actionPerformed(choice, employeeId);
	}

	@Override
	public void actionPerformed(int choice, String employeeId) {
		switch(choice)
		{
		case 1:
			Training training=inputDetails.inputTrainingDetails();
			boolean status = false;
			try {
				status = hrService.createTrainingInCalender(training);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(status)
				System.out.println("Training Successfully Created");
			else
				System.out.println("Cannot be created");
			break;
			
		case 2:
			System.out.println("Update details here!!");
			Training updatedTraining=inputDetails.inputTrainingDetails();
			try {
				boolean updateStatus=hrService.modifyTrainingInCalender(updatedTraining.getTid(), updatedTraining);
				if(updateStatus)
					System.out.println("Your update was successfull...");
				else
					System.out.println("Failed to update the training...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 3:
			System.out.println("Enter the training Id for which you want to see enrolled emplyees :-");
			int trainingId=sc.nextInt();
			ArrayList<Employee> employeeList=new ArrayList<>();
			try {
				employeeList=hrService.viewEmployeeEnrolledForTraining(trainingId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Employees enrolled for "+trainingId+" are ");
			System.out.println(employeeList);

			break;
			
		case 4:
			try {
				System.out.println(hrService.viewRequestedTraining());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("1. Approve Pending Requests");
			System.out.println("2. Return to Previous Menu");
			int approve=sc.nextInt();
			if(approve==1)
			{
				try {
					hrService.approveEnrollmentOfTraining();
					System.out.println("Status after Approval :-");
					System.out.println(hrService.viewRequestedTraining());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			else
				break;
			
		case 5:
			LinkedHashMap<Integer,String> hashmap = new LinkedHashMap<>();
			try {
				hashmap = hrService.displayAvailableTrainingFeedback();
				for (int key : hashmap.keySet()) {
				    System.out.println("Training ID : "+key +" "+"Training Name : "+hashmap.get(key));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Enter the training Id for which you wish to see the feedback :-");
			int trainId=sc.nextInt();
			System.out.println("1. View Consolidated Feedback \n2. View detailed feedback");
			int ch=sc.nextInt();
			if(ch==1)
			{
				System.out.println("Consolidated Feedback");
				try {
					System.out.println(hrService.viewTrainingFeedbackConsolidated(trainId));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(ch==2)
			{
				System.out.println("Detailed Feedback");
				try {
					System.out.println(hrService.viewTrainingFeedbackDetailed(trainId));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Invalid Input...");
			}
			break;
			
		case 6:
			System.out.println("You Have Successfully Logged Out!");
			LoginPresentation loginPresentation = new LoginPresentationImpl();
			loginPresentation.mainMenu();
			break;
			
		default:System.out.println("Invald Input");
		}

		showHRMenu(employeeId);
	}

}
