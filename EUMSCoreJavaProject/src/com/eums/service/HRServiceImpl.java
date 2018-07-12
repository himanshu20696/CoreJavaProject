package com.eums.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eums.beans.Employee;
import com.eums.beans.Feedback;
import com.eums.beans.RequestedTraining;
import com.eums.beans.Training;
import com.eums.dao.RequestedTrainingDao;
import com.eums.dao.RequestedTrainingDaoImpl;
import com.eums.dao.TrainingDao;
import com.eums.dao.TrainingDaoImpl;

public class HRServiceImpl implements HRService {

	private TrainingDao trainingDao = new TrainingDaoImpl();
	private RequestedTrainingDao requestedTrainingDao = new RequestedTrainingDaoImpl(); 
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
	public ArrayList<Employee> viewEmployeeEnrolledForTraining(int trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveEnrollmentOfTraining(int employeeId, int trainingId) throws SQLException {
		List<RequestedTraining> requestedTraining = new ArrayList<RequestedTraining>();
		requestedTraining=requestedTrainingDao.listAllRecords();
		Training training = new Training();
		training = trainingDao.searchRecord(trainingId);
		for(RequestedTraining r : requestedTraining)
		{
			if(!r.isAccepted() && training!=null)
			{
				if(r.getDateWithTime()<training.getSdate() && 
						training.getAvailablecapacity()<training.getMaxcapacity())
				{
					//Enrolled_for_training()
				}
			}
		}
	}

	@Override
	public ArrayList<Feedback> viewTrainingFeedback(int trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
