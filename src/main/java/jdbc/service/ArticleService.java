package jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entites.Article;
import entites.factory.ArticleFactory;
import jdbc.connection.ConnectionDB;
import jdbc.dao.IArticleDao;
import jdbc.error.CrudErrorException;

/**
 * The Class ArticleService.
 */
public class ArticleService implements IArticleDao {

	/** The Constant REQUEST_FILE. */
	private static final String REQUEST_FILE;

	/** The Constant ENTITY_FILE. */
	private static final String ENTITY_FILE;

	/** The Constant CONNECTION_FILE. */
	private static final String CONNECTION_FILE;

	/** The Constant TABLE_ARTICLE. */
	private static final String TABLE_ARTICLE;

	/** The Constant INSERT. */
	private static final String INSERT;

	/** The Constant SELECT. */
	private static final String SELECT;

	/** The Constant UPDATE. */
	private static final String UPDATE;

	/** The Constant DELETE. */
	private static final String DELETE;

	static {
		CONNECTION_FILE = "db-cloud";
		ENTITY_FILE = "entities";
		ResourceBundle connect = ResourceBundle.getBundle(CONNECTION_FILE);
		ResourceBundle entity = ResourceBundle.getBundle(ENTITY_FILE);
		TABLE_ARTICLE = connect.getString("MYSQL_ADDON_DB") + "." + entity.getString("TABLE_ARTICLE");

		REQUEST_FILE = "request";
		ResourceBundle request = ResourceBundle.getBundle(REQUEST_FILE);
		SELECT = String.format(request.getString("SELECT_TABLE"), TABLE_ARTICLE);
		INSERT = String.format(request.getString("INSERT_TABLE"), TABLE_ARTICLE);
		UPDATE = String.format(request.getString("UPDATE_TABLE"), TABLE_ARTICLE);
		DELETE = String.format(request.getString("DELETE_TABLE"), TABLE_ARTICLE);

	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws SQLException the SQL exception
	 */
	@Override
	public List<Article> getAll() throws SQLException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection conn = ConnectionDB.getInstance();
				PreparedStatement ps = conn.prepareStatement(SELECT);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Article article = ArticleFactory.builder(rs);
				articles.add(article);
			}
		} catch (Exception e) {
			throw new CrudErrorException(SELECT);
		}
		return articles;
	}

	/**
	 * Find.
	 *
	 * @param article the article
	 * @return the article
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Article find(Article article) throws SQLException {
		Article result = null;
		List<Article> articles = getAll();
		for (Article a : articles) {
			if (a.equals(article))
				result = a;
		}
		return result;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the article
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Article findById(Integer id) throws SQLException {
		Article result = null;
		try (Connection conn = ConnectionDB.getInstance();
				PreparedStatement ps = conn.prepareStatement(SELECT + " WHERE ID=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				result = ArticleFactory.builder(rs);
			}

		} catch (Exception e) {
			throw new CrudErrorException(SELECT);
		}
		return result;
	}

	/**
	 * Insert.
	 *
	 * @param article the article
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean insert(Article article) throws SQLException {
		int result = 0;
		if (!isExist(article)) {
			try (Connection conn = ConnectionDB.getInstance();
					PreparedStatement ps = conn
							.prepareStatement(INSERT + " (REF,DESIGNATION,PRIX,ID_FOU) VALUES (?,?,?,?)")) {
				ps.setString(1, article.getRef());
				ps.setString(2, article.getDesignation());
				ps.setDouble(3, article.getPrix());
				ps.setInt(4, article.getId_fournisseur());
				result = ps.executeUpdate();
			} catch (Exception e) {
				throw new CrudErrorException(INSERT);
			}
		}
		return result > 0;
	}

	/**
	 * Update.
	 *
	 * @param articleOld the article old
	 * @param articleNew the article new
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean update(Article articleOld, Article articleNew) throws SQLException {
		int result = 0;
		Article articleRef = find(articleOld);
		try (Connection conn = ConnectionDB.getInstance();
				PreparedStatement ps = conn
						.prepareStatement(UPDATE + " REF=? DESIGNATION=? PRIX=? ID_FOU=? WHERE ID=?")) {
			ps.setString(1, articleNew.getRef());
			ps.setString(2, articleNew.getDesignation());
			ps.setDouble(3, articleNew.getPrix());
			ps.setInt(4, articleNew.getId_fournisseur());
			ps.setInt(5, articleRef.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			throw new CrudErrorException(UPDATE);
		}

		return result > 0;
	}

	/**
	 * Delete.
	 *
	 * @param article the article
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean delete(Article article) throws SQLException {
		int result = 0;

		try (Connection conn = ConnectionDB.getInstance();
				PreparedStatement preStatement = conn.prepareStatement(DELETE + " WHERE REF=?");) {
			preStatement.setString(1, article.getRef());
			result = preStatement.executeUpdate();
		} catch (Exception e) {
			throw new CrudErrorException(DELETE);
		}
		return result > 0;
	}

	/**
	 * Checks if is exist.
	 *
	 * @param article the article
	 * @return true, if is exist
	 * @throws SQLException the SQL exception
	 */
	private boolean isExist(Article article) throws SQLException {
		List<Article> articles = getAll();
		for (Article a : articles) {
			if (a.equals(article))
				return true;
		}
		return false;
	}
}
