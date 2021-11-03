package run;

import java.sql.SQLException;
import java.util.List;

import entites.Fournisseur;
import jdbc.FournisseurService;
import jdbc.InterfaceDao;

public class TestConnexionJdbc {

	private static final String NAME_FOURNISSEUR = "La Maison de la Peinture";
	private static final String NAME_FOURNISSEUR_RETIF = "La Maison des Peintures";

	public static void main(String[] args) throws SQLException {

		// TODO à refaire avec logback problème de dependences
		
		InterfaceDao<Fournisseur> fournisseurService = new FournisseurService();
//		insertion du fournisseur
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom(NAME_FOURNISSEUR);
		fournisseurService.insert(fournisseur);

//		correction du nom du fournisseur dans la base de donnée
		Fournisseur fournisseur2 = new Fournisseur();
		fournisseur2.setNom(NAME_FOURNISSEUR_RETIF);
		int nb = fournisseurService.update(fournisseur, fournisseur2);
		System.out.println("nombre update "+nb);
		
//		suppression du fournisseur modifié
		nb = fournisseurService.delete(fournisseur2);
		System.out.println("nombre delete fournisseur1 "+nb);

//		affichage de la liste		
		List<Fournisseur> fournisseurs = fournisseurService.getAll();
		for (Fournisseur f : fournisseurs) {
			System.out.println("fournisseur "+f.getId()+" nom :"+f.getNom());
		}
	}

}
