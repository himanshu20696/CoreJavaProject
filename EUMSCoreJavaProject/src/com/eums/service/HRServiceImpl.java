package com.eums.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public boolean createTrainingInCalender(Training training) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean approveEnrollmentOfTraining(String employeeId, int trainingId) throws SQLException {
		//call listpendingrecords for display from presentation layer
		List<RequestedTraining> requestedTraining = new ArrayList<RequestedTraining>();
		Training training = new Training();
		training = trainingDao.searchRecord(trainingId);
		requestedTraining=requestedTrainingDao.listPendingRecords();
		Boolean update=false;
		for(RequestedTraining r : requestedTraining)
		{
			if(Date.valueOf(r.getDateWithTime()).before(Date.valueOf(training.getSdate())) &&  
					training.getAvailablecapacity()<training.getMaxcapacity())
			{
				r.setAccepted(true);
				EnrolledTraining enrolledTraining = new EnrolledTraining(r.getEid(),r.getTid());
				enrolledTrainingDao.insertRecord(enrolledTraining);
			}
			r.setProcessed(true);
			update=requestedTrainingDao.updateRecord(trainingId, employeeId, r);
		}
		//Display records from requested_training
		return true;
	}

	@Override
	public List<Feedback> viewTrainingFeedbackDetailed(int trainingId) throws SQLException {

		return feedbackDao.listDetailedFeedback(trainingId);
	}

	@Override
	public String viewTrainingFeedbackConsolidated(int trainingId) throws SQLException {
		// TODO Auto-generated method stub
		return feedbackDao.listConsolidatedFeedback(trainingId);
	}

}
