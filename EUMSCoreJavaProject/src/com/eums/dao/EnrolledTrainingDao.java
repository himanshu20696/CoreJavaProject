package com.eums.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.eums.beans.EnrolledTraining;

public interface EnrolledTrainingDao {
	ArrayList<EnrolledTraining> listAllRecords() throws SQLException;
}
