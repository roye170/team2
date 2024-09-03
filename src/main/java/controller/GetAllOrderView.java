package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.OrderViewBean;
import Util.JDBCutil;

@WebServlet("/GetAllOrderView")
public class GetAllOrderView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAllOrderView() {
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

			String sql = "SELECT * FROM OrderContent;";
			preparedStatement1 = connection.prepareStatement(sql);
			resultSet1 = preparedStatement1.executeQuery();
			List<OrderViewBean> orderInfo = new ArrayList<>();
			OrderViewBean orderViewBean = null;
			
			while (resultSet1.next()) {
				orderViewBean = new OrderViewBean();
				
//				Date date = new Date(); 
//				Date paydate;
//	            if (resultSet1.getDate("paydate")== null) {
//	                paydate = Date("2012-09-10");
//	            }else {
//	            	paydate = resultSet1.getDate("paydate");
//	            }
				orderViewBean.setOrderNumber(resultSet1.getString("ordernum"));
				orderViewBean.setPatientName(resultSet1.getString("pt_name"));
				orderViewBean.setPayStatus(resultSet1.getInt("pay_status"));
				orderViewBean.setOrderDate(resultSet1.getDate("orderdate")); 
				orderViewBean.setThreapyStatus(resultSet1.getInt("th_status"));
				orderViewBean.setTreatmentItem(resultSet1.getString("treatment_item"));
			    orderViewBean.setPateintAge(resultSet1.getInt("pt_age")); 
			    orderViewBean.setPatientGender(resultSet1.getInt("pt_gender"));
			    orderViewBean.setPatientPhone(resultSet1.getString("pt_phone"));
			    orderViewBean.setPatientAddress(resultSet1.getString("pt_address"));
			    orderViewBean.setPatientEmail(resultSet1.getString("pt_email"));
			    orderViewBean.setDoctorName(resultSet1.getString("empname"));
			    orderViewBean.setPayTool(resultSet1.getString("paytool"));
			    orderViewBean.setPayDate((resultSet1.getDate("paydate")));
			    orderViewBean.setQuote(resultSet1.getDouble("price"));
				
				orderInfo.add(orderViewBean);
			}
			//用gson把list轉成json
			Gson gson = new Gson();
			String json = gson.toJson(orderInfo);
			response.getWriter().write(json);
			
//			preparedStatement1.close();
//			resultSet1.close();
			
			
//			//查詢2
//			preparedStatement2 = connection.prepareStatement(sql2);
//            resultSet2 = preparedStatement2.executeQuery();
//            List<String> treatmentStatus = new ArrayList<>();
//            while (resultSet2.next()) {
//                treatmentStatus.add(resultSet2.getString("th_status"));
//            }
//            request.setAttribute("orderInfoList", orderInfo);
//            request.setAttribute("treatmentStatusList", treatmentStatus);
			
			
//			request.setAttribute("emps", emps);
//			request.getRequestDispatcher("./OrderJSP/OrderView.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection, preparedStatement1, resultSet1);
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}