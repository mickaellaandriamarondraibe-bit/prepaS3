package model;

import java.time.LocalDateTime;

public class Article {
    private int id;
    private String nom;
    private LocalDateTime dateCreation;

    public Article() {
    }

    public Article(String nom) {
        this.nom = nom;
    }

    public Article(int id, String nom, LocalDateTime dateCreation) {
        this.id = id;
        this.nom = nom;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return nom;
    }
}
