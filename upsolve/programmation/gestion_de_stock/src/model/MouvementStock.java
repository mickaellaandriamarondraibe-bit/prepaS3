package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MouvementStock {
    public static final String TYPE_ENTREE = "ENTREE";
    public static final String TYPE_SORTIE = "SORTIE";

    private int id;
    private String type;
    private String methode;
    private Article article;
    private int quantite;
    private int quantiteRestante;
    private BigDecimal prixUnitaire;
    private LocalDate dateMouvement;

    public MouvementStock() {
    }

    public MouvementStock(int id, String type, String methode, Article article,
                          int quantite, int quantiteRestante, BigDecimal prixUnitaire,
                          LocalDate dateMouvement) {
        this.id = id;
        this.type = type;
        this.methode = methode;
        this.article = article;
        this.quantite = quantite;
        this.quantiteRestante = quantiteRestante;
        this.prixUnitaire = prixUnitaire;
        this.dateMouvement = dateMouvement;
    }

    public static MouvementStock creerEntree(Article article, int quantite,
                                             BigDecimal prixAchatUnitaire, LocalDate dateEntree) {
        MouvementStock mouvement = new MouvementStock();
        mouvement.setType(TYPE_ENTREE);
        mouvement.setArticle(article);
        mouvement.setQuantite(quantite);
        mouvement.setQuantiteRestante(quantite);
        mouvement.setPrixUnitaire(prixAchatUnitaire);
        mouvement.setDateMouvement(dateEntree);
        return mouvement;
    }

    public static MouvementStock creerSortie(Article article, String methode, int quantiteSortie,
                                             BigDecimal prixVenteUnitaire, LocalDate dateSortie) {
        MouvementStock mouvement = new MouvementStock();
        mouvement.setType(TYPE_SORTIE);
        mouvement.setMethode(methode);
        mouvement.setArticle(article);
        mouvement.setQuantite(quantiteSortie);
        mouvement.setQuantiteRestante(0);
        mouvement.setPrixUnitaire(prixVenteUnitaire);
        mouvement.setDateMouvement(dateSortie);
        return mouvement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantiteRestante() {
        return quantiteRestante;
    }

    public void setQuantiteRestante(int quantiteRestante) {
        this.quantiteRestante = quantiteRestante;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public LocalDate getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDate dateMouvement) {
        this.dateMouvement = dateMouvement;
    }
}
