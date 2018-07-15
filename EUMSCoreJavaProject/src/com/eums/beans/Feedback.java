package com.eums.beans;

public class Feedback {
	private String eid;
	private int tid;
	private int coverageoftopics;
	private int effectivenessofcource;
	private int presentationstyle;
	private int paceofdelivery;
	private int courceoverall;
	private int traineroverall;

	public Feedback() {

	}

	public Feedback(String eid, int tid, int coverageoftopics, int effectivenessofcource, int presentationstyle,
			int paceofdelivery, int courceoverall, int traineroverall) {
		super();
		this.eid = eid;
		this.tid = tid;
		this.coverageoftopics = coverageoftopics;
		this.effectivenessofcource = effectivenessofcource;
		this.presentationstyle = presentationstyle;
		this.paceofdelivery = paceofdelivery;
		this.courceoverall = courceoverall;
		this.traineroverall = traineroverall;
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

	public int getCoverageoftopics() {
		return coverageoftopics;
	}

	public void setCoverageoftopics(int coverageoftopics) {
		this.coverageoftopics = coverageoftopics;
	}

	public int getEffectivenessofcource() {
		return effectivenessofcource;
	}

	public void setEffectivenessofcource(int effectivenessofcource) {
		this.effectivenessofcource = effectivenessofcource;
	}

	public int getPresentationstyle() {
		return presentationstyle;
	}

	public void setPresentationstyle(int presentationstyle) {
		this.presentationstyle = presentationstyle;
	}

	public int getPaceofdelivery() {
		return paceofdelivery;
	}

	public void setPaceofdelivery(int paceofdelivery) {
		this.paceofdelivery = paceofdelivery;
	}

	public int getCourceoverall() {
		return courceoverall;
	}

	public void setCourceoverall(int courceoverall) {
		this.courceoverall = courceoverall;
	}

	public int getTraineroverall() {
		return traineroverall;
	}

	public void setTraineroverall(int traineroverall) {
		this.traineroverall = traineroverall;
	}

	@Override
	public String toString() {
		return "Feedback [eid=" + eid + ", tid=" + tid + ", coverageoftopics=" + coverageoftopics
				+ ", effectivenessofcource=" + effectivenessofcource + ", presentationstyle=" + presentationstyle
				+ ", paceofdelivery=" + paceofdelivery + ", courceoverall=" + courceoverall + ", traineroverall="
				+ traineroverall + "]";
	}


}
