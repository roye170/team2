package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Util.JDBCutil;

@WebServlet("/OrderDelete")
public class OrderDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderDelete() {
		super();
	}

	Connection connection = null;
	PreparedStatement preparedStatement1;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {		
//			connection = utils.JDBCutil.getConnection();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=order;encrypt=false",
						"kai", "1234");
			
			String orderNumber = request.getParameter("ordernum");
     
            String sql1 = "DELETE FROM OrderContent WHERE ordernum = ?";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, orderNumber);
			preparedStatement1.execute();
						


			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("/clinc/OrderView.html");
			connection.close();
			preparedStatement1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}