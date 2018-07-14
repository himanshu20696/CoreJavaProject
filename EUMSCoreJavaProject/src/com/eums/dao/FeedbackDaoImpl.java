package com.eums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.eums.beans.Feedback;
import com.eums.helper.DBConnection;

public class FeedbackDaoImpl implements FeedbackDao {

	@Override
	public boolean insertFeedback(Feedback feedback) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("insert into feedback values(?,?,?,?,?,?,?,?)");		
		pst.setString(1,feedback.getEid());
		pst.setInt(2, feedback.getTid());
		pst.setInt(3, feedback.getCoverageoftopics());
		pst.setInt(4, feedback.getEffectivenessofcource());
		pst.setInt(5, feedback.getPresentationstyle());
		pst.setInt(6, feedback.getPaceofdelivery());
		pst.setInt(7, feedback.getCourceoverall());
		pst.setInt(8, feedback.getTraineroverall());

		int rows=pst.executeUpdate();
		
		if(rows <= 0)
			return false;		
		
		return true;
	}

	@Override
	public List<Feedback> listDetailedFeedback(int trainingId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from feedback where training_id="+trainingId);
		Feedback feedback=null;
		List<Feedback> fblist = new ArrayList<>();
		
		while(rs.next()){
			String eid=rs.getString(1);
			int tid=rs.getInt(2);
			int coverageoftopics=rs.getInt(3);
			int effectivenessofcource=rs.getInt(4);
			int presentationstyle=rs.getInt(5);
			int paceofdelivery=rs.getInt(6);
			int courceoverall=rs.getInt(7);
			int traineroverall=rs.getInt(8);
			feedback=new Feedback(eid,tid,coverageoftopics,effectivenessofcource,
					presentationstyle,paceofdelivery,courceoverall,traineroverall);
			fblist.add(feedback);
		}
		return fblist;
	}

	@Override
	public String listConsolidatedFeedback(int trainingId) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		float cOverall=0.0f,tOverall=0.0f;
		ResultSet rs = stmt.executeQuery("select AVG(courceoverall),AVG(traineroverall) from feedback where training_id="+trainingId);
		while(rs.next()){
			cOverall=rs.getInt(1);
			tOverall=rs.getInt(2);
		}
		return ("Course Overall : "+cOverall+"\n"+"Trainer Overall : "+tOverall);
	}

	@Override
	public LinkedHashMap<Integer, String> generatePopupList(String employeeID) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Connection con=null;
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		LinkedHashMap<Integer,String> hashmap = new LinkedHashMap<>();
		ResultSet rs = stmt.executeQuery("select requested_training.training_id, training_details.tname from "
				+ "requested_training inner join training_details on  "
				+ "requested_training.training_id=training_details.tid"
				+ "where requested_training.user_id="+employeeID+"and "+dateFormat.format(date)+">training_details.enddate and "
						+ "requested_training.notified=false");
		while(rs.next()){
			hashmap.put(rs.getInt(1),rs.getString(2));
			stmt.executeQuery("Update requested_training set notified=true "
					+ "where training_id="+rs.getInt(1));
		}
		return hashmap;
	}

	@Override
	public boolean updateRecord(String employeeId, int trainingId, Feedback newFeedback) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("update feedback set "
				+ "coverageoftopics=?"
				+ "effectivenessofcource=?"
				+ "presentationstyle=?"
				+ "paceofdelivery=?"
				+ "courceoverall=?"
				+ "traineroverall=?"
				+ "where user__id=? and training__id=?");
		pst.setInt(1, newFeedback.getCoverageoftopics());
		pst.setInt(2, newFeedback.getEffectivenessofcource());
		pst.setInt(3, newFeedback.getPresentationstyle());
		pst.setInt(4, newFeedback.getPaceofdelivery());
		pst.setInt(5, newFeedback.getCourceoverall());
		pst.setInt(6, newFeedback.getTraineroverall());
		pst.setString(7, employeeId);
		pst.setInt(8, trainingId);
		int rows = pst.executeUpdate();
		if(rows <= 0)
			return false;
		return true;
	}

}
