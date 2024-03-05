package PhamThiKimNgan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtils {
	  public static Connection getMSSQLConnection() throws SQLException, ClassNotFoundException {
	        String hostName = "localhost";
	        String sqlInstanceName = "SQL2019";
	        String dbName = "PhamThiKimNgan_2110900033_Dbs";
	        String userName = "sa";
	        String password = "123456";
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433;encrypt=true;trustServerCertificate=true;instance=" + sqlInstanceName + ";databaseName="+ dbName;
	        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	        return conn;
	   
	    }
}
