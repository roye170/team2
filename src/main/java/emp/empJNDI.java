package emp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

import com.google.gson.Gson;

import bean.empBean;

@WebServlet("/empJNDI")
public class empJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn=null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    try {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinc");
	        conn = ds.getConnection();

	        String sql = ("SELECT * FROM emp");
	                
      PreparedStatement stmt = conn.prepareStatement(sql);

	        ResultSet rs = stmt.executeQuery();
	        List<empBean> emps = new ArrayList<>();
	        while (rs.next()) {
	            empBean emp = new empBean();
	            emp.setEmpid(rs.getString("emp_id"));
	            emp.setEmpname(rs.getString("empname"));
	            emp.setTitle(rs.getString("title"));
	            emp.setTitleLevel(rs.getString("titlelevel"));
	            emp.setEmpTel(rs.getString("emp_tel"));
	            emp.setEmpGender(rs.getString("emp_gender"));
	            emp.setSpec(rs.getString("spec"));
	            emp.setEmpEmail(rs.getString("emp_email"));
	            emp.setPassWord(rs.getString("password"));
	            emps.add(emp);
	        }
	        Gson gson = new Gson();
	        String json = gson.toJson(emps);
	        

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);
		} catch (Exception e) {
			    e.printStackTrace();
		
		}finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {

            }
        }

		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);
	

	}
}
	

