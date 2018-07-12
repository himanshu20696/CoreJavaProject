package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.eums.beans.Employee;
import com.eums.beans.Feedback;
import com.eums.beans.Training;

public interface HRService {
	boolean createTrainingInCalender(Training training);
	boolean modifyTrainingInCalender(int trainingId, Training newTraining) throws SQLException;
	ArrayList<Employee> viewEmployeeEnrolledForTraining(int trainingId);
	boolean approveEnrollmentOfTraining(int employeeId, int trainingId) throws SQLException;
	ArrayList<Feedback> viewTrainingFeedback(int trainingId);
}
