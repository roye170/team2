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

@WebServlet("/OrderAdd")
public class OrderAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderAdd() {
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
            }else {
            	payDate = Date.valueOf("0001-01-01");
            }

            	        
            String sql1 = "INSERT INTO OrderContent (ordernum, pt_name, pt_age, pt_gender, pt_phone, pt_address, pt_email, empname, orderdate, paytool, paydate, price, pay_status, treatment_item, th_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, orderNumber);
            preparedStatement1.setString(2, patientName);
            preparedStatement1.setInt(3, patientAge);
            preparedStatement1.setInt(4, patientGender);
            preparedStatement1.setString(5, patientPhone);
            preparedStatement1.setString(6, patientAddress);
            preparedStatement1.setString(7, patientEmail);
            preparedStatement1.setString(8, doctorName);
            preparedStatement1.setDate(9, orderDate);
            preparedStatement1.setString(10, payTool);
            preparedStatement1.setDate(11, payDate);
            preparedStatement1.setDouble(12, quote);
            preparedStatement1.setInt(13, payStatus);
            preparedStatement1.setString(14, treatmentItem);
            preparedStatement1.setInt(15, thStatus);

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