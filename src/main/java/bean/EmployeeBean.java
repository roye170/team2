package bean;

public class EmployeeBean {
	private String empid;
	private String empname;
	private String title;
	private String spec;
	private String empEmail;
	private String passWord;
	private int titleLevel;
	private int empTel;
	private int empGender;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTitleLevel() {
		return titleLevel;
	}
	public void setTitleLevel(int titleLevel) {
		this.titleLevel = titleLevel;
	}
	public int getEmpTel() {
		return empTel;
	}
	public void setEmpTel(int empTel) {
		this.empTel = empTel;
	}
	public int getEmpGender() {
		return empGender;
	}
	public void setEmpGender(int empGender) {
		this.empGender = empGender;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
