package com.eums.dao;

import java.sql.SQLException;
import java.util.List;

import com.eums.beans.Training;

public interface TrainingDao {
	List<Training> listAllRecords() throws SQLException;
	Training searchRecord(int tId) throws SQLException;
	boolean insertRecord(Training training) throws SQLException;
	boolean deleteRecord(int tId) throws SQLException;
	boolean updateRecord(int tId,Training newTraining) throws SQLException;
}
