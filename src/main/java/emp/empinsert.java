package emp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

@WebServlet("/empinsert")
public class empinsert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            Connection conn = ds.getConnection();

            String sql = "INSERT INTO emp (emp_id, empname, title, titlelevel, emp_tel, emp_gender, spec, emp_email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, request.getParameter("emp_id"));
            stmt.setString(2, request.getParameter("empname"));
            stmt.setString(3, request.getParameter("title"));
            stmt.setString(4, request.getParameter("titlelevel"));
            stmt.setString(5, request.getParameter("emp_tel"));
            stmt.setString(6, request.getParameter("emp_gender"));
            stmt.setString(7, request.getParameter("spec"));
            stmt.setString(8, request.getParameter("emp_email"));
            stmt.setString(9, request.getParameter("password"));

            int affectedRows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (affectedRows > 0) {
                out.print("{\"success\": true, \"message\": \"Employee added successfully.\"}");
            } else {
                out.print("{\"success\": false, \"message\": \"Failed to add employee.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
    }
}