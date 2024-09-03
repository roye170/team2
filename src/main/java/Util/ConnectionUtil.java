package Util;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;

public class ConnectionUtil {
	private static DataSource dataSource;
	private static Context context;
    
	static {
        try {
        	context = new InitialContext();
        	dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/clinc");
        } catch (Exception e){
        	
        }
    }
	
	public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
