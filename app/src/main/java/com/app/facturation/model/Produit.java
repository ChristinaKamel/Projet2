package com.app.facturation.model;

import java.io.Serializable;

public class Produit implements Serializable {

    private String nomProduit;
    private int prixUnitaire;
    private String descriptionProduit;

    public Produit() {
    }

    public Produit(String nomProduit, int prixUnitaire, String descriptionProduit) {
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.descriptionProduit = descriptionProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

}
