package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.eums.beans.EnrolledTraining;
import com.eums.beans.Feedback;
import com.eums.beans.RequestedTraining;
import com.eums.beans.Training;
import com.eums.dao.EnrolledTrainingDao;
import com.eums.dao.EnrolledTrainingDaoImpl;
import com.eums.dao.FeedbackDao;
import com.eums.dao.FeedbackDaoImpl;
import com.eums.dao.RequestedTrainingDao;
import com.eums.dao.RequestedTrainingDaoImpl;
import com.eums.dao.TrainingDao;
import com.eums.dao.TrainingDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private FeedbackDao feedBackDao = new FeedbackDaoImpl();
	private RequestedTrainingDao requestedTrainingDao = new RequestedTrainingDaoImpl();
	private TrainingDao trainingDao = new TrainingDaoImpl();
	private EnrolledTrainingDao enrolledTrainingDao = new EnrolledTrainingDaoImpl();

	@Override
	public ArrayList<Training> viewUpcommingTraining(String employeeId) throws SQLException {
		ArrayList<Training> allTraining=new ArrayList<>();
		allTraining = trainingDao.listAllRecords();
		ArrayList<Training> list=new ArrayList<>();
		ArrayList<Integer> enrolledTrainingList = new ArrayList<>();
		enrolledTrainingList=enrolledTrainingDao.listEmployeeEnrolledTrainingRecords(employeeId);

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  


		for(Training training:allTraining)
		{
			if(date.before(training.getSdate())&&!enrolledTrainingList.contains(training.getTid()))
			{
				list.add(training);
			}
		}
		return list;
	}

	@Override
	public boolean enrollForTraining(int trainingId, String employeeId) throws SQLException {
		ArrayList<EnrolledTraining> enrolledTrainingList = new ArrayList<>();
		ArrayList<EnrolledTraining> enrolledEmployeeTrainingList = new ArrayList<>();
		enrolledTrainingList = enrolledTrainingDao.listAllRecords();
		for(EnrolledTraining enrolledTraining:enrolledTrainingList)
		{
			if(enrolledTraining.getEmployeeId().equals(employeeId))
			{
				enrolledEmployeeTrainingList.add(enrolledTraining);
			}
		}
		Training wantToJoinTraining = trainingDao.searchRecord(trainingId);
		java.sql.Date startDate = new java.sql.Date(wantToJoinTraining.getSdate().getTime());
		System.out.println("startDate :"+startDate);
		for(EnrolledTraining enrolledTraining:enrolledEmployeeTrainingList)
		{
			Training training = trainingDao.searchRecord(enrolledTraining.getTrainingId());
			java.sql.Date endDate = new java.sql.Date(training.getEdate().getTime());
			System.out.println("endDate :"+endDate);
			if(endDate.after(startDate))
			{
				return false;
			}
		}
		RequestedTraining requestedTraining = new RequestedTraining();
		requestedTraining.setEid(employeeId);
		requestedTraining.setTid(trainingId);
		requestedTraining.setAccepted(false);
		requestedTraining.setNotified(false);
		requestedTraining.setProcessed(false);
		long millis=System.currentTimeMillis();  
		java.sql.Timestamp date=new java.sql.Timestamp(millis);
		requestedTraining.setDateWithTime(date);
		boolean result = requestedTrainingDao.insertRecord(requestedTraining);
		return result;
	}

	@Override
	public boolean feedbackFilling(Feedback feedback) throws SQLException {
//		long millis=System.currentTimeMillis();  
//		java.sql.Date date=new java.sql.Date(millis);
//		Training training = trainingDao.searchRecord(feedback.getTid());
////		System.out.println("current time in feedback filling "+date.getTime());
////		System.out.println("training time in feedback filling "+training.getEdate().getTime());
		int eligible=feedbackEligibilityCheck(feedback.getTid(),feedback.getEid());
		if(eligible==3)	
		{	
			return feedBackDao.insertFeedback(feedback);
		}
		else
			return false;
	}
	
	public int feedbackEligibilityCheck(int trainingId, String employeeId) throws SQLException {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		int eligible=1;
		Training training = trainingDao.searchRecord(trainingId);
		if(training.equals(null))
		{
			return 0;
		}
//		System.out.println("current time in feedback filling "+date.getTime());
//		System.out.println("training time in feedback filling "+training.getEdate().getTime());
		if(date.toString().equals(training.getEdate().toString()))
		{
			eligible=2;
			if((feedBackDao.searchRecord(employeeId, trainingId))==0)
			{
				eligible=3;
			}
		}
		return eligible;
		
	}

	@Override
	public void feedbackDisablement(String employeeId) throws SQLException {
		//Finding All Training in which user is enrolled
		ArrayList<Training> trainingList = new ArrayList<>();
		ArrayList<Training> feedbackDisabledTrainingList = new ArrayList<>();
		trainingList = viewEnrolledTraining(employeeId);

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		for(Training training:trainingList)
		{
			int result = feedBackDao.searchRecord(employeeId, training.getTid());
			if(date.after(training.getEdate()) && result==0)
			{
				feedbackDisabledTrainingList.add(training);
			}
		}
		Feedback feedback = new Feedback();
		feedback.setCoverageoftopics(0);
		feedback.setCourceoverall(0);
		feedback.setEffectivenessofcource(0);
		feedback.setPaceofdelivery(0);
		feedback.setPresentationstyle(0);
		feedback.setTraineroverall(0);
		for(Training training:feedbackDisabledTrainingList)
		{
			feedBackDao.updateRecord(employeeId, training.getTid(), feedback);
		}
	}

	@Override
	public LinkedHashMap<Integer, String> feedbackPopup(String employeeId) throws SQLException {
		ArrayList<Integer> enrolledTrainingIdList = new ArrayList<>();
		enrolledTrainingIdList = enrolledTrainingDao.listEmployeeEnrolledTrainingRecords(employeeId);
		Training training = new Training();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis); 
		LinkedHashMap<Integer,String> hashmap = new LinkedHashMap<>();
		for(Integer tId : enrolledTrainingIdList)
		{
			training = trainingDao.searchRecord(tId);
			if(training.getEdate().toString().equals(date.toString()) 
					&& (feedBackDao.searchRecord(employeeId, tId))==0)
			{
				hashmap.put(tId, training.getTname());
			}
		}
		return hashmap;
	}

	@Override
	public LinkedHashMap<String, Boolean> notificationOfEnrollment(String employeeId) throws SQLException {
		LinkedHashMap<String, Boolean> notificationMap = new LinkedHashMap<>();
		List<RequestedTraining> requestedTrainingList = requestedTrainingDao.listAllRecords();
		ArrayList<RequestedTraining> requestedTrainingEmployeeList = new ArrayList<>();
		for(RequestedTraining requestedTraining:requestedTrainingList)
		{
			if((requestedTraining.getEid().equals(employeeId)) && (requestedTraining.isNotified()==false))
			{
				requestedTrainingEmployeeList.add(requestedTraining);
			}
		}
		for(RequestedTraining requestedTraining:requestedTrainingEmployeeList)
		{
			Training training = trainingDao.searchRecord(requestedTraining.getTid());
			notificationMap.put(training.getTname(), requestedTraining.isAccepted());
			RequestedTraining newRequestedTraining = new RequestedTraining();
			newRequestedTraining.setEid(requestedTraining.getEid());
			newRequestedTraining.setTid(requestedTraining.getTid());
			newRequestedTraining.setAccepted(requestedTraining.isAccepted());
			newRequestedTraining.setNotified(true);
			newRequestedTraining.setProcessed(requestedTraining.isNotified());
			requestedTrainingDao.updateRecord(newRequestedTraining);
		}
		return notificationMap;
	}

	@Override
	public ArrayList<Training> viewEnrolledTraining(String employeeId) throws SQLException {
		ArrayList<EnrolledTraining> enrolledTrainingList = enrolledTrainingDao.listAllRecords();
		ArrayList<Integer> trainingIdList= new ArrayList<>();
		ArrayList<Training> trainingList= new ArrayList<>();
		for(EnrolledTraining enrolledTraining:enrolledTrainingList)
		{
			if(enrolledTraining.getEmployeeId().equals(employeeId))
			{
				trainingIdList.add(enrolledTraining.getTrainingId());
			}
		}
		for(Integer trainingId:trainingIdList)
		{
			Training training=trainingDao.searchRecord(trainingId);
			trainingList.add(training);
		}

		return trainingList;
	}
	
	@Override
	public boolean checkIfEmployeeEnrolledToAnyTraining(String employeeId) throws SQLException
	{
		ArrayList<Integer> employeeEnrolled = new ArrayList<>();
		employeeEnrolled = enrolledTrainingDao.listEmployeeEnrolledTrainingRecords(employeeId);
		if(employeeEnrolled.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
