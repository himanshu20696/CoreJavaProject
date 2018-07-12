package com.eums.service;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.eums.beans.*;
public interface EmployeeService {
	ArrayList<Training> viewUpcommingTraining(Training training);
	ArrayList<Training> viewEnrolledTraining(int employeeId);
	boolean enrollForTraining(int trainingId);
	boolean feedbackFilling(Feedback feedback) throws SQLException;
	boolean feedbackDisablement(Training training);
	LinkedHashMap<Integer, String> feedbackPopup(String employeeID) throws SQLException;
	boolean notificationOfEnrollment(RequestedTraining requestedtraining);
	
	
	
}
