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

	public Training inputTrainingDetails(String employeeId){
		String tname = null;
		String ttype = null;
		String trainername = null;
		Date sdate = null;
		Date edate = null;
		boolean mandatory = false;
		Integer availablecapacity = null;
		Integer	maxcapacity = null;
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		try {
		System.out.println("Enter training Name");
		tname=sc.nextLine();
		System.out.println("Enter trainering Type (Technical/HR/QMS)");
		ttype=sc.nextLine();
		System.out.println("Enter trainer's Name");
		trainername=sc.nextLine();
		
		int invalid=0;
		do{
			if(invalid>0)
				System.out.println("Start Date passed or same as current date!");
			System.out.println("Starting Date (yyyy-mm-dd)");
			sdate=Date.valueOf(sc.nextLine());
			invalid++;
		}while(sdate.before(date)||sdate.equals(date));
		invalid=0;
		do{
			if(invalid>0)
				System.out.println("Invalid End Date ! Date passed !");
			System.out.println("End Date (yyyy-mm-dd)");
			edate=Date.valueOf(sc.nextLine());
			invalid++;
		}while(edate.before(sdate));
		
		System.out.println("Is Training Mandatory (true/false)");
		mandatory=sc.nextBoolean();
		sc.nextLine();
		if(mandatory == false)
		{
			System.out.println("Maximum capacity of training");
			maxcapacity=sc.nextInt();
			sc.nextLine();
			availablecapacity=maxcapacity;
		}
		} catch(IllegalArgumentException e) {
			System.out.println("Please Enter Valid Date Format (Format Mentioned)");
			return null;
		}
		//System.out.println("creating training object");
		Training training=new Training();
		//System.out.println("setting values");
		training.setTname(tname);
		training.setTtype(ttype);
		training.setTrainername(trainername);
		training.setSdate(sdate);
		training.setEdate(edate);
		training.setMaxcapacity(maxcapacity);
		training.setAvailablecapacity(availablecapacity);
		training.setMandatory(mandatory);
		//System.out.println("returing training");
		return training;
	}
	
	public Training inputModifyTrainingDetails(String employeeId){
		int tId=0;
		String tname = null;
		String ttype = null;
		String trainername = null;
		Date sdate = null;
		Date edate = null;
		boolean mandatory = false;
		Integer availablecapacity = null;
		Integer	maxcapacity = null;
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		try {
		System.out.println("Enter training id for which you wanna modify");
		tId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter training Name");
		tname=sc.nextLine();
		System.out.println("Enter trainering Type (Technical/HR/QMS)");
		ttype=sc.nextLine();
		System.out.println("Enter trainer's Name");
		trainername=sc.nextLine();
		
		int invalid=0;
		do{
			if(invalid>0)
				System.out.println("Start Date passed or same as current date!");
			System.out.println("Starting Date (yyyy-mm-dd)");
			sdate=Date.valueOf(sc.nextLine());
			invalid++;
		}while(sdate.before(date)||sdate.equals(date));
		invalid=0;
		do{
			if(invalid>0)
				System.out.println("Invalid End Date ! Date passed !");
			System.out.println("End Date (yyyy-mm-dd)");
			edate=Date.valueOf(sc.nextLine());
			invalid++;
		}while(edate.before(sdate));
		
		System.out.println("Is Training Mandatory (true/false)");
		mandatory=sc.nextBoolean();
		sc.nextLine();
		if(mandatory == false)
		{
			System.out.println("Maximum capacity of training");
			maxcapacity=sc.nextInt();
			sc.nextLine();
			availablecapacity=maxcapacity;
		}
		} catch(IllegalArgumentException e) {
			System.out.println("Please Enter Valid Date Format (Format Mentioned)");
			return null;
		}
		//System.out.println("creating training object");
		Training training=new Training();
		//System.out.println("setting values");
		training.setTid(tId);
		training.setTname(tname);
		training.setTtype(ttype);
		training.setTrainername(trainername);
		training.setSdate(sdate);
		training.setEdate(edate);
		training.setMaxcapacity(maxcapacity);
		training.setAvailablecapacity(availablecapacity);
		training.setMandatory(mandatory);
		//System.out.println("returing training");
		return training;
	}
	
	public void showEnrolledTrainings(String employeeId)
	{
		try {
		ArrayList<Training> enrolledTrainingList=new ArrayList<>();
		enrolledTrainingList=employeeService.viewEnrolledTraining(employeeId);
		System.out.println("You Are Enrolled In Following Trainings :- ");
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
	
	public boolean checkIfEmployeeEnrolledTraining(String employeeId)
	{
		try {
			return employeeService.checkIfEmployeeEnrolledToAnyTraining(employeeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
