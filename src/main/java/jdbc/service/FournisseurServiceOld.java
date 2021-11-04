package jdbc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entites.Fournisseur;
import jdbc.connection.ConnectionDB;
import jdbc.dao.IFournisseurDao;
import jdbc.error.CrudErrorException;

/**
 * The Class FournisseurService. CRUD with Statement and without Autocloseable
 */
public class FournisseurServiceOld implements IFournisseurDao {

	/** The Constant PROPERTIES_FILE. */
	private static final String PROPERTIES_FILE;
	
	/** The Constant ENTITY_FILE. */
	private static final String ENTITY_FILE;

	/** The Constant TABLE_FOURNISSEUR. */
	private static final String TABLE_FOURNISSEUR;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FournisseurServiceOld.class);

	static {
		/** The Constant initialized */
		PROPERTIES_FILE = "db-cloud";
		ENTITY_FILE="entities";
		
		ResourceBundle props = ResourceBundle.getBundle(PROPERTIES_FILE);
		ResourceBundle entity = ResourceBundle.getBundle(ENTITY_FILE);
		TABLE_FOURNISSEUR = props.getString("MYSQL_ADDON_DB") + "." + entity.getString("TABLE_FOURNISSEUR");
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 * @throws SQLException the SQL exception
	 */
	@Override
	public List<Fournisseur> getAll() throws SQLException {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		Connection connection;
		try {
			connection = ConnectionDB.getInstance();
			try (Statement statement = connection.createStatement();

			) {
				ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_FOURNISSEUR);

				while (rs.next()) {
					Fournisseur fournisseur = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
					fournisseurs.add(fournisseur);
				}
				rs.close();
				connection.close();
			}

		} catch (SQLException e) {
			throw new CrudErrorException("SELECT ");
		}

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
		Fournisseur fournisseur=null;
		Connection connection;
		try {
			connection = ConnectionDB.getInstance();
			try (Statement statement = connection.createStatement();

			) {
				ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_FOURNISSEUR+" WHERE ID="+id);
				fournisseur = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
				
				rs.close();
				connection.close();
			}

		} catch (SQLException e) {
			throw new CrudErrorException("SELECT ");
		}

		return fournisseur;
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
		if (!isExist(fournisseur.getNom())) {
			int id = getAll().size() + 1;
			Connection conn;
			try {
				conn = ConnectionDB.getInstance();
				try (Statement statement = conn.createStatement();) {
					result = statement.executeUpdate("INSERT INTO " + TABLE_FOURNISSEUR + " (ID,NOM) VALUES('" + id + "','"
							+ fournisseur.getNom() + "')");
					LOGGER.trace("Insert execute : {}" ,(result > 0));
				}
			} catch (SQLException e) {
				throw new CrudErrorException("INSERT ");
			}
		}
		return result > 0;
	}

	/**
	 * Update.
	 *
	 * @param fournisseurOld the fournisseur old
	 * @param fournisseurNew the fournisseur new
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean update(Fournisseur fournisseurOld, Fournisseur fournisseurNew) throws SQLException {
		int result = 0;
		Fournisseur fournisseurRef = find(fournisseurOld);
		if (fournisseurRef != null) {
			Connection conn;
			try {
				conn = ConnectionDB.getInstance();
				try (Statement statement = conn.createStatement();) {
					result = statement.executeUpdate("UPDATE " + TABLE_FOURNISSEUR + " SET NOM='" + fournisseurNew.getNom() + "' WHERE ID='"
							+ fournisseurRef.getId() + "'");
					LOGGER.trace("Insert execute : {}" ,(result > 0));
				}
			} catch (SQLException e) {
				throw new CrudErrorException("UPDATE ");
			}
		}
		return result > 0;
	}

	/**
	 * Delete.
	 *
	 * @param fournisseur the fournisseur
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean delete(Fournisseur fournisseur) throws SQLException {
		int result = 0;
		if (isExist(fournisseur.getNom())) {
			fournisseur = find(fournisseur);
			Connection conn;
			try {
				conn = ConnectionDB.getInstance();
				try (Statement statement = conn.createStatement();) {
					result = statement.executeUpdate("DELETE FROM " + TABLE_FOURNISSEUR + " WHERE ID='" + fournisseur.getId() + "'");
					LOGGER.trace("Delete execute : {}" ,(result > 0));
				}
			} catch (SQLException e) {
				throw new CrudErrorException("DELETE");
			}

		}
		return result > 0;
	}

	/**
	 * Checks if is exist.
	 *
	 * @param nom the nom
	 * @return true, if is exist
	 * @throws SQLException the SQL exception
	 */
	private boolean isExist(String nom) throws SQLException {
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(nom))
				return true;
		}
		return false;
	}
}
