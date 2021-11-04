package jdbc.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * The Interface IJdbcDao.
 *
 * @param <T> the generic type
 * @param <U> the generic type
 */
public interface IJdbcDao<T,U>{

	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws SQLException the SQL exception
	 */
	public List<T> getAll() throws SQLException;

	/**
	 * Find.
	 *
	 * @param object the object
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	public T find(T object) throws SQLException;
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	public T findById(U id) throws SQLException;

	/**
	 * Insert.
	 *
	 * @param object the object
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean insert(T object) throws SQLException;

	/**
	 * Update.
	 *
	 * @param objectOld the object old
	 * @param objectNew the object new
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean update(T objectOld, T objectNew) throws SQLException;

	/**
	 * Delete.
	 *
	 * @param object the object
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean delete(T object) throws SQLException;

}
