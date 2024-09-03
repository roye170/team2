package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import bean.LeaveRecodeBean;
import dao.LeaveRecordCRUD;

public class LeaveRecordJNDI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LeaveRecordJNDI() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LeaveRecordCRUD crud = new LeaveRecordCRUD();
        
        if ("getRecord".equals(action)) {
            String paperNo = request.getParameter("paperNo");
            // Retrieve specific record
            LeaveRecodeBean leaveRecord = crud.SelectOne(paperNo);
            // Convert the LeaveRecodeBean to JSON
            Gson gson = new Gson();
            String json = gson.toJson(leaveRecord);
            // Set content type and write JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else if ("getPaperNo".equals(action)) {
            String paperNo = crud.SelectMaxPaperNo();
            DecimalFormat df = new DecimalFormat("000000");
            int sqlPaperNo = paperNo != null && !paperNo.isEmpty() ? Integer.parseInt(paperNo) : 0;
            paperNo = "LV" + df.format(sqlPaperNo + 1);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(paperNo);

        } else {
            // Retrieve all records
            List<LeaveRecodeBean> leaveRecordList = crud.Select();

            // Convert the list of LeaveRecodeBean to JSON
            Gson gson = new Gson();
            String json = gson.toJson(leaveRecordList);
            
            // Set content type and write JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LeaveRecordCRUD crud = new LeaveRecordCRUD();

        if ("update".equals(action)) {
            String paperno = request.getParameter("paperNo");
            String empid = request.getParameter("empid");
            String empname = request.getParameter("empname");
            String type = request.getParameter("type");
            String leaveDay = request.getParameter("leaveDay");
            String leaveTimeStarted = request.getParameter("leaveTimeStarted");
            String total = request.getParameter("total");

            // Update the record
            crud.Update(paperno, empid, empname, type, leaveDay, leaveTimeStarted, Double.parseDouble(total));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Record updated successfully.\"}");

        } else if ("delete".equals(action)) {
            String paperno = request.getParameter("paperNo");
            // Handle delete action
            crud.Delete(paperno);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Record deleted successfully.\"}");
        } else if ("insert".equals(action)) {
        	String paperno = request.getParameter("paperNo");
            String empid = request.getParameter("empid");
            String empname = request.getParameter("empname");
            String type = request.getParameter("type");
            String leaveDay = request.getParameter("leaveDay");
            String leaveTimeStarted = request.getParameter("leaveTimeStarted");
            String total = request.getParameter("total");
            crud.Insert(paperno, empid, empname, type, leaveDay, leaveTimeStarted, Double.parseDouble(total));
        }
    }
}
