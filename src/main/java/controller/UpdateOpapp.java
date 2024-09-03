package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;

import bean.OpApp;

@WebServlet("/UpdateOpapp")
public class UpdateOpapp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        String paperno = request.getParameter("paperno");
        String ptName = request.getParameter("pt_name");
        String ptPhone = request.getParameter("pt_phone");
        String roomName = request.getParameter("room_name");
        String opAppDate = request.getParameter("op_appdate");
        String beginTime = request.getParameter("begin_time");
        String endTime = request.getParameter("end_time");
        String empName = request.getParameter("empname");
        int visit = Integer.parseInt(request.getParameter("visit"));
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();

            stmt = conn.prepareStatement(
              "UPDATE opapp SET pt_name=?, pt_phone=?, room_name=?, op_appdate=?, begin_time=?, end_time=?, empname=?, visit=? WHERE paperno=?");

            stmt.setString(1, ptName);
            stmt.setString(2, ptPhone);
            stmt.setString(3, roomName);
            stmt.setString(4, opAppDate);
            stmt.setString(5, beginTime);
            stmt.setString(6, endTime);
            stmt.setString(7, empName);
            stmt.setInt(8, visit);
            stmt.setString(9, paperno);

            stmt.execute();
            
          response.setContentType("text/html;charset=UTF-8");
          response.sendRedirect("/clinc/OpApp2.html");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("发生错误: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        doGet(request, response);
    }
}