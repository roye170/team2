package bean;

public class PatientBean {
	private int patientId;
    private String patientName;
    private int patientAge;
    private int patientGender;
    private int patientPhone;
    private String patientAddress;
    private String patientEmail;
    private String patientAllergies;

    //Getter & Setter
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public int getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(int patientGender) {
		this.patientGender = patientGender;
	}
	public int getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(int patientPhone) {
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
	public String getPatientAllergies() {
		return patientAllergies;
	}
	public void setPatientAllergies(String patientAllergies) {
		this.patientAllergies = patientAllergies;
	}


}
