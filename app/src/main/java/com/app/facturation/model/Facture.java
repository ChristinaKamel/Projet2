package com.app.facturation.model;

import com.app.facturation.utils.MonPair;

import java.util.ArrayList;
import java.util.List;

public class Facture {

    private String idFacture;
    private Client client;
    //Produit => Quantite
    private List<MonPair<Produit, Quantite>> produits;
    private long timestampCreation;
    private String dateEcheance;
    private String notes;

    public Facture() {
    }

    public Facture(String idFacture, Client client, List<MonPair<Produit, Quantite>> produits, long timestampCreation, String dateEcheance, String notes) {
        this.idFacture = idFacture;
        this.client = client;
        this.produits = produits;
        this.timestampCreation = timestampCreation;
        this.dateEcheance = dateEcheance;
        this.notes = notes;
    }

    public Facture(Client client, List<MonPair<Produit, Quantite>> produits, long timestampCreation, String dateEcheance, String notes) {
        this.client = client;
        this.produits = produits;
        this.timestampCreation = timestampCreation;
        this.dateEcheance = dateEcheance;
        this.notes = notes;
        genererIdFacture();
    }

    public Facture(Client client, long timestampCreation, String dateEcheance, String notes) {
        this.client = client;
        this.produits = new ArrayList<>();
        this.timestampCreation = timestampCreation;
        this.dateEcheance = dateEcheance;
        this.notes = notes;
        produits.add(new MonPair<>(new Produit("", 0, ""), new Quantite(0)));
        genererIdFacture();
    }

    public Facture(String idFacture, Client client, long timestampCreation, String dateEcheance, String notes) {
        this.idFacture = idFacture;
        this.client = client;
        this.produits = new ArrayList<>();
        this.timestampCreation = timestampCreation;
        this.dateEcheance = dateEcheance;
        this.notes = notes;
        produits.add(new MonPair<>(new Produit("", 0, ""), new Quantite(0)));
    }

    private void genererIdFacture() {
        for (int i = 0; i < 16; i++) {
            idFacture += Math.floor(Math.random() * 9);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<MonPair<Produit, Quantite>> getProduits() {
        return produits;
    }

    public void setProduits(List<MonPair<Produit, Quantite>> produits) {
        this.produits = produits;
    }

    public long getTimestampCreation() {
        return timestampCreation;
    }

    public void setTimestampCreation(long timestampCreation) {
        this.timestampCreation = timestampCreation;
    }

    public String getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void ajouterProduit(Produit produit, Quantite quantite) {
        this.produits.add(new MonPair<>(produit, quantite));
    }

    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
    }

    public float getTotal() {
        float total = 0f;
        for (MonPair<Produit, Quantite> pair : produits) {
            total += pair.getFirst().getPrixUnitaire() * pair.getSecond().getQuantite();
        }
        return total;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdFacture() {
        return idFacture;
    }
}
