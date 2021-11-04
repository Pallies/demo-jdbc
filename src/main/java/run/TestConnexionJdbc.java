package run;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entites.Fournisseur;
import jdbc.FournisseurServiceOld;
import jdbc.InterfaceDao;

public class TestConnexionJdbc {

	private static final String NAME_FOURNISSEUR = "La Maison de la Peinture";
	private static final String NAME_FOURNISSEUR_RETIF = "La Maison des Peintures";
	private static final Logger LOGGER = LoggerFactory.getLogger(TestConnexionJdbc.class);
	public static void main(String[] args) {

		
		InterfaceDao<Fournisseur> fournisseurService = new FournisseurServiceOld();
		
//		insertion du fournisseur
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom(NAME_FOURNISSEUR);
		fournisseurService.insert(fournisseur);
		LOGGER.trace("insertion du : "+fournisseur.getClass().getSimpleName()+" avec comme valeur : "+fournisseur.getNom());
		
//		correction du nom du fournisseur dans la base de donnée
		Fournisseur fournisseur2 = new Fournisseur();
		fournisseur2.setNom(NAME_FOURNISSEUR_RETIF);
		int nb = fournisseurService.update(fournisseur, fournisseur2);
		LOGGER.trace("nombre d'élément mis à jour : "+nb);
//		suppression du fournisseur modifié
		nb = fournisseurService.delete(fournisseur2);
		LOGGER.trace("nombre d'élément supprimé : "+nb);
//		affichage de la liste		
		List<Fournisseur> fournisseurs = fournisseurService.getAll();
		for (Fournisseur f : fournisseurs) {
			System.out.println("fournisseur "+f.getId()+" nom :"+f.getNom());
		}
	}

}
