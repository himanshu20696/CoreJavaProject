package com.eums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eums.beans.RequestedTraining;
import com.eums.helper.DBConnection;

public class RequestedTrainingDaoImpl implements RequestedTrainingDao {

	@Override
	public boolean insertRecord(RequestedTraining requestedTraining) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("insert into requested_training"
				+ "(user__id,training__id,enrolledtime,accepted,notified) "
				+ "values(?,?,?,?,?)");
		pst.setString(1, requestedTraining.getEid());
		pst.setInt(2, requestedTraining.getTid());
		pst.setString(3, requestedTraining.getDateWithTime());
		pst.setBoolean(4, requestedTraining.isAccepted());
		pst.setBoolean(5, requestedTraining.isNotified());

		int rows=pst.executeUpdate();
		if(rows <= 0)
			return false;

		return true;
	}

	@Override
	public List<RequestedTraining> listAllRecords() throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		ArrayList<RequestedTraining> requestedTrainingDetails=new ArrayList<>();
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from requested_training");
		RequestedTraining requestedTraining=null;

		while(rs.next()){
			int tid = rs.getInt("user__id");
			String eid = rs.getString("training__id");
			String enrolledTime = rs.getString("enrolledtime");
			Boolean accepted = rs.getBoolean("accepted");
			Boolean notified = rs.getBoolean("notified");
			Boolean processed = rs.getBoolean("processed");
			requestedTraining = new RequestedTraining(eid, tid, enrolledTime, accepted, notified, processed);
			requestedTrainingDetails.add(requestedTraining);
		}

		return requestedTrainingDetails;
	}

	@Override
	public boolean updateRecord(int tId, String eid, RequestedTraining newRequestedTraining) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("update training_details set "
				+ "accepted=?"
				+ "notified=?"
				+ "where tid=? and eid=?");
		pst.setBoolean(1, newRequestedTraining.isAccepted());
		pst.setBoolean(2, newRequestedTraining.isNotified());
		pst.setInt(3, tId);
		pst.setString(4, eid);
		int rows = pst.executeUpdate();
		if(rows <= 0)
			return false;

		return true;
	}

	@Override
	public List<RequestedTraining> listPendingRecords() throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		ArrayList<RequestedTraining> requestedTrainingDetails=new ArrayList<>();
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from requested_training where processed=false");
		RequestedTraining requestedTraining=null;

		while(rs.next()){
			int tid = rs.getInt("user__id");
			String eid = rs.getString("training__id");
			String enrolledTime = rs.getString("enrolledtime");
			Boolean accepted = rs.getBoolean("accepted");
			Boolean notified = rs.getBoolean("notified");
			Boolean processed = rs.getBoolean("processed");
			requestedTraining = new RequestedTraining(eid, tid, enrolledTime, accepted, notified,processed);
			requestedTrainingDetails.add(requestedTraining);
		}

		return requestedTrainingDetails;
	}

}
