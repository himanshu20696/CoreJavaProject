package com.eums.beans;

import java.sql.Date;

public class Training {
	private int tid;
	private String tname;
	private String ttype;
	private String trainername;
	private Date sdate;
	private Date edate;
	private Integer maxcapacity;
	private Integer availablecapacity;
	private boolean mandatory;

	public Training() {

	}

	public Training(int tid, String tname, String ttype, String trainername, Date sdate, Date edate,
			Integer maxcapacity, Integer availablecapacity, boolean mandatory) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ttype = ttype;
		this.trainername = trainername;
		this.sdate = sdate;
		this.edate = edate;
		this.maxcapacity = maxcapacity;
		this.availablecapacity = availablecapacity;
		this.mandatory = mandatory;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTrainername() {
		return trainername;
	}

	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Integer getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(Integer maxcapacity) {
		this.maxcapacity = maxcapacity;
	}

	public Integer getAvailablecapacity() {
		return availablecapacity;
	}

	public void setAvailablecapacity(Integer availablecapacity) {
		this.availablecapacity = availablecapacity;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String toString() {
		return "\nTraining [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", trainername=" + trainername
				+ ", sdate=" + sdate + ", edate=" + edate + ", maxcapacity=" + maxcapacity + ", availablecapacity="
				+ availablecapacity + ", mandatory=" + mandatory + "]";
	}

}
