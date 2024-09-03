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

@WebServlet("/OrderUpdate")
public class OrderUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderUpdate() {
		super();
	}

	Connection connection = null;
	PreparedStatement preparedStatement1;
	ResultSet resultSet1;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String empno = request.getParameter("empno");
		try {
			
			connection = Util.JDBCutil.getConnection();
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=order;encrypt=false",
//						"kai", "1234");
			String orderNumber = request.getParameter("orderNumber");
	        String patientName = request.getParameter("patientName");
	        int patientAge = Integer.parseInt(request.getParameter("patientAge"));
	        int patientGender = Integer.parseInt(request.getParameter("patientGender"));
	        String patientPhone = request.getParameter("patientPhone");
	        String patientAddress = request.getParameter("patientAddress");
	        String patientEmail = request.getParameter("patientEmail");
	        String doctorName = request.getParameter("doctorName");
	        double quote = Double.parseDouble(request.getParameter("quote"));
	        int payStatus =  Integer.parseInt(request.getParameter("payStatus"));
	        String payTool = request.getParameter("paymentMethod");
//	        Date payDate = Date.valueOf(request.getParameter("paymentDate"));
	        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
	        String treatmentItem = request.getParameter("treatmentItem");
            int thStatus = Integer.parseInt(request.getParameter("treatmentStatus"));
                        
            //如果發生還沒付款的狀況，沒辦法轉成Date
            String paymentDate = request.getParameter("paymentDate");
            Date payDate = null;
            if (!paymentDate.isEmpty()) {
                payDate = Date.valueOf(paymentDate);
            }

            
	        
            String sql1 = "UPDATE OrderContent SET pt_name = ?, pt_age = ?, pt_gender = ?, pt_phone = ?, pt_address = ?, pt_email = ?, empname = ?, orderdate = ?, paytool = ?, paydate = ?, price = ?, pay_status = ?, treatment_item = ?, th_status = ? WHERE ordernum = ?";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, patientName);
            preparedStatement1.setInt(2, patientAge);
            preparedStatement1.setInt(3, patientGender);
            preparedStatement1.setString(4, patientPhone);
            preparedStatement1.setString(5, patientAddress);
            preparedStatement1.setString(6, patientEmail);
            preparedStatement1.setString(7, doctorName);
            preparedStatement1.setDate(8, orderDate);
            preparedStatement1.setString(9, payTool);
            preparedStatement1.setDate(10, payDate);
            preparedStatement1.setDouble(11, quote);
            preparedStatement1.setInt(12, payStatus);
            preparedStatement1.setString(13, treatmentItem);
            preparedStatement1.setInt(14, thStatus);
            preparedStatement1.setString(15, orderNumber);

			preparedStatement1.execute();			
			
		} catch (SQLException e) {
			e.printStackTrace();

//		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();

		}finally {
			//確保出錯時還會導向網頁
			JDBCutil.closeResource(connection,preparedStatement1,resultSet1);
			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("/clinc/OrderView.html");
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}