package entites;

/**
 * The Class Fournisseur.
 */
public class Fournisseur {

	/** The id. */
	private int id;
	
	/** The nom. */
	private String nom;

	/**
	 * Instantiates a new fournisseur.
	 */
	public Fournisseur() {
	}
    
    /**
     * Instantiates a new fournisseur.
     *
     * @param id the id
     * @param nom the nom
     */
    public Fournisseur(int id,String nom) {
    	setId(id);
    	setNom(nom);
    }
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Fournisseur id : " + id + ", nom : " + nom ;
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
