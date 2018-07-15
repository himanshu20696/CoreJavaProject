package com.eums.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public void showHRMenu() {
		System.out.println(" HR MENU");
		System.out.println("=========");
		System.out.println("1. Create new training. ");
		System.out.println("2. Modify existing training.");
		System.out.println("3. View employees enrolled for training.");
		System.out.println("4. Approve enrollments for trainings.");
		System.out.println("5. View Feedback.");
		System.out.println("6. log out.");

		System.out.println("Enter your choice :- ");
		int choice=sc.nextInt();
		actionPerformed(choice);
	}

	@Override
	public void actionPerformed(int choice) {
		switch(choice)
		{
		case 1:

			Training training=inputDetails.inputTrainingDetails();
			boolean status=hrService.createTrainingInCalender(training);
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
			System.out.println("Enter the training Id :-");
			int tId=sc.nextInt();
			System.out.println("Enter employee Id :-");
			String eId=sc.next();
			try {
				boolean approvedStatus= hrService.approveEnrollmentOfTraining(eId,tId);
				if(approvedStatus)
					System.out.println("Training Approved");
				else
					System.out.println("Training not approved");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 5:
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
			//log out
			break;
		default:System.out.println("Invald Input");
		}

		showHRMenu();
	}

}
