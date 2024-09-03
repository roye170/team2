package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.MedicalRecordBean;
import dao.MedicalRecordCRUD;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/DeleteMedicalRecordJNDI")
public class DeleteMedicalRecordJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 從請求中獲取病歷ID
        String mrId = request.getParameter("mrId");

        // 刪除資料
        MedicalRecordCRUD crud = new MedicalRecordCRUD();
        crud.delete(mrId);

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