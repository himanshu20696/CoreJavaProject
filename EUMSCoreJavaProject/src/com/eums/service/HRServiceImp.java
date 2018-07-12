package com.eums.service;

import java.util.ArrayList;

import com.eums.beans.Employee;
import com.eums.beans.Feedback;
import com.eums.beans.Training;

public class HRServiceImp implements HRService{

	@Override
	public boolean createTrainingInCalender(Training training) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyTrainingInCalender(int trainingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Employee> viewEmployeeEnrolledForTraining(int trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveEnrollmentOfTraining(int employeeId, int trainingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Feedback> viewTrainingFeedback(int trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
