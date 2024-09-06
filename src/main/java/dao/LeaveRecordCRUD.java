package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.LeaveRecodeBean;

public class LeaveRecordCRUD {
	public void Insert(String paperNo, String empid, String empname, String type,
			String leaveDay, String leaveTimeStarted, double total) {
		String sql = "INSERT INTO leaverecord VALUES(?,?,?,?,?,?,?)";
		try(Connection connection = Util.ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1,paperNo);
			statement.setString(2,empid);
			statement.setString(3,empname);
			statement.setString(4,type);
			statement.setString(5,leaveDay);
			statement.setString(6,leaveTimeStarted);
			statement.setDouble(7,total);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<LeaveRecodeBean> Select(){
		String sql = "SELECT * FROM leaverecord";
		List<LeaveRecodeBean> leaverecordlist = new ArrayList<>();
		try(Connection connection = Util.ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultset = statement.executeQuery();){
			LeaveRecodeBean leaverecord = null;
			while (resultset.next()) {
				leaverecord = new LeaveRecodeBean();
				leaverecord.setPaperNo(resultset.getString("paperno"));
				leaverecord.setEmpid(resultset.getString("empid"));
				leaverecord.setEmpname(resultset.getString("empname"));
				leaverecord.setType(resultset.getString("type"));
				String date = resultset.getString("lvday");
				Date sqldate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
				leaverecord.setLeaveDay(sqldate);
				String time = resultset.getString("lvtime");
				leaverecord.setLeaveTimeStarted(time);
				leaverecord.setTotal(resultset.getDouble("total"));
				leaverecordlist.add(leaverecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return leaverecordlist;
	}
	
	public LeaveRecodeBean SelectOne(String paperNo) {
	    LeaveRecodeBean record = null;
	    String sql = "SELECT * FROM leaverecord WHERE paperNo = ?";
	    try (Connection connection = Util.ConnectionUtil.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, paperNo);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                record = new LeaveRecodeBean();
	                record.setPaperNo(resultSet.getString("paperNo"));
	                record.setEmpid(resultSet.getString("empid"));
	                record.setEmpname(resultSet.getString("empname"));
	                record.setType(resultSet.getString("type"));
	                record.setLeaveDay(resultSet.getDate("leaveDay"));
	                record.setLeaveTimeStarted(resultSet.getString("leaveTimeStarted"));
	                record.setTotal(resultSet.getDouble("total"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return record;
	}
	
	public String SelectMaxPaperNo() {
		String sql = "SELECT MAX(SUBSTRING(paperno, 3,8)) paperno FROM leaverecord";
		String ans = null;
		try(Connection connection = Util.ConnectionUtil.getConnection();
		    PreparedStatement statement = connection.prepareStatement(sql);
		    ){
			try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	            	ans = resultSet.getString("paperNo");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public void Delete(String paperNo) {
		String sql = "DELETE FROM leaverecord WHERE paperno = ?";
		try(Connection connection = Util.ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1,paperNo);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Update(String paperNo, String empid, String empname, String type,
			String lvday, String lvtime, double total) {
		String sql = "UPDATE leaverecord SET empid = ?,"
				+ "empname = ?, type = ?, lvday = ?, lvtime = ?, total = ?"
				+ "WHERE paperno = ?";
		try(Connection connection = Util.ConnectionUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
				statement.setString(1,empid);
				statement.setString(2,empname);
				statement.setString(3,type);
				statement.setString(4,lvday);
				statement.setString(5,lvtime);
				statement.setDouble(6,total);
				statement.setString(7,paperNo);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
