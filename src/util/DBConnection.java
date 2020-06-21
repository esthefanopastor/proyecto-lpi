package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
	private static ResourceBundle rBundle = ResourceBundle.getBundle("db_connection");

	static {
		try {
			Class.forName(rBundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(rBundle.getString("url"), rBundle.getString("username"),
					rBundle.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
