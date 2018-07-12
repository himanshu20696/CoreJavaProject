package com.eums.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.eums.beans.Feedback;

public interface FeedbackDao {
	
	boolean insertFeedback(int trainingId,String employeeId,Feedback feedback) throws SQLException;
	List<Feedback> listDetailedFeedback(int trainingId) throws SQLException;
	String listConsolidatedFeedback(int trainingId) throws SQLException;
	LinkedHashMap<Integer, String> generatePopupList(String employeeID) throws SQLException;
}
