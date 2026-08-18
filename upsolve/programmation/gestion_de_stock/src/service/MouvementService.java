package service;

import dao.DatabaseConnection;
import dao.MouvementDao;
import model.Article;
import model.MethodeSortie;
import model.MouvementStock;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MouvementService {
    private final MouvementDao mouvementDao;

    public MouvementService() {
        this.mouvementDao = new MouvementDao();
    }

    public void enregistrerEntree(Article article, String quantiteTexte,
                                  String prixAchatTexte, LocalDate dateEntree) throws SQLException {
        validerArticle(article);
        int quantite = convertirQuantite(quantiteTexte);
        BigDecimal prixAchat = convertirPrix(prixAchatTexte, "prix d'achat");
        validerDate(dateEntree, "La date d'entree est obligatoire.");

        mouvementDao.ajouterEntree(MouvementStock.creerEntree(article, quantite, prixAchat, dateEntree));
    }

    public int enregistrerSortie(Article article, String quantiteTexte, String prixVenteTexte,
                                 LocalDate dateSortie, MethodeSortie methodeSortie) throws SQLException {
        validerArticle(article);
        int quantiteDemandee = convertirQuantite(quantiteTexte);
        BigDecimal prixVente = convertirPrix(prixVenteTexte, "prix de vente");
        validerDate(dateSortie, "La date de sortie est obligatoire.");

        if (methodeSortie == null) {
            throw new IllegalArgumentException("Veuillez selectionner une methode de sortie.");
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);

                boolean lifo = methodeSortie == MethodeSortie.LIFO;
                List<MouvementStock> entreesDisponibles = mouvementDao.listerEntreesDisponibles(
                        connection,
                        article.getId(),
                        lifo
                );

                int resteASortir = quantiteDemandee;
                int quantiteSortie = 0;

                for (MouvementStock entree : entreesDisponibles) {
                    if (resteASortir == 0) {
                        break;
                    }

                    int quantitePrelevee = Math.min(resteASortir, entree.getQuantiteRestante());
                    int nouveauReste = entree.getQuantiteRestante() - quantitePrelevee;
                    mouvementDao.modifierQuantiteRestante(connection, entree.getId(), nouveauReste);

                    resteASortir -= quantitePrelevee;
                    quantiteSortie += quantitePrelevee;
                }

                if (quantiteSortie == 0) {
                    throw new IllegalArgumentException("Stock vide pour cet article. Aucune sortie n'a ete enregistree.");
                }

                mouvementDao.ajouterSortie(connection, MouvementStock.creerSortie(
                        article,
                        methodeSortie.name(),
                        quantiteSortie,
                        prixVente,
                        dateSortie
                ));

                connection.commit();
                return quantiteSortie;
            } catch (SQLException | RuntimeException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public List<MouvementStock> listerMouvements() throws SQLException {
        return mouvementDao.listerTous();
    }

    public List<MouvementStock> listerMouvementsParArticle(int articleId) throws SQLException {
        if (articleId <= 0) {
            throw new IllegalArgumentException("Veuillez selectionner un article.");
        }

        return mouvementDao.listerParArticle(articleId);
    }

    private void validerArticle(Article article) {
        if (article == null || article.getId() <= 0) {
            throw new IllegalArgumentException("Veuillez selectionner un article.");
        }
    }

    private void validerDate(LocalDate date, String message) {
        if (date == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private int convertirQuantite(String quantiteTexte) {
        try {
            int quantite = Integer.parseInt(quantiteTexte.trim());

            if (quantite <= 0) {
                throw new IllegalArgumentException("La quantite doit etre superieure a 0.");
            }

            return quantite;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La quantite doit etre un nombre entier.");
        }
    }

    private BigDecimal convertirPrix(String prixTexte, String libelle) {
        try {
            BigDecimal prix = new BigDecimal(prixTexte.trim().replace(',', '.'));

            if (prix.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Le " + libelle + " ne doit pas etre negatif.");
            }

            return prix;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le " + libelle + " doit etre un nombre valide.");
        }
    }
}
