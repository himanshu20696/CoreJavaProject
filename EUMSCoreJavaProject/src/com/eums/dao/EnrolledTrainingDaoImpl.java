package com.eums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public boolean insertRecord(EnrolledTraining enrolledTraining) throws SQLException {

		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("insert into employee_enrolled_for_training values(?,?)");		
		pst.setString(1, enrolledTraining.getEmployeeId());
		pst.setInt(2, enrolledTraining.getTrainingId());

		int rows=pst.executeUpdate();

		if(rows <= 0)
			return false;		

		return true;
	}

	public EnrolledTraining searchRecord(String employeeId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select training__id from employee_enrolled_for_training where user__id="+employeeId);
		EnrolledTraining enrolledTraining=null;
		while(rs.next()){
			int trainingId = rs.getInt(1);
			enrolledTraining = new EnrolledTraining(employeeId,trainingId);
		}
		return enrolledTraining;
	}
	
	@Override
	public ArrayList<Integer> listEmployeeEnrolledTrainingRecords(String employeeId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ArrayList<Integer> enrolledTrainingIdList = new ArrayList<>();
		ResultSet rs = stmt.executeQuery("select training__id from "
				+ "employee_enrolled_for_training where user__id='"+employeeId+"'");
		while(rs.next()){
			int trainingId = rs.getInt(1);
			enrolledTrainingIdList.add(trainingId);
		}
		return enrolledTrainingIdList;
	}

}
