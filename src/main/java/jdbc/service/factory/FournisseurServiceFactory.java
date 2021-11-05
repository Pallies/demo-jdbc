package jdbc.service.factory;

import java.util.ResourceBundle;

import jdbc.dao.IFournisseurDao;
import jdbc.service.FournisseurService;

public final class FournisseurServiceFactory {

	private static final String MODE;

	static {
		ResourceBundle props = ResourceBundle.getBundle("project");
		MODE = props.getString("STORE");
	}

	private FournisseurServiceFactory() {
	}

	public static IFournisseurDao getFournisseurService() {
		IFournisseurDao fournisseurService = null;

		switch (MODE) {
		case "DEV":
			fournisseurService = new FournisseurService();
			break;
		case "TEST":
			break;
		case "PROD":
			break;
		default:
			break;
		}
		return fournisseurService;
	}
}
