package com.eums.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public interface FeedbackDao {
	
	boolean insertFeedback(int trainingId,int employeeId,Feedback feedback) throws SQLException;
	List<Feedback> listDetailedFeedback(int trainingId) throws SQLException;
	Feedback listConsolidatedFeedback(int trainingID) throws SQLException;
	LinkedHashMap<Integer, String> generatePopupList() throws SQLException;
}
