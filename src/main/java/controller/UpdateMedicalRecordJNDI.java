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
@WebServlet("/UpdateMedicalRecordJNDI")
public class UpdateMedicalRecordJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 從請求中獲取需要更新的資料
        String mrId = request.getParameter("mrId");
        String newRes = request.getParameter("res");
        String newTtrc = request.getParameter("ttrc");
        String newPp = request.getParameter("pp");

        // 更新資料
        MedicalRecordCRUD crud = new MedicalRecordCRUD();
        crud.update(mrId, newRes, newTtrc, newPp);

        // 取得更新後的資料
        List<MedicalRecordBean> medicalRecordList = new ArrayList<>();
        medicalRecordList = crud.select();
        request.setAttribute("medicalRecords", medicalRecordList);

        // 將資料傳給 JSP 頁面
        request.getRequestDispatcher("GetMedicalRecord.jsp").forward(request, response);
        response.sendRedirect("GetMedicalRecord.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}


