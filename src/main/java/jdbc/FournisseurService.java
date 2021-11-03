package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entites.Fournisseur;

public class FournisseurService implements InterfaceDao<Fournisseur> {

	private static final String TABLE_FOURNISSEUR = "botvtfokmckwxy474co6.FOURNISSEUR";

	@Override
	public List<Fournisseur> getAll() {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		Connection connection = ConnectionDB.getInstance();
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+TABLE_FOURNISSEUR);
			while (rs.next()) {
				Fournisseur fournisseur = new Fournisseur(rs.getInt("ID"), rs.getString("NOM"));
				fournisseurs.add(fournisseur);
			}
		} catch (SQLException e) {
		}

		return fournisseurs;
	}

	@Override
	public Fournisseur find(Fournisseur fournisseur) {
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(fournisseur.getNom()))
				return f;
		}
		return null;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		if (!isExist(fournisseur.getNom())) {
			int id = getAll().size() + 1;
			Connection conn = ConnectionDB.getInstance();
			Statement statement;
			try {
				statement = conn.createStatement();
				statement.executeUpdate("INSERT INTO " + TABLE_FOURNISSEUR + " (ID,NOM) VALUES('" + id + "','"
						+ fournisseur.getNom() + "')");
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int update(Fournisseur fournisseurOld, Fournisseur fournisseurNew) {
		Fournisseur fournisseurRef = find(fournisseurOld);
		if (fournisseurRef != null) {
			Connection conn = ConnectionDB.getInstance();
			Statement statement;
			try {
				statement = conn.createStatement();
				return statement.executeUpdate("UPDATE " + TABLE_FOURNISSEUR + " SET NOM='" + fournisseurNew.getNom()
						+ "' WHERE ID='" + fournisseurRef.getId()+"'");
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int delete(Fournisseur fournisseur) {
		if (isExist(fournisseur.getNom())) {
			fournisseur = find(fournisseur);
			Connection conn = ConnectionDB.getInstance(); Statement statement;
			try {
				statement = conn.createStatement();
				return  statement.executeUpdate("DELETE FROM " + TABLE_FOURNISSEUR + " WHERE ID='" + fournisseur.getId()+"'");
			} catch (SQLException e) {
			}

		}
		return 0;
	}

	private boolean isExist(String nom) {
		List<Fournisseur> fournisseurs = getAll();
		for (Fournisseur f : fournisseurs) {
			if (f.getNom().equals(nom))
				return true;
		}
		return false;
	}

}
