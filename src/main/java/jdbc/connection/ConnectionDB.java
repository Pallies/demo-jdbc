package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * The Class ConnectionDB.
 */
public class ConnectionDB {



	/** The Constant REF_PROPERTIES. */
	private static final String REF_PROPERTIES = "db-cloud";

	/** The Constant DB_URL. */
	private static final String DB_URL;

	/** The Constant DB_LOGIN. */
	private static final String DB_LOGIN;

	/** The Constant DB_PWD. */
	private static final String DB_PWD;

	/** The connection. */
	private static Connection connection = null;

	static {
		ResourceBundle properties = ResourceBundle.getBundle(REF_PROPERTIES);
		DB_URL = properties.getString("MYSQL_ADDON_URL");
		DB_LOGIN = properties.getString("MYSQL_ADDON_USER");
		DB_PWD = properties.getString("MYSQL_ADDON_PASSWORD");
	}

	/**
	 * Instantiates a new connection DB.
	 *
	 * @throws SQLException the SQL exception
	 */
	private ConnectionDB() throws SQLException {
			connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
	}

	/**
	 * Gets the single instance of ConnectionDB.
	 *
	 * @return single instance of ConnectionDB
	 * @throws SQLException the SQL exception
	 */
	public static Connection getInstance() throws SQLException {
		if (connection == null|| connection.isClosed())
			new ConnectionDB();
		return connection;
	}
	
	/**
	 * Connection is valid.
	 *
	 * @return true, if successful
	 */
	public static boolean connectionIsValid() {
		boolean result = false;
		try {
			result = getInstance().isValid(3000);
		} catch (SQLException e) {
		}
		return result;
	}
}
