package bean;
import java.sql.*;

public class OpApp implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int paperno;
	private String ptname;
	private String ptphone;
	private String roomname;
	private Date opappdate;  
	private Time beginTime;
	private Time endTime;
	private String empname;
	private int visit;
	
	public int getPaperno() {
		return paperno;
	}
	public void setPaperno(int paperno) {
		this.paperno = paperno;
	}
	public String getPtname() {
		return ptname;
	}
	public void setPtname(String ptname) {
		this.ptname = ptname;
	}
	public String getPtphone() {
		return ptphone;
	}
	public void setPtphone(String ptphone) {
		this.ptphone = ptphone;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public Date getOpappdate() {
		return opappdate;
	}
	public void setOpappdate(Date opappdate) {
		this.opappdate = opappdate;
	}
	public Time getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	

	
}
