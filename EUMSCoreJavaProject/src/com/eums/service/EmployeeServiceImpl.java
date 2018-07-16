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
	public ArrayList<Training> viewUpcommingTraining() throws SQLException {
		ArrayList<Training> allTraining=new ArrayList<>();
		allTraining = trainingDao.listAllRecords();
		ArrayList<Training> list=new ArrayList<>();

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  


		for(Training training:allTraining)
		{
			if(date.before(training.getSdate()))
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
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		requestedTraining.setDateWithTime(formatter.format(date));
		requestedTraining.setDateWithTime(date);
		boolean result = requestedTrainingDao.insertRecord(requestedTraining);
		return result;
	}

	@Override
	public boolean feedbackFilling(Feedback feedback) throws SQLException {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		//Training training = new Training();
		Training training = trainingDao.searchRecord(feedback.getTid());
		if(date.equals(training.getEdate()) && 
				!(feedBackDao.searchRecord(feedback.getEid(), feedback.getTid())))
		{	
			return feedBackDao.insertFeedback(feedback);
		}
		else
			return false;
	}

	@Override
	public void feedbackDisablement(String employeeId) throws SQLException {
		ArrayList<Training> trainingList = new ArrayList<>();
		ArrayList<Training> feedbackDisabledTrainingList = new ArrayList<>();
		trainingList = viewEnrolledTraining(employeeId); 
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		for(Training training:trainingList)
		{
			if(date.after(training.getEdate()))
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
			if(training.getEdate().equals(date) 
					&& !(feedBackDao.searchRecord(employeeId, tId)))
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
			newRequestedTraining.setAccepted(requestedTraining.isAccepted());
			newRequestedTraining.setNotified(true);
			newRequestedTraining.setProcessed(requestedTraining.isNotified());
			requestedTrainingDao.updateRecord(requestedTraining.getTid(), requestedTraining.getEid(), newRequestedTraining);
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

}
