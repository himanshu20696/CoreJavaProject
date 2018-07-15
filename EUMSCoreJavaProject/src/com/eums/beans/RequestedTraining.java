package com.eums.beans;

public class RequestedTraining {
	private String eid;
	private int tid;
	private String dateWithTime;
	private boolean accepted;
	private boolean notified;
	private boolean processed;
	
	public RequestedTraining() {
		
	}

	public RequestedTraining(String eid, int tid, String dateWithTime, boolean accepted, boolean notified, boolean processed) {
		super();
		this.eid = eid;
		this.tid = tid;
		this.dateWithTime = dateWithTime;
		this.accepted = accepted;
		this.notified = notified;
		this.processed = processed;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getDateWithTime() {
		return dateWithTime;
	}

	public void setDateWithTime(String dateWithTime) {
		this.dateWithTime = dateWithTime;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
	public String toString() {
		return "RequestedTraining [eid=" + eid + ", tid=" + tid + ", dateWithTime=" + dateWithTime + ", accepted="
				+ accepted + ", notified=" + notified + ", processed=" + processed + "]";
	}
	
	
}
