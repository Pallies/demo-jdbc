package jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entites.Fournisseur;
import entites.factory.FournisseurFactory;
import jdbc.dao.IFournisseurDao;
import jdbc.error.CrudErrorException;

/**
 * The Class FournisseurService.
 * CRUD with PreparedStatement and with Autocloseable
 * connection to each request
 * creation of formatted query start, symbolized by constants
 * INSERT, SELECT_ALL, UPDATE, DELETE
 */
public class FournisseurService implements IFournisseurDao {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FournisseurService.class);

	/** The Constant REFERENCE_FILE. */
	private static final String CONNECTION_FILE;
	
	/** The Constant ENTITY_FILE. */
	private static final String ENTITY_FILE;

	/** The Constant REQUEST_FILE. */
	private static final String REQUEST_FILE;

	/** The Constant TABLE_FOURNISSEUR. */
	private static final String TABLE_FOURNISSEUR;

	/** The Constant URL. */
	private static final String URL;

	/** The Constant LOGIN. */
	private static final String LOGIN;

	/** The Constant PWD. */
	private static final String PWD;

	/** The Constant INSERT. */
	private static final String INSERT;

	/** The Constant SELECT_ALL. */
	private static final String SELECT_ALL;

	/** The Constant UPDATE. */
	private static final String UPDATE;

	/** The Constant DELETE. */
	private static final String DELETE;

	static {
		/** The Constant initialized */
		CONNECTION_FILE = "db-cloud";
		ENTITY_FILE="entities";
		
		ResourceBundle connect = ResourceBundle.getBundle(CONNECTION_FILE);
		ResourceBundle entity = ResourceBundle.getBundle(ENTITY_FILE);
		URL = connect.getString("MYSQL_ADDON_URL");
		LOGIN = connect.getString("MYSQL_ADDON_USER");
		PWD = connect.getString("MYSQL_ADDON_PASSWORD");
		TABLE_FOURNISSEUR = connect.getString("MYSQL_ADDON_DB") + "." + entity.getString("TABLE_FOURNISSEUR");

		REQUEST_FILE = "request";
		ResourceBundle request = ResourceBundle.getBundle(REQUEST_FILE);
		INSERT = String.format(request.getString("INSERT_TABLE"), TABLE_FOURNISSEUR);
		SELECT_ALL = String.format(request.getString("SELECT_TABLE"), TABLE_FOURNISSEUR);
		UPDATE = String.format(request.getString("UPDATE_TABLE"), TABLE_FOURNISSEUR);
		DELETE = String.format(request.getString("DELETE_TABLE"), TABLE_FOURNISSEUR);
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws SQLException the SQL exception
	 */
	@Override
	public List<Fournisseur> getAll() throws SQLException {
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();

		try {
			Connection conn = DriverManager.getConnection(URL, LOGIN, PWD);
			try (PreparedStatement preStatement = conn.prepareStatement(SELECT_ALL);) {
				ResultSet rs = preStatement.executeQuery();
				while (rs.next()) {
					Fournisseur fournisseur = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
					fournisseurs.add(fournisseur);
				}

			}
		} catch (Exception e) {
			throw new CrudErrorException(SELECT_ALL);
		}
		LOGGER.debug(SELECT_ALL);
		// liste vide en cas d'erreur
		return fournisseurs;
	}

	/**
	 * Find.
	 *
	 * @param fournisseur the fournisseur
	 * @return the fournisseur
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Fournisseur find(Fournisseur fournisseur) throws SQLException {
		Fournisseur result = null;
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(fournisseur.getNom()))
				result = f;
		}
		return result;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the fournisseur
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Fournisseur findById(Integer id) throws SQLException {
		Fournisseur result = null;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PWD);
				PreparedStatement preStatement = conn.prepareStatement(SELECT_ALL + " WHERE ID=?");) {
			preStatement.setInt(1, id);
			ResultSet rs = preStatement.executeQuery();
			result = FournisseurFactory.builder(rs.getInt("ID"), rs.getString("NOM"));
			rs.close();
		} catch (SQLException e) {
			throw new CrudErrorException(SELECT_ALL);
		}
		return result;
	}

	/**
	 * Insert.
	 *
	 * @param fournisseur the fournisseur
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean insert(Fournisseur fournisseur) throws SQLException {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PWD);
				PreparedStatement preStatement = conn.prepareStatement(INSERT + " (ID,NOM) VALUES(?,?)")) {
			preStatement.setInt(1, fournisseur.getId());
			preStatement.setString(2, fournisseur.getNom());

			result = preStatement.executeUpdate();
		} catch (Exception e) {
			throw new CrudErrorException(INSERT);
		}
		LOGGER.debug(INSERT);
		LOGGER.trace("Insert execute : {}", (result > 0));
		return result > 0;
	}

	/**
	 * Update.
	 *
	 * @param fournisseurOld the fournisseur old
	 * @param fournisseurNew the fournisseur new
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean update(Fournisseur fournisseurOld, Fournisseur fournisseurNew) throws SQLException {
		int result = 0;
		Fournisseur fournisseurRef = find(fournisseurOld);
		fournisseurRef.setNom(fournisseurNew.getNom());
		if (fournisseurRef != null) {
			try (Connection conn = DriverManager.getConnection(URL, LOGIN, PWD);
					PreparedStatement preStatement = conn.prepareStatement(UPDATE + " NOM=? WHERE ID=?")) {
				preStatement.setString(1, fournisseurRef.getNom());
				preStatement.setInt(2, fournisseurRef.getId());
				
				result = preStatement.executeUpdate();
			} catch (Exception e) {
				throw new CrudErrorException(UPDATE);
			}
		}

		LOGGER.debug(UPDATE);
		LOGGER.trace("Update execute : {}", (result > 0));
		return result > 0;
	}

	/**
	 * Delete.
	 *
	 * @param fournisseur the fournisseur
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean delete(Fournisseur fournisseur) throws SQLException {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(URL, LOGIN, PWD);
				PreparedStatement preStatement = conn.prepareStatement(DELETE + " WHERE NOM=?");) {
			preStatement.setString(1, fournisseur.getNom());
			result = preStatement.executeUpdate();
		} catch (Exception e) {
			throw new CrudErrorException(DELETE);
		}

		LOGGER.debug(DELETE);
		LOGGER.trace("Delete execute : {}", (result > 0));
		return result > 0;
	}

}
