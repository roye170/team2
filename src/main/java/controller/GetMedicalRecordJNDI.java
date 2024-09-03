package controller;

import java.io.IOException;
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

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetMedicalRecordJNDI") 
public class GetMedicalRecordJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // 設定返回的 MIME 類型和編碼
            response.setContentType("text/html;charset=UTF-8");

            // 初始化 JNDI 上下文並查找數據源
            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();

            // 查詢所有病歷記錄
            String sql = "SELECT * FROM MedicalRecord";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            // 將查詢結果轉換為 MedicalRecordBean 的列表
            List<MedicalRecordBean> medicalRecords = new ArrayList<>();
            while (rs.next()) {
                MedicalRecordBean medicalRecord = new MedicalRecordBean();
                medicalRecord.setMrId(rs.getString("mr_id"));
                medicalRecord.setAppId(rs.getString("app_id"));
                medicalRecord.setPtId(rs.getString("pt_id"));
                medicalRecord.setEmpId(rs.getString("emp_id"));
                medicalRecord.setRes(rs.getString("res"));
                medicalRecord.setTtrc(rs.getString("ttrc"));
                medicalRecord.setPp(rs.getString("pp"));
                medicalRecord.setRecordDate(rs.getDate("record_date"));
                medicalRecords.add(medicalRecord);
            }

            // 將病歷列表添加到請求屬性中並轉發到 JSP 頁面
            request.setAttribute("medicalRecords", medicalRecords);
            request.getRequestDispatcher("GetMedicalRecord.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}