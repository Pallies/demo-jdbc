package entites;

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
}
