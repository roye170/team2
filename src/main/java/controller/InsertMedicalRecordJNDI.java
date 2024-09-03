package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.MedicalRecordBean;
import dao.MedicalRecordCRUD;
@WebServlet("/InsertMedicalRecordJNDI")
public class InsertMedicalRecordJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String mrId = request.getParameter("mrId");
        String appId = request.getParameter("appId");
        String ptId = request.getParameter("ptId");
        String empId = request.getParameter("empId");
        String res = request.getParameter("res");
        String ttrc = request.getParameter("ttrc");
        String pp = request.getParameter("pp");
        Date recordDate = Date.valueOf(request.getParameter("recordDate")); 

        // 新增資料
        MedicalRecordCRUD crud = new MedicalRecordCRUD();
        crud.insert(mrId, appId, ptId, empId, res, ttrc, pp, recordDate);

        // 取得資料
        List<MedicalRecordBean> medicalRecordList = new ArrayList<>();
        medicalRecordList = crud.select();
        request.setAttribute("medicalRecords", medicalRecordList);
        request.getRequestDispatcher("GetMedicalRecord.jsp").forward(request, response);
        response.sendRedirect("GetMedicalRecord.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
