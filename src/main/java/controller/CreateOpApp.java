package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet("/CreateOpApp")
public class CreateOpApp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateOpApp() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();
            
            int paperno = Integer.parseInt(request.getParameter("paperno"));
            String ptname = request.getParameter("pt_name");
            String ptphone = request.getParameter("pt_phone");
            String roomname = request.getParameter("room_name");
            Date opappDate = Date.valueOf(request.getParameter("op_appdate"));
//            Time beginTime = Time.valueOf(request.getParameter("begin_time"));
//            Time endTime = Time.valueOf(request.getParameter("end_time"));
            String beginTime = request.getParameter("begin_time");
            String endTime = request.getParameter("end_time");
            String empname = request.getParameter("empname");
            int visit = Integer.parseInt(request.getParameter("visit"));

            String sql = "INSERT INTO opapp (paperno, pt_name, pt_phone, room_name, op_appdate, begin_time, end_time, empname, visit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, paperno);
            stmt.setString(2, ptname);
            stmt.setString(3, ptphone);
            stmt.setString(4, roomname);
            stmt.setDate(5, opappDate);
            stmt.setString(6, beginTime);
            stmt.setString(7, endTime);
            stmt.setString(8, empname);
            stmt.setInt(9, visit);
            
            stmt.execute();
            
//            request.getRequestDispatcher("/OpappView2").forward(request, response);
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("/clinc/OpApp2.html");
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().println("發生錯誤: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}