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

import com.google.gson.Gson;

@WebServlet("/empUpdate")
public class empUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection conn;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
            conn = ds.getConnection();


            String sql = "UPDATE emp SET empname=?, title=?, titlelevel=?, emp_tel=?, emp_gender=?, spec=?, emp_email=?, password=? WHERE emp_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);


            stmt.setString(1, request.getParameter("empname"));
            stmt.setString(2, request.getParameter("title"));
            stmt.setString(3, request.getParameter("titlelevel"));
            stmt.setString(4, request.getParameter("emp_tel"));
            stmt.setString(5, request.getParameter("emp_gender"));
            stmt.setString(6, request.getParameter("spec"));
            stmt.setString(7, request.getParameter("emp_email"));
            stmt.setString(8, request.getParameter("password"));
            stmt.setString(9, request.getParameter("emp_id"));

 
            int rowsAffected = stmt.executeUpdate();


            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String jsonResponse;

            if (rowsAffected > 0) {
                jsonResponse = gson.toJson(new Response("success", "Update successful"));
            } else {
                jsonResponse = gson.toJson(new Response("failure", "Update failed"));
            }

            out.print(jsonResponse);
            out.flush();


            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    private class Response {
        private String status;
        private String message;

        public Response(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
