package com.eums.beans;

public class EnrolledTraining {
	private String employeeId;
	private int trainingId;

	public EnrolledTraining()
	{

	}

	public EnrolledTraining(String employeeId, int trainingId) {
		super();
		this.employeeId = employeeId;
		this.trainingId = trainingId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	@Override
	public String toString() {
		return "\nEnrolledTraining [employeeId=" + employeeId + ", trainingId=" + trainingId + "]";
	}
}
