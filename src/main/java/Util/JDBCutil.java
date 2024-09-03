package Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCutil {
	public static void main(String[] args) {
		getConnection();
	}

	// 取得連線
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Properties properties = new Properties();
//			InputStream propertiespath = JDBCutil.class.getClassLoader().getResourceAsStream("jdbcLogin.properties");
//			properties.load(propertiespath);

//			String user = properties.getProperty("user");
//			String password = properties.getProperty("password");
//			String url = properties.getProperty("url");

			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=order;encrypt=false",
					"kai", "1234");
			boolean status = !connection.isClosed();
			if (status == true) {
				System.out.println("連線成功 " + "使用者:" + "kai");
			} else {
				System.out.println("連線失敗");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("連線失敗");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("連線失敗");
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeResource(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet1,ResultSet resultSet2) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet1 != null) {
				resultSet1.close();
			}
			if (resultSet2 != null) {
				resultSet2.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResource(Connection connection, PreparedStatement preparedStatement1,PreparedStatement preparedStatement2, ResultSet resultSet1,ResultSet resultSet2) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement1 != null) {
				preparedStatement1.close();
			}
			if (preparedStatement2 != null) {
				preparedStatement2.close();
			}
			if (resultSet1 != null) {
				resultSet1.close();
			}
			if (resultSet2 != null) {
				resultSet2.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
