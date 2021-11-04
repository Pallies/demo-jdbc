package entites.factory;

import entites.Fournisseur;

/**
 * A factory for creating Fournisseur objects.
 */
public final class FournisseurFactory extends Fournisseur{

	/**
	 * Instantiates a new fournisseur factory.
	 */
	private FournisseurFactory() {
		super();
	}
	/**
	 * Instantiates a new fournisseur factory.
	 */
	private FournisseurFactory(int id,String nom) {
		super(id,nom);
	}
	
	/**
	 * Builder.
	 *
	 * @return the fournisseur
	 */
	public static Fournisseur builder() {
		return new FournisseurFactory();
	}
	
	/**
	 * Builder.
	 *
	 * @param id the id
	 * @param nom the nom
	 * @return the fournisseur
	 */
	public static Fournisseur builder(int id,String nom) {
		return new FournisseurFactory(id, nom);
	}
}
