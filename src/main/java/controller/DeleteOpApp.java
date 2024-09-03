package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet("/DeleteOpApp")
public class DeleteOpApp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public DeleteOpApp() {
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
            
            String sql = "DELETE FROM opapp WHERE paperno=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, paperno);
            
            int rowsAffected = stmt.executeUpdate();
            
            response.setContentType("text/plain;charset=UTF-8");
            if (rowsAffected > 0) {
                response.getWriter().write("刪除成功");
            } else {
                response.getWriter().write("刪除失敗，找不到指定的記錄");
            }
            
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("發生錯誤: " + e.getMessage());
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