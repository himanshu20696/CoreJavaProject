package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.eums.beans.Employee;
import com.eums.beans.EnrolledTraining;
import com.eums.beans.Feedback;
import com.eums.beans.RequestedTraining;
import com.eums.beans.Training;
import com.eums.dao.EmployeeDao;
import com.eums.dao.EmployeeDaoImpl;
import com.eums.dao.EnrolledTrainingDao;
import com.eums.dao.EnrolledTrainingDaoImpl;
import com.eums.dao.FeedbackDao;
import com.eums.dao.FeedbackDaoImpl;
import com.eums.dao.RequestedTrainingDao;
import com.eums.dao.RequestedTrainingDaoImpl;
import com.eums.dao.TrainingDao;
import com.eums.dao.TrainingDaoImpl;

public class HRServiceImpl implements HRService {

	private TrainingDao trainingDao = new TrainingDaoImpl();
	private FeedbackDao feedbackDao=new FeedbackDaoImpl();
	private RequestedTrainingDao requestedTrainingDao = new RequestedTrainingDaoImpl();
	private EnrolledTrainingDao enrolledTrainingDao = new EnrolledTrainingDaoImpl();
	private EmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public boolean createTrainingInCalender(Training training) throws SQLException {
		return trainingDao.insertRecord(training);
	}

	@Override
	public boolean modifyTrainingInCalender(int trainingId, Training newTraining) throws SQLException {
		return trainingDao.updateRecord(trainingId, newTraining);
	}

	@Override
	public ArrayList<Employee> viewEmployeeEnrolledForTraining(int trainingId) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<>();
		ArrayList<String> employeeIdList = new ArrayList<>();
		ArrayList<EnrolledTraining> enrolledTrainingList = enrolledTrainingDao.listAllRecords();
		for(EnrolledTraining enrolledTraining:enrolledTrainingList)
		{
			if(enrolledTraining.getTrainingId() == trainingId)
			{
				employeeIdList.add(enrolledTraining.getEmployeeId());
			}
		}
		for(String employeeId:employeeIdList)
		{
			Employee employee = employeeDao.searchRecord(employeeId);
			employeeList.add(employee);
		}
		return employeeList;
	}

	@Override
	public void approveEnrollmentOfTraining() throws SQLException {
		List<RequestedTraining> requestedTraining = new ArrayList<RequestedTraining>();
		requestedTraining=requestedTrainingDao.listPendingRecords();
		for(RequestedTraining r : requestedTraining)
		{
			Training training = trainingDao.searchRecord(r.getTid());
			if(training.getAvailablecapacity() > 0)
			{
				r.setAccepted(true);
				EnrolledTraining enrolledTraining = new EnrolledTraining(r.getEid(),r.getTid());
				enrolledTrainingDao.insertRecord(enrolledTraining);
				training.setAvailablecapacity(training.getAvailablecapacity()-1);
				trainingDao.updateRecord(training.getTid(), training);
			}
			else
			{
				r.setAccepted(false);
			}
			r.setProcessed(true);
			requestedTrainingDao.updateRecord(r);
		}
	}
	
	@Override
	public List<Feedback> viewTrainingFeedbackDetailed(int trainingId) throws SQLException {
		return feedbackDao.listDetailedFeedback(trainingId);
	}

	@Override
	public String viewTrainingFeedbackConsolidated(int trainingId) throws SQLException {
		return feedbackDao.listConsolidatedFeedback(trainingId);
	}

	@Override
	public ArrayList<RequestedTraining> viewRequestedTraining() throws SQLException {
		return requestedTrainingDao.listPendingRecords();
	}
	
	@Override
	public void autoApproveOfMandateTraining() throws SQLException {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		ArrayList<Integer> allDistinctTrainingIdList = new ArrayList<>();
		ArrayList<Training> mandatoryTraining=new ArrayList<>();
		List<Employee> employeeList = new ArrayList<>();
		TrainingDao trainingDao=new TrainingDaoImpl();
		EmployeeDao employeeDao=new EmployeeDaoImpl();
		employeeList = employeeDao.listAllRecords();
		//System.out.println(employeeList);
		mandatoryTraining=trainingDao.listAllRecords();
		allDistinctTrainingIdList = enrolledTrainingDao.listAllDistinctTrainings();
		//System.out.println(mandatoryTraining);
		for(Training mandatory:mandatoryTraining)
		{
			for(Employee emp:employeeList)
			{
			     if(mandatory.isMandatory()==true && emp.getEmployeeType().equalsIgnoreCase("EMP") && mandatory.getSdate().after(date) && !allDistinctTrainingIdList.contains(mandatory.getTid()))
			     {
			    	//System.out.println(mandatory);
			    	//System.out.println(emp);
			    	EnrolledTraining enrolledTraining = new EnrolledTraining(emp.getEmployeeID(), mandatory.getTid()) ;
			     	enrolledTrainingDao.insertRecord(enrolledTraining);
			     }
			}	
		}		
	}
	
	@Override
	public LinkedHashMap<Integer,String> displayAvailableTrainingFeedback() throws SQLException {	
		return feedbackDao.viewAvailableTrainingFeedback();
	}

	@Override
	public ArrayList<Training> viewTrainings() throws SQLException {
		return trainingDao.listAllRecords();
	}
}
