import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/compta?useSSL=false";
	private static final String DB_LOGIN = "root";
	private static final String DB_PWD = "";

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
