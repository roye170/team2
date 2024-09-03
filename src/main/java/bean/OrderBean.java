package bean;

import java.util.Date;

public class OrderBean {
		private String orderNumber;
	    private int patientId;
	    private String patientName;
	    private String paperNumber;
	    private String applicationId;
	    private String empName;
	    private Date orderDate;
	    private String payTool;
	    private Date payDate;
	    private int price;
	    private int payStatus;
	    //療程

	    private String treatmentId;
	    private int treatmentStatus;


	    
		public String getPatientName() {
			return patientName;
		}
		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}
		public String getTreatmentId() {
			return treatmentId;
		}
		public void setTreatmentId(String treatmentId) {
			this.treatmentId = treatmentId;
		}
		public int getTreatmentStatus() {
			return treatmentStatus;
		}
		public void setTreatmentStatus(int treatmentStatus) {
			this.treatmentStatus = treatmentStatus;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public int getPatientId() {
			return patientId;
		}
		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}
		public String getPaperNumber() {
			return paperNumber;
		}
		public void setPaperNumber(String paperNumber) {
			this.paperNumber = paperNumber;
		}
		public String getApplicationId() {
			return applicationId;
		}
		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public Date getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
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
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getPayStatus() {
			return payStatus;
		}
		public void setPayStatus(int payStatus) {
			this.payStatus = payStatus;
		}

	}


