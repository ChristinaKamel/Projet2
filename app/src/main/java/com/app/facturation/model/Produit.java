package com.app.facturation.model;

public class Produit {

    private String nomProduit;
    private float prixUnitaire;
    private String descriptionProduit;

    public Produit() {}

    public Produit(String nomProduit, float prixUnitaire, String descriptionProduit) {
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

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

}
