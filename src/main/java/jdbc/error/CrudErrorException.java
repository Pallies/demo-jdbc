package jdbc.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbc.connection.ConnectionDB;

/**
 * The Class CrudErrorException.
 */
public class CrudErrorException extends SQLException {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudErrorException.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4872525895966839706L;

	/**
	 * Instantiates a new insert error exception.
	 *
	 * @param msg the msg
	 */
	public CrudErrorException(String msg) {
		super(buildMessage(msg));
		LOGGER.warn(buildMessage(msg));
	}

	/**
	 * Builds the message.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	private static String buildMessage(String msg) {
		StringBuilder str = new StringBuilder();
		str.append("fail query => ").append(msg).append("\t( connection at DB : ").append(ConnectionDB.connectionIsValid())
				.append(" ).");
		return str.toString();
	}
}
