package jdbc;

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

/**
 * The Class FournisseurService.
 * CRUD with Statement and without Autocloseable
 */
public class FournisseurService implements InterfaceDao<Fournisseur> {

	/** The Constant TABLE_FOURNISSEUR. */
	private static final String TABLE_FOURNISSEUR;
	
	/** The Constant PROPERTIES_FILE. */
	private static final String PROPERTIES_FILE;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FournisseurService.class);

	static {
		/** The Constant initialized */
		PROPERTIES_FILE = "db-cloud";
		ResourceBundle table = ResourceBundle.getBundle(PROPERTIES_FILE);
		TABLE_FOURNISSEUR = table.getString("MYSQL_ADDON_DB") + "." + table.getString("MYSQL_TABLE_FOURNISSEUR");
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Fournisseur> getAll() {
		LOGGER.trace("getALL");
		List<Fournisseur> fournisseurs = new ArrayList<>();
		Connection connection = ConnectionDB.getInstance();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_FOURNISSEUR);
			statement.close();
			while (rs.next()) {
				Fournisseur fournisseur = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
				fournisseurs.add(fournisseur);
			}
		} catch (SQLException e) {
			autoCloseable(statement);
		}

		return fournisseurs;
	}

	/**
	 * Find.
	 *
	 * @param fournisseur the fournisseur
	 * @return the fournisseur
	 */
	@Override
	public Fournisseur find(Fournisseur fournisseur) {
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(fournisseur.getNom()))
				return f;
		}
		return null;
	}

	/**
	 * Insert.
	 *
	 * @param fournisseur the fournisseur
	 */
	@Override
	public void insert(Fournisseur fournisseur) {
		if (!isExist(fournisseur.getNom())) {
			int id = getAll().size() + 1;
			Connection conn = ConnectionDB.getInstance();
			Statement statement = null;
			try {
				statement = conn.createStatement();
				statement.executeUpdate("INSERT INTO " + TABLE_FOURNISSEUR + " (ID,NOM) VALUES('" + id + "','"
						+ fournisseur.getNom() + "')");
				statement.close();
			} catch (SQLException e) {
				autoCloseable(statement);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param fournisseurOld the fournisseur old
	 * @param fournisseurNew the fournisseur new
	 * @return the int
	 */
	@Override
	public int update(Fournisseur fournisseurOld, Fournisseur fournisseurNew) {
		Fournisseur fournisseurRef = find(fournisseurOld);
		if (fournisseurRef != null) {
			Connection conn = ConnectionDB.getInstance();
			Statement statement = null;
			try {
				statement = conn.createStatement();
				int result= statement.executeUpdate("UPDATE " + TABLE_FOURNISSEUR + " SET NOM='" + fournisseurNew.getNom()
						+ "' WHERE ID='" + fournisseurRef.getId() + "'");
				statement.close();
				return result;
			} catch (SQLException e) {
				autoCloseable(statement);
			}
		}
		return 0;
	}

	/**
	 * Delete.
	 *
	 * @param fournisseur the fournisseur
	 * @return the int
	 */
	@Override
	public int delete(Fournisseur fournisseur) {
		if (isExist(fournisseur.getNom())) {
			fournisseur = find(fournisseur);
			Connection conn = ConnectionDB.getInstance();
			Statement statement = null;
			try {
				statement = conn.createStatement();
				int result = statement
						.executeUpdate("DELETE FROM " + TABLE_FOURNISSEUR + " WHERE ID='" + fournisseur.getId() + "'");
				statement.close();
			} catch (SQLException e) {
				 autoCloseable(statement);
			}

		}
		return 0;
	}

	/**
	 * Checks if is exist.
	 *
	 * @param nom the nom
	 * @return true, if is exist
	 */
	private boolean isExist(String nom) {
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(nom))
				return true;
		}
		return false;
	}
	private void autoCloseable(Statement statement) {
		try {
			statement.close();
		} catch (Exception e) {
		}
	}

}
