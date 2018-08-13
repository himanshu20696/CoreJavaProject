package com.eums.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eums.beans.RequestedTraining;

public interface RequestedTrainingDao {
	boolean insertRecord(RequestedTraining requestedTraining) throws SQLException;
	List<RequestedTraining> listAllRecords() throws SQLException;
	boolean updateRecord(RequestedTraining newRequestedTraining) throws SQLException;
	ArrayList<RequestedTraining> listPendingRecords() throws SQLException;
}
