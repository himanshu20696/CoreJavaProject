package com.eums.helper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.eums.beans.Training;
import com.eums.service.EmployeeService;
import com.eums.service.EmployeeServiceImpl;

public class InputTrainingDetails {
	
	EmployeeService employeeService=new EmployeeServiceImpl();
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
}
