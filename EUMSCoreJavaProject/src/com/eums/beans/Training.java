package com.eums.beans;

public class Training {
	private int tid;
	private String tname;
	private String ttype;
	private String trainername;
	private String sdate;
	private String edate;
	private int maxcapacity;
	private int availablecapacity;
	private boolean mandatory;
	
	public Training() {
		
	}

	public Training(int tid, String tname, String ttype, String trainername, String sdate, String edate,
			int maxcapacity, int availablecapacity, boolean mandatory) {
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

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(int maxcapacity) {
		this.maxcapacity = maxcapacity;
	}

	public int getAvailablecapacity() {
		return availablecapacity;
	}

	public void setAvailablecapacity(int availablecapacity) {
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
		return "Training [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", trainername=" + trainername
				+ ", sdate=" + sdate + ", edate=" + edate + ", maxcapacity=" + maxcapacity + ", availablecapacity="
				+ availablecapacity + ", mandatory=" + mandatory + "]";
	}
	
}
