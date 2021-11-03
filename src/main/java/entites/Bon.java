package entites;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Bon.
 */
public class Bon {

	/** The id. */
	private int id;
	
	/** The numero. */
	private int numero;
	
	/** The date. */
	private Date date;
	
	/** The delai. */
	private int delai;
	
	/** The id fournisseur. */
	private int id_fournisseur;
	
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Gets the delai.
	 *
	 * @return the delai
	 */
	public int getDelai() {
		return delai;
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
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Sets the delai.
	 *
	 * @param delai the new delai
	 */
	public void setDelai(int delai) {
		this.delai = delai;
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
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
