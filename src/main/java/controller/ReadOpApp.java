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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.OpApp;
import com.google.gson.Gson;

@WebServlet("/ReadOpApp")
public class ReadOpApp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ReadOpApp() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String papernoParam = request.getParameter("paperno");
        String opappDateParam = request.getParameter("op_appdate");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();
            
            String sql;
            if (papernoParam != null) {
                sql = "SELECT * FROM opapp WHERE paperno=?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(papernoParam));
            } else if (opappDateParam != null) {
                sql = "SELECT * FROM opapp WHERE op_appdate=?";
                stmt = conn.prepareStatement(sql);
                stmt.setDate(1, Date.valueOf(opappDateParam));
            } else {
                throw new ServletException("必須提供 paperno 或 op_appdate 參數");
            }
            
            resultSet = stmt.executeQuery();
            
            List<OpApp> opAppList = new ArrayList<>();
            while (resultSet.next()) {
                OpApp opapp = new OpApp();
                opapp.setPaperno(resultSet.getInt("paperno"));
                opapp.setPtname(resultSet.getString("pt_name"));
                opapp.setPtphone(resultSet.getString("pt_phone"));
                opapp.setRoomname(resultSet.getString("room_name"));
                opapp.setOpappdate(resultSet.getDate("op_appdate"));
                opapp.setBeginTime(resultSet.getTime("begin_time"));
                opapp.setEndTime(resultSet.getTime("end_time"));
                opapp.setEmpname(resultSet.getString("empname"));
                opapp.setVisit(resultSet.getInt("visit"));
                opAppList.add(opapp);
            }
            
            Gson gson = new Gson();
            String json = gson.toJson(opAppList);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("發生錯誤: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}