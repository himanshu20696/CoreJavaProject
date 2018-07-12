package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.eums.beans.Feedback;
import com.eums.beans.RequestedTraining;
import com.eums.beans.Training;
import com.eums.dao.FeedbackDao;
import com.eums.dao.FeedbackDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private FeedbackDao feedBackDao = new FeedbackDaoImpl();
	@Override
	public ArrayList<Training> viewUpcommingTraining(Training training) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Training> viewEnrolledTraining() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean enrollForTraining(int trainingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean feedbackFilling(Feedback feedback) throws SQLException {
		return feedBackDao.insertFeedback(feedback);
	}

	@Override
	public boolean feedbackDisablement(Training training) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedHashMap<Integer, String> feedbackPopup(String employeeID) throws SQLException {
		return feedBackDao.generatePopupList(employeeID);
	}

	@Override
	public boolean notificationOfEnrollment(RequestedTraining requestedtraining) {
		// TODO Auto-generated method stub
		return false;
	}

}
