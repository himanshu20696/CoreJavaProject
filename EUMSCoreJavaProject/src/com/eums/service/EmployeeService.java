package com.eums.service;
import java.awt.List;
import java.util.ArrayList;

import com.eums.beans.*;
public interface EmployeeService {
	ArrayList<Training> viewUpcommingTraining(Training training);
	ArrayList<Training> viewEnrolledTraining();
	boolean enrollForTraining(int trainingId);
	boolean feedbackFilling(Feedback feedback);
	boolean feedbackDisablement(Training training);
	boolean feedbackPopup(Training training);
	boolean notificationOfEnrollment(RequestedTraining requestedtraining);
	
	
	
}
