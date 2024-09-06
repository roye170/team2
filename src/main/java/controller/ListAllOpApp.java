package controller;
import com.google.gson.Gson;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.OpApp;

@WebServlet("/ListAllOpApp")
public class ListAllOpApp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListAllOpApp() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/clinic");
            conn = ds.getConnection();
            
            String sql = "SELECT * FROM opapp";
            stmt = conn.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            
            List<OpApp> opApps = new ArrayList<>();
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
                
                opApps.add(opapp);
            }
//            request.setAttribute("opapps", opApps);
          //request.getRequestDispatcher("/webcontent/ListOpApps.jsp").forward(request, response);
          //用gson把list轉成json
			Gson gson = new Gson();
			String json = gson.toJson(opApps);
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}