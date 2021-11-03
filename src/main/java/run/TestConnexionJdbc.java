package run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
	private static ResourceBundle properties = ResourceBundle.getBundle("database");

	private static final String DB_URL = properties.getString("MYSQL_ADDON_HOST");
	private static final String DB_LOGIN = properties.getString("MYSQL_ADDON_USER");
	private static final String DB_PWD = properties.getString("MYSQL_ADDON_PASSWORD");

	public static void main(String[] args) throws SQLException {


		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
			System.out.println(connection);
			connection.close();
		} catch (SQLException e) {
			connection.close();
		}

	}

}
