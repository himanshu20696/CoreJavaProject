package com.eums.beans;

//Employee POJO
public class Employee {
	
	String eid,email,epassword,ename,enumber,eaddress,eteam,etype;

	public Employee(String eid, String email, String epassword, String ename, String enumber, String eaddress,
			String eteam, String etype) {
		super();
		this.eid = eid;
		this.email = email;
		this.epassword = epassword;
		this.ename = ename;
		this.enumber = enumber;
		this.eaddress = eaddress;
		this.eteam = eteam;
		this.etype = etype;
	}
	
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEnumber() {
		return enumber;
	}

	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public String getEteam() {
		return eteam;
	}

	public void setEteam(String eteam) {
		this.eteam = eteam;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", email=" + email + ", ename=" + ename + ", enumber=" + enumber + ", eaddress="
				+ eaddress + ", eteam=" + eteam + "]";
	}

}
