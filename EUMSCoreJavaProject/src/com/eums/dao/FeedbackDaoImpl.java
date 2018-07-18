package com.eums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		ResultSet rs = stmt.executeQuery("select * from feedback where training__id="+trainingId);
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
		ResultSet rs = stmt.executeQuery("select AVG(courceoverall),AVG(traineroverall) from feedback where training__id="+trainingId);
		while(rs.next()){
			cOverall=rs.getInt(1);
			tOverall=rs.getInt(2);
		}
		return ("Course Overall : "+cOverall+"\n"+"Trainer Overall : "+tOverall);
	}

	@Override
	public boolean updateRecord(String employeeId, int trainingId, Feedback newFeedback) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("update feedback set "
				+ "coverageoftopics=?,"
				+ "effectivenessofcource=?,"
				+ "presentationstyle=?,"
				+ "paceofdelivery=?,"
				+ "courceoverall=?,"
				+ "traineroverall=? "
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
	
	@Override
	public int searchRecord(String employeeId, int trainingId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		int result=0;
		ResultSet rs = stmt.executeQuery("select count(*) from feedback where user__id='"+employeeId+"' and training__id="+trainingId);
		
		while(rs.next())
		{
			result = rs.getInt(1);
		}
		return result;
	}
	
	@Override
	public LinkedHashMap<Integer,String> viewAvailableTrainingFeedback() throws SQLException{
		LinkedHashMap<Integer,String> hashmap = new LinkedHashMap<>();
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select DISTINCT tid,tname from training_details");
		while(rs.next()){
			int tid = rs.getInt(1);
			String tname = rs.getString(2);
			hashmap.put(tid, tname);
		}
		return hashmap;
	}
}
