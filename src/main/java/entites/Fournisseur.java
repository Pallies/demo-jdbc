package entites;

// TODO: Auto-generated Javadoc
/**
 * The Class Fournisseur.
 */
public class Fournisseur {

	/** The id. */
	private int id;
	
	/** The nom. */
	private String nom;

	public Fournisseur() {
	}
    public Fournisseur(int id,String nom) {
    	setId(id);
    	setNom(nom);
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
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
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
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
