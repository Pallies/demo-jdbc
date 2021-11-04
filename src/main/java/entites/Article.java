package entites;

import java.util.Objects;

/**
 * The Class Article.
 */
public class Article {

	/** The id. */
	private int id;

	/** The ref. */
	private String ref;

	/** The designation. */
	private String designation;

	/** The prix. */
	private double prix;

	/** The id fournisseur. */
	private int id_fournisseur;

	/**
	 * Instantiates a new article.
	 */
	public Article() {
	}

	/**
	 * Instantiates a new article.
	 *
	 * @param ref the ref
	 * @param designation the designation
	 */
	public Article(String ref, String designation) {
		setRef(ref);
		setDesignation(designation);
	}

	/**
	 * Instantiates a new article.
	 *
	 * @param ref the ref
	 * @param designation the designation
	 * @param prix the prix
	 */
	public Article(String ref, String designation, double prix) {
		setRef(ref);
		setDesignation(designation);
		setPrix(prix);
	}
	
	/**
	 * Instantiates a new article.
	 *
	 * @param id the id
	 * @param ref the ref
	 * @param designation the designation
	 * @param prix the prix
	 */
	public Article(int id,String ref, String designation, double prix) {
		setId(id);
		setRef(ref);
		setDesignation(designation);
		setPrix(prix);
	}
	
	/**
	 * Instantiates a new article.
	 *
	 * @param id the id
	 * @param ref the ref
	 * @param designation the designation
	 * @param prix the prix
	 * @param idFournisseur the id fournisseur
	 */
	public Article(int id,String ref, String designation, double prix,int idFournisseur) {
		setId(id);
		setRef(ref);
		setDesignation(designation);
		setPrix(prix);
		setId_fournisseur(idFournisseur);
	}
	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the id fournisseur.
	 *
	 * @return the id fournisseur
	 */
	public int getId_fournisseur() {
		return id_fournisseur;
	}

	/**
	 * Gets the prix.
	 *
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Gets the ref.
	 *
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the id fournisseur.
	 *
	 * @param id_fournisseur the new id fournisseur
	 */
	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}

	/**
	 * Sets the prix.
	 *
	 * @param prix the new prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Sets the ref.
	 *
	 * @param ref the new ref
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	@Override
	public int hashCode() {
		return Objects.hash(designation, id, id_fournisseur, prix, ref);
	}

	/**
	 * Equals.
	 * the ids are not compared
	 * if ref unique --> compared only ref 
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Article))
			return false;
		Article other = (Article) obj;
		return Objects.equals(designation, other.designation)
				&& id_fournisseur == other.id_fournisseur
				&& Double.doubleToLongBits(prix) == Double.doubleToLongBits(other.prix)
				&& Objects.equals(ref, other.ref);
	}
}
