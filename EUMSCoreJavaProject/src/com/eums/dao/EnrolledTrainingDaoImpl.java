package com.eums.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.eums.beans.EnrolledTraining;
import com.eums.helper.DBConnection;

public class EnrolledTrainingDaoImpl implements EnrolledTrainingDao {

	@Override
	public ArrayList<EnrolledTraining> listAllRecords() throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		ArrayList<EnrolledTraining> enrollTrainingList=new ArrayList<>();
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from employee_enrolled_for_training");
		EnrolledTraining enrollTraining=null;
		while(rs.next()){
			String employeeId=rs.getString("user__id");
			int trainingId=rs.getInt("training__id");
			enrollTraining=new EnrolledTraining(employeeId, trainingId);
			enrollTrainingList.add(enrollTraining);
		}
		return enrollTrainingList;
	}
	
}
