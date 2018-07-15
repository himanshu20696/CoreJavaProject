package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eums.beans.Employee;
import com.eums.beans.Feedback;
import com.eums.beans.Training;

public interface HRService {
	boolean createTrainingInCalender(Training training);
	boolean modifyTrainingInCalender(int trainingId, Training newTraining) throws SQLException;
	ArrayList<Employee> viewEmployeeEnrolledForTraining(int trainingId) throws SQLException;
	boolean approveEnrollmentOfTraining(String employeeId, int trainingId) throws SQLException;
	List<Feedback> viewTrainingFeedbackDetailed(int trainingId) throws SQLException;
	String viewTrainingFeedbackConsolidated(int trainingId) throws SQLException;
	
}
