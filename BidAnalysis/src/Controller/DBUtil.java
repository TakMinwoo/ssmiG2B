package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:myoracle";

	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, "scott", "tiger");
		return con;
	}

}
