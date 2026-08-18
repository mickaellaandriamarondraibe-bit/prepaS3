package service;

import dao.ArticleDao;
import model.Article;

import java.sql.SQLException;
import java.util.List;

public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = new ArticleDao();
    }

    public void ajouterArticle(String nom) throws SQLException {
        String nomNettoye = validerNom(nom);

        if (articleDao.nomExiste(nomNettoye)) {
            throw new IllegalArgumentException("Cet article existe deja.");
        }

        articleDao.ajouter(new Article(nomNettoye));
    }

    public List<Article> listerArticles() throws SQLException {
        return articleDao.lister();
    }

    public void modifierArticle(int id, String nom) throws SQLException {
        String nomNettoye = validerNom(nom);

        if (id <= 0) {
            throw new IllegalArgumentException("Veuillez selectionner un article.");
        }

        if (articleDao.nomExistePourUnAutreArticle(id, nomNettoye)) {
            throw new IllegalArgumentException("Un autre article porte deja ce nom.");
        }

        articleDao.modifier(new Article(id, nomNettoye, null));
    }

    public void supprimerArticle(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Veuillez selectionner un article.");
        }

        articleDao.supprimer(id);
    }

    private String validerNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'article est obligatoire.");
        }

        String nomNettoye = nom.trim();

        if (nomNettoye.length() > 150) {
            throw new IllegalArgumentException("Le nom de l'article ne doit pas depasser 150 caracteres.");
        }

        return nomNettoye;
    }
}
