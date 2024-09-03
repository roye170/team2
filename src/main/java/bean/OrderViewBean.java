package bean;

import java.util.Date;

public class OrderViewBean {
	private String orderNumber;	
    private String patientName;
    private int payStatus;
    private Date orderDate;
    private String treatmentItem;
    private int threapyStatus;
    //後來新增的
    private int pateintAge;
    private int patientGender;
    private String patientPhone;
    private String patientAddress;
    private String patientEmail;
    private String doctorName;
    private String payTool;
    private Date payDate;
    private double quote;
    
    
    
	public int getPateintAge() {
		return pateintAge;
	}
	public void setPateintAge(int pateintAge) {
		this.pateintAge = pateintAge;
	}
	public int getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(int patientGender) {
		this.patientGender = patientGender;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPayTool() {
		return payTool;
	}
	public void setPayTool(String payTool) {
		this.payTool = payTool;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public double getQuote() {
		return quote;
	}
	public void setQuote(double quote) {
		this.quote = quote;
	}
	public String getTreatmentItem() {
		return treatmentItem;
	}
	public void setTreatmentItem(String treatmentItem) {
		this.treatmentItem = treatmentItem;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getThreapyStatus() {
		return threapyStatus;
	}
	public void setThreapyStatus(int threapyStatus) {
		this.threapyStatus = threapyStatus;
	}
    
    
}
