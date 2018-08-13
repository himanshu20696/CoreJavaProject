package com.eums.helper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import com.eums.beans.Training;
import com.eums.service.EmployeeService;
import com.eums.service.EmployeeServiceImpl;
import com.eums.service.HRService;
import com.eums.service.HRServiceImpl;

public class InputTrainingDetails {
	
	EmployeeService employeeService=new EmployeeServiceImpl();
	HRService hrService = new HRServiceImpl();
	Scanner sc=new Scanner(System.in);

	public Training inputTrainingDetails(){
		System.out.println("Enter training Id ");
		int tid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter training Name ");
		String tname=sc.nextLine();
		System.out.println("Enter trainering Type ");
		String ttype=sc.nextLine();
		System.out.println("Enter trainer's Name ");
		String trainername=sc.nextLine();
		System.out.println("Starting Date (yyyy-mm-dd) ");
		String sdate=sc.nextLine();
		System.out.println("End Date (yyyy-mm-dd) ");
		String edate=sc.nextLine();
		System.out.println("Maximum capacity of training");
		int maxcapacity=sc.nextInt();
		sc.nextLine();
		int availablecapacity=maxcapacity;
		System.out.println("Is Training Mandatory (true/false)");
		boolean mandatory=sc.nextBoolean();
		sc.nextLine();
		Training training=new Training(tid, tname, ttype, trainername, Date.valueOf(sdate), Date.valueOf(edate), maxcapacity, availablecapacity, mandatory);
		return training;
	}
	
	public void showEnrolledTrainings(String employeeId)
	{
		try {
		ArrayList<Training> enrolledTrainingList=new ArrayList<>();
		enrolledTrainingList=employeeService.viewEnrolledTraining(employeeId);
		System.out.println("You Are Enrolled In Following Trainings : ");
		for(Training list:enrolledTrainingList)
		{
			System.out.println(list); 
		}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void feedbackDisablement(String employeeId)
	{
		try {
			employeeService.feedbackDisablement(employeeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void autoEnrollMandatoryTrainings()
	{
		try {
			hrService.autoApproveOfMandateTraining();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void notificationOfEnrollmentToUser(String employeeId)
	{
		try {
			LinkedHashMap<String, Boolean> notification = new LinkedHashMap<>();
			notification = employeeService.notificationOfEnrollment(employeeId);
			Set<String> keys = notification.keySet();
			for(String trainings:keys)
			{
				System.out.print("Your Training Request for "+trainings+" ");
				if(notification.get(trainings))
				{
					System.out.println("Has Been Approved");
				}
				else
				{
					System.out.println("Has Been Declined");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void feedbackPopupToEmployee(String employeeId)
	{
		try {
			LinkedHashMap<Integer, String> feedbackPopup = new LinkedHashMap<>();
			feedbackPopup = employeeService.feedbackPopup(employeeId);
			Set<Integer> keys = feedbackPopup.keySet();
			for(Integer trainingId:keys)
			{
				System.out.println("Please Fill Feedback For "+feedbackPopup.get(trainingId)+" Having Trianing ID "+trainingId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
