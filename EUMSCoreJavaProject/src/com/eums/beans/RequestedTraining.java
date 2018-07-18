package com.eums.beans;

import java.sql.Timestamp;

public class RequestedTraining {
	private String eid;
	private int tid;
	private Timestamp dateWithTime;
	private boolean accepted;
	private boolean notified;
	private boolean processed;

	public RequestedTraining() {

	}

	public RequestedTraining(String eid, int tid, Timestamp dateWithTime, boolean accepted, boolean notified, boolean processed) {
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

	public Timestamp getDateWithTime() {
		return dateWithTime;
	}

	public void setDateWithTime(Timestamp dateWithTime) {
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
		return "\nRequestedTraining [eid=" + eid + ", tid=" + tid + ", dateWithTime=" + dateWithTime + ", accepted="
				+ accepted + ", notified=" + notified + ", processed=" + processed + "]";
	}


}
