package jdbc.service.factory;

import java.util.ResourceBundle;

import jdbc.dao.IArticleDao;
import jdbc.service.ArticleService;

public final class ArticleServiceFactory {

	private static final String MODE;

	static {
		ResourceBundle props = ResourceBundle.getBundle("project");
		MODE = props.getString("STORE");
	}

	private ArticleServiceFactory() {
	}

	public static IArticleDao getArticleService() {
		IArticleDao articleService = null;

		switch (MODE) {
		case "DEV":
			articleService = new ArticleService();
			break;
		case "TEST":
			break;
		case "PROD":
			break;
		default:
			break;
		}
		return articleService;
	}
}
