package emp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.sql.DataSource;

import com.google.gson.Gson;

@WebServlet("/empDelete")
public class empDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection conn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empid = request.getParameter("emp_id");
        boolean success = false;
        String message = "";

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();

            String sql = "DELETE FROM emp WHERE emp_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, empid);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                message = "員工資料已成功刪除";
            } else {
                message = "找不到對應的員工資料";
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
        	  e.printStackTrace();
			    throw new ServletException(e);
        }

        ResponseObj responseObj = new ResponseObj(success, message);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseObj);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    class ResponseObj {
        private boolean success;
        private String message;

        public ResponseObj(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}