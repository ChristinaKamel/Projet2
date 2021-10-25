package com.app.facturation.model;

import java.util.HashMap;
import java.util.Map;

public class Facture {

    private Client client;
    //Produit => Quantite
    private Map<Produit, Integer> produits;
    private long timestampCreation;
    private String dateEcheance;
    private String notes;

    public Facture() {}

    public Facture(Client client, Map<Produit, Integer> produits, long timestampCreation, String dateEcheance, String notes) {
        this.client = client;
        this.produits = produits;
        this.timestampCreation = timestampCreation;
        this.dateEcheance = dateEcheance;
        this.notes = notes;
    }

    public Facture(Client client, long timestampCreation, String dateEcheance, String notes) {
        this(client, new HashMap<>(), timestampCreation, dateEcheance, notes);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(Map<Produit, Integer> produits) {
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

    public void ajouterProduit(Produit produit, int quantite) {
        this.produits.put(produit, quantite);
    }

    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
    }

    public float getTotal() {
        float total = 0f;
        for (Map.Entry<Produit, Integer> entry: produits.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        return total;
    }
}
