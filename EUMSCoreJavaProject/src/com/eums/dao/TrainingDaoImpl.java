package com.eums.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.eums.beans.Training;
import com.eums.helper.DBConnection;

public class TrainingDaoImpl implements TrainingDao {

	@Override
	public ArrayList<Training> listAllRecords() throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		ArrayList<Training> trainingDetails=new ArrayList<>();
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from training_details");
		Training training=null;

		while(rs.next()){
			int tid = rs.getInt("tid");
			String tname = rs.getString("tname");
			String ttype = rs.getString("ttype");
			String trainerName = rs.getString("trainername");
			Date startDate = rs.getDate("startdate");
			Date endDate = rs.getDate("enddate");
			int maxCapacity = rs.getInt("maxcapacity");
			int availableCapacity = rs.getInt("availablecapacity");
			boolean mandatory = rs.getBoolean("mandatory");
			training = new Training(tid, tname, ttype, trainerName, startDate, endDate, maxCapacity, availableCapacity, mandatory);
			trainingDetails.add(training);
		}

		return trainingDetails;
	}

	@Override
	public Training searchRecord(int tId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from training_details where tid="+tId);
		Training training=null;

		while(rs.next()){
			int tid = rs.getInt("tid");
			String tname = rs.getString("tname");
			String ttype = rs.getString("ttype");
			String trainerName = rs.getString("trainername");
			Date startDate = rs.getDate("startdate");
			Date endDate = rs.getDate("enddate");
			int maxCapacity = rs.getInt("maxcapacity");
			int availableCapacity = rs.getInt("availablecapacity");
			boolean mandatory = rs.getBoolean("mandatory");
			training = new Training(tid, tname, ttype, trainerName, startDate, endDate, maxCapacity, availableCapacity, mandatory);
		}
		return training;
	}

	@Override
	public boolean insertRecord(Training training) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("insert into training_details"
				+ "(tname,ttype,trainername,startdate,enddate,maxcapacity,"
				+ "availablecapacity,mandatory) values(?,?,?,?,?,?,?,?)");
		pst.setString(1, training.getTname());
		pst.setString(2, training.getTtype());
		pst.setString(3, training.getTrainername());
		pst.setDate(4, training.getSdate());
		pst.setDate(5, training.getEdate());
		if(training.getMaxcapacity() == null)
		{
			pst.setNull(6, Types.NULL);
			pst.setNull(7, Types.NULL);
		}
		else
		{
			pst.setInt(6, training.getMaxcapacity());
			pst.setInt(7, training.getAvailablecapacity());	
		}
		pst.setBoolean(8, training.isMandatory());

		int rows=pst.executeUpdate();
		if(rows <= 0)
			return false;

		return true;
	}

	@Override
	public boolean deleteRecord(int tId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		int rows = stmt.executeUpdate("delete from training_details where tid="+tId);
		if(rows <= 0)
			return false;

		return true;
	}

	@Override
	public boolean updateRecord(int tId, Training newTraining) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("update training_details set "
				+ "tname=?,"
				+ "ttype=?,"
				+ "trainername=?,"
				+ "startdate=?,"
				+ "enddate=?,"
				+ "maxcapacity=?,"
				+ "availablecapacity=?,"
				+ "mandatory=? "
				+ "where tid=?");
		pst.setString(1, newTraining.getTname());
		pst.setString(2, newTraining.getTtype());
		pst.setString(3, newTraining.getTrainername());
		pst.setDate(4, newTraining.getSdate());
		pst.setDate(5, newTraining.getEdate());
		if(newTraining.getMaxcapacity() == null)
		{
			pst.setNull(6, Types.NULL);
			pst.setNull(7, Types.NULL);
		}
		else
		{
			pst.setInt(6, newTraining.getMaxcapacity());
			pst.setInt(7, newTraining.getAvailablecapacity());	
		}
		pst.setBoolean(8, newTraining.isMandatory());
		pst.setInt(9, tId);
		int rows = pst.executeUpdate();
		if(rows <= 0)
			return false;

		return true;
	}

}
