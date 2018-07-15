package com.eums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eums.beans.Employee;
import com.eums.helper.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	public List<Employee> listAllRecords() throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		ArrayList<Employee> employeeDetails=new ArrayList<>();
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from company");
		Employee employee=null;
		while(rs.next()){
			String employeeId=rs.getString(1);
			String employeeName=rs.getString(2);
			String employeeEmail=rs.getString(3);
			String employeePassword=rs.getString(4);
			String employeeType=rs.getString(5);
			String employeeTeam=rs.getString(6);
			String employeeAddress=rs.getString(7);
			String employeeContactNo=rs.getString(8);
			employee=new Employee(employeeId,employeeName,employeeEmail,employeePassword,
					employeeType,employeeTeam,employeeAddress,employeeContactNo);
			employeeDetails.add(employee);
		}

		return employeeDetails;
	}

	@Override
	public Employee searchRecord(String employeeId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from employee_details where eid="+"'"+employeeId+"'");
		Employee employee=null;

		while(rs.next()){
			String employeeName=rs.getString("ename");
			String employeeEmail=rs.getString("eemail");
			String employeePassword=rs.getString("epassword");
			String employeeType=rs.getString("etype");
			String employeeTeam=rs.getString("eteam");
			String employeeAddress=rs.getString("eaddress");
			String employeeContactNo=rs.getString("enumber");
			employee=new Employee(employeeId,employeeName,employeeEmail,employeePassword,
					employeeType,employeeTeam,employeeAddress,employeeContactNo);
		}
		return employee;
	}

	@Override
	public boolean insertRecord(Employee employee) throws SQLException {
		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("insert into employee_details values(?,?,?,?,?,?,?,?)");		
		pst.setString(1, employee.getEmployeeID());
		pst.setString(2, employee.getEmployeeName());
		pst.setString(3, employee.getEmployeeEmail());
		pst.setString(4, employee.getEmployeePassword());
		pst.setString(5, employee.getEmployeeType());
		pst.setString(6, employee.getEmployeeTeam());
		pst.setString(7, employee.getEmployeeAddress());
		pst.setString(8, employee.getEmployeeContactNo());

		int rows=pst.executeUpdate();

		if(rows <= 0)
			return false;		

		return true;
	}

	@Override
	public boolean deleteRecord(String employeeId) throws SQLException {
		Connection con=null; 
		Statement stmt=null;
		con=DBConnection.getDBConnection();
		stmt=con.createStatement();
		int rows = stmt.executeUpdate("delete from employee_details where eid="+employeeId);
		if(rows <= 0)
			return false;

		return true;
	}

	@Override
	public boolean updateRecord(String employeeId, Employee newEmployee) throws SQLException {

		Connection con=null; 
		PreparedStatement pst=null;
		con=DBConnection.getDBConnection();
		pst=con.prepareStatement("update employee_details set "
				+ "ename=?"
				+ "eemail=?"
				+ "epassword=?"
				+ "etype=?"
				+ "eteam=?"
				+ "eaddress=?"
				+ "econtactno=?"
				+ "where eid=?");
		pst.setString(1, newEmployee.getEmployeeName());
		pst.setString(2, newEmployee.getEmployeeEmail());
		pst.setString(3, newEmployee.getEmployeePassword());
		pst.setString(4, newEmployee.getEmployeeType());
		pst.setString(5, newEmployee.getEmployeeTeam());
		pst.setString(6, newEmployee.getEmployeeAddress());
		pst.setString(7, newEmployee.getEmployeeContactNo());
		pst.setString(8, employeeId);
		int rows = pst.executeUpdate();
		if(rows <= 0)
			return false;

		return true;
	}

}
