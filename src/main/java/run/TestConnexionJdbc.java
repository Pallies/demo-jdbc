package run;

import java.sql.SQLException;
import java.util.List;

import entites.Fournisseur;
import jdbc.dao.IFournisseurDao;
import jdbc.service.factory.FournisseurServiceFactory;

/**
 * The Class TestConnexionJdbc.
 */
public class TestConnexionJdbc {

	/** The Constant NAME_FOURNISSEUR. */
	private static final String NAME_FOURNISSEUR = "La Maison de la Peinture";
	
	/** The Constant NAME_FOURNISSEUR_RETIF. */
	private static final String NAME_FOURNISSEUR_RETIF = "La Maison des Peintures";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {

		IFournisseurDao fournisseurService = FournisseurServiceFactory.getFournisseurService();

//		insertion du fournisseur
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom(NAME_FOURNISSEUR);
		fournisseurService.insert(fournisseur);

//		correction du nom du fournisseur dans la base de donnée
		Fournisseur fournisseur2 = new Fournisseur();
		fournisseur2.setNom(NAME_FOURNISSEUR_RETIF);
		fournisseurService.update(fournisseur, fournisseur2);
//		Fournisseur fournisseur=new Fournisseur();
//		fournisseur.setNom(NAME_FOURNISSEUR);
//		suppression du fournisseur modifié
		fournisseurService.delete(fournisseur2);
		
//		affichage de la liste		
		List<Fournisseur> fournisseurs = fournisseurService.getAll();
		for (Fournisseur f : fournisseurs) {
			System.out.println("fournisseur " + f.getId() + " nom : " + f.getNom());
		}
		
	}

}
