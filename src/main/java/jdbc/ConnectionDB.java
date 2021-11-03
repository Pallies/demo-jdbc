package jdbc;

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
	 */
	private ConnectionDB() {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
		} catch (SQLException e) {
		}

	}

	/**
	 * Gets the single instance of ConnectionDB.
	 *
	 * @return single instance of ConnectionDB
	 */
	public static Connection getInstance() {
		if (connection == null)
			new ConnectionDB();
		return connection;
	}

}
