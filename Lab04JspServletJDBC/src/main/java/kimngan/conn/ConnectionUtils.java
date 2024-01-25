package kimngan.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getMSSQLConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String sqlInstanceName = "SQL2019";
		String dbName = "Lab04JspServletJDBC";
		String userName = "sa";
		String password = "123456$";
		
		String connectionURL = "jdbc:sqlserver://localhost:1433" + hostName + ":1000;instance=" + sqlInstanceName + ";databaseName=" + dbName;
		Connection conn= DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			
		}
	}
	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
			
		}
	}
}