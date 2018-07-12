package com.eums.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.eums.beans.Feedback;

public interface FeedbackDao {
	
	boolean insertFeedback(int trainingId,int employeeId,Feedback feedback) throws SQLException;
	List<Feedback> listDetailedFeedback(int trainingId) throws SQLException;
	Feedback listConsolidatedFeedback(int trainingId) throws SQLException;
	LinkedHashMap<Integer, String> generatePopupList() throws SQLException;
}
