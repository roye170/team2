package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.MedicalRecordBean;

public class MedicalRecordCRUD {

    // 插入 MedicalRecord 資料
    public void insert(String mrId, String appId, String ptId, String empId, String res, String ttrc, String pp, java.sql.Date recordDate) {
        Connection connection;
        PreparedStatement statement;
        Context context;
        DataSource datasource;
        String sql = "INSERT INTO MedicalRecord (mr_id, app_id, pt_id, emp_id, res, ttrc, pp, record_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            // 連線
            context = new InitialContext();
            datasource = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            connection = datasource.getConnection();
            statement = connection.prepareStatement(sql);
            
            // 設定資料
            statement.setString(1, mrId);
            statement.setString(2, appId);
            statement.setString(3, ptId);
            statement.setString(4, empId);
            statement.setString(5, res);
            statement.setString(6, ttrc);
            statement.setString(7, pp);
            statement.setDate(8, recordDate);
            
            statement.execute();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 查詢 MedicalRecord 資料
    public List<bean.MedicalRecordBean> select() {
        Connection connection = null;
        PreparedStatement statement;
        Context context;
        DataSource datasource;
        String sql = "SELECT * FROM MedicalRecord";
        ResultSet resultset;
        List<bean.MedicalRecordBean> medicalRecordList = new ArrayList<>();
        
        try {
            context = new InitialContext();
            datasource = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            connection = datasource.getConnection();
            statement = connection.prepareStatement(sql);
            resultset = statement.executeQuery();
            bean.MedicalRecordBean medicalRecord = null;
            
            while (resultset.next()) {
                medicalRecord = new bean.MedicalRecordBean();
                medicalRecord.setMrId(resultset.getString("mr_id"));
                medicalRecord.setAppId(resultset.getString("app_id"));
                medicalRecord.setPtId(resultset.getString("pt_id"));
                medicalRecord.setEmpId(resultset.getString("emp_id"));
                medicalRecord.setRes(resultset.getString("res"));
                medicalRecord.setTtrc(resultset.getString("ttrc"));
                medicalRecord.setPp(resultset.getString("pp"));
                medicalRecord.setRecordDate(resultset.getDate("record_date"));
                
                medicalRecordList.add(medicalRecord);
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return medicalRecordList;
    }
    public MedicalRecordBean selectByPtId(String ptId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        MedicalRecordBean medicalRecord = null;
        Context context;
        DataSource datasource;

        try {
            // 連線
            context = new InitialContext();
            datasource = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            connection = datasource.getConnection();
            String sql = "SELECT * FROM medical_record WHERE pt_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, ptId);
            resultSet = statement.executeQuery();

            // 查詢結果處理
            if (resultSet.next()) {
                medicalRecord = new MedicalRecordBean();
                medicalRecord.setMrId(resultSet.getString("mr_id"));
                medicalRecord.setAppId(resultSet.getString("app_id"));
                medicalRecord.setPtId(resultSet.getString("pt_id"));
                medicalRecord.setEmpId(resultSet.getString("emp_id"));
                medicalRecord.setRes(resultSet.getString("res"));
                medicalRecord.setTtrc(resultSet.getString("ttrc"));
                medicalRecord.setPp(resultSet.getString("pp"));
                medicalRecord.setRecordDate(resultSet.getDate("record_date"));
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return medicalRecord;
    }


    // 刪除 MedicalRecord 資料
    public void delete(String mrId) {
        Connection connection;
        PreparedStatement statement;
        Context context;
        DataSource datasource;
        String sql = "DELETE FROM MedicalRecord WHERE mr_id = ?";
        
        try {
            // 連線
            context = new InitialContext();
            datasource = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            connection = datasource.getConnection();
            statement = connection.prepareStatement(sql);
            
            // 設定資料
            statement.setString(1, mrId);
            statement.execute();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新 MedicalRecord 資料
    public void update(String mrId, String newRes, String newTtrc, String newPp) {
        Connection connection;
        PreparedStatement statement;
        Context context;
        DataSource datasource;
        String sql = "UPDATE MedicalRecord SET res = ?, ttrc = ?, pp = ? WHERE mr_id = ?";
        
        try {
            // 連線
            context = new InitialContext();
            datasource = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            connection = datasource.getConnection();
            statement = connection.prepareStatement(sql);
            
            // 設定資料
            statement.setString(1, newRes);
            statement.setString(2, newTtrc);
            statement.setString(3, newPp);
            statement.setString(4, mrId);
            statement.execute();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
}
