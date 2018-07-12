package com.eums.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.eums.beans.Feedback;
import com.eums.beans.RequestedTraining;
import com.eums.beans.Training;
import com.eums.helper.DBConnection;

public class EmloyeeServiceImp implements EmployeeService{

	@Override
	public ArrayList<Training> viewUpcommingTraining(Training training) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Training> viewEnrolledTraining(int employeeId) {
		// TODO Auto-generated method stub
		ArrayList<Training> list=new ArrayList<>();
		Connection con=null; 
		Statement stmt=null;
		try{
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from user_enrolled_for_training where user__id="+employeeId+" ;");
		while(rs.next()){
		int trainingId=rs.getInt("training__id");
			ResultSet rs1=stmt.executeQuery("select * from training_details"
					+ " where tid ="+trainingId+";");
			while(rs1.next())
			{
				Training ob=new Training();
				ob.setTid(rs1.getInt("tid"));
				ob.setTname(rs1.getString("tname"));
				ob.setTrainername(rs1.getString("trainername"));
				ob.setTtype(rs1.getString("ttype"));
				ob.setMandatory(rs1.getBoolean("mandatory"));
				ob.setAvailablecapacity(rs1.getInt("availablecapacity"));
				ob.setSdate(rs1.getString("startdate"));
				ob.setEdate(rs1.getString("enddate"));
				ob.setMaxcapacity(rs1.getInt("maxcapacity"));
				list.add(ob);
			}
			
			
			
		}
		
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		
		
		
		return list;
	}

	@Override
	public boolean enrollForTraining(int trainingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean feedbackFilling(Feedback feedback) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean feedbackDisablement(Training training) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean feedbackPopup(Training training) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notificationOfEnrollment(RequestedTraining requestedtraining) {
		// TODO Auto-generated method stub
		return false;
	}

}
