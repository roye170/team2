package bean;

import java.util.Date;

public class LeaveRecodeBean {
	private String paperNo;
	private String empid;
	private String empname;
	private String type;
	private Date leaveDay;
	private String leaveTimeStarted;
	private double total;
	public String getPaperNo() {
		return paperNo;
	}
	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(Date leaveDay) {
		this.leaveDay = leaveDay;
	}
	public String getLeaveTimeStarted() {
		return leaveTimeStarted;
	}
	public void setLeaveTimeStarted(String time) {
		this.leaveTimeStarted = time;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
