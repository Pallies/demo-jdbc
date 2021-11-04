package entites.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import entites.Article;

/**
 * A factory for creating Article objects.
 */
public final class ArticleFactory extends Article {

	/**
	 * Instantiates a new article factory.
	 */
	private ArticleFactory() {
		super();
	}

	/**
	 * Instantiates a new article factory.
	 *
	 * @param ref         the ref
	 * @param designation the designation
	 */
	private ArticleFactory(String ref, String designation) {
		super(ref, designation);
	}

	/**
	 * Instantiates a new article factory.
	 *
	 * @param ref         the ref
	 * @param designation the designation
	 * @param prix        the prix
	 */
	private ArticleFactory(String ref, String designation, double prix) {
		super(ref, designation, prix);
	}

	/**
	 * Instantiates a new article factory.
	 *
	 * @param id          the id
	 * @param ref         the ref
	 * @param designation the designation
	 * @param prix        the prix
	 */
	private ArticleFactory(int id, String ref, String designation, double prix) {
		super(id, ref, designation, prix);
	}

	/**
	 * Instantiates a new article factory.
	 *
	 * @param id the id
	 * @param ref the ref
	 * @param designation the designation
	 * @param prix the prix
	 * @param idFournisseur the id fournisseur
	 */
	private ArticleFactory(int id, String ref, String designation, double prix, int idFournisseur) {
		super(id, ref, designation, prix, idFournisseur);
	}

	/**
	 * Builder.
	 *
	 * @return the article
	 */
	public static Article builder() {
		return new ArticleFactory();
	}

	/**
	 * Builder.
	 *
	 * @param ref         the ref
	 * @param designation the designation
	 * @return the article
	 */
	public static Article builder(String ref, String designation) {
		return new ArticleFactory(ref, designation);
	}

	/**
	 * Builder.
	 *
	 * @param ref         the ref
	 * @param designation the designation
	 * @param prix        the prix
	 * @return the article
	 */
	public static Article builder(String ref, String designation, double prix) {
		return new ArticleFactory(ref, designation, prix);
	}

	/**
	 * Builder.
	 *
	 * @param id          the id
	 * @param ref         the ref
	 * @param designation the designation
	 * @param prix        the prix
	 * @return the article
	 */
	public static Article builder(int id, String ref, String designation, double prix) {
		return new ArticleFactory(id, ref, designation, prix);
	}

	/**
	 * Builder.
	 *
	 * @param id the id
	 * @param ref the ref
	 * @param designation the designation
	 * @param prix the prix
	 * @param idFournisseur the id fournisseur
	 * @return the article
	 */
	public static Article builder(int id, String ref, String designation, double prix, int idFournisseur) {
		return new ArticleFactory(id, ref, designation, prix, idFournisseur);
	}
	
	public static Article builder(ResultSet rs) throws SQLException {
		return new ArticleFactory(rs.getInt("ID"), rs.getString("REF"),
				rs.getString("DESIGNATION"), rs.getDouble("PRIX"),rs.getInt("ID_FOU"));
	}
}
