package bean;
import java.io.*;
import java.sql.Date;

public class MedicalRecordBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    private String mrId;
    private String appId;
    private String ptId;
    private String empId;
    private String res;
    private String ttrc;
    private String pp;
    private Date recordDate;

   
    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getTtrc() {
        return ttrc;
    }

    public void setTtrc(String ttrc) {
        this.ttrc = ttrc;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}


