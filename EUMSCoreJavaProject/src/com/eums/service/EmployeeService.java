package com.eums.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.eums.beans.Feedback;
import com.eums.beans.Training;

public interface EmployeeService {
	ArrayList<Training> viewUpcommingTraining(String employeeId) throws SQLException;
	ArrayList<Training> viewEnrolledTraining(String employeeId) throws SQLException;
	boolean enrollForTraining(int trainingId, String employeeId) throws SQLException;
	boolean feedbackFilling(Feedback feedback) throws SQLException;
	void feedbackDisablement(String employeeId) throws SQLException;
	LinkedHashMap<Integer, String> feedbackPopup(String employeeID) throws SQLException;
	LinkedHashMap<String, Boolean> notificationOfEnrollment(String employeeId) throws SQLException;
	boolean checkIfEmployeeEnrolledToAnyTraining(String employeeId) throws SQLException;
}
