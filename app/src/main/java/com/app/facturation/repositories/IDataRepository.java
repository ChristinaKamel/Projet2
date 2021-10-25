package com.app.facturation.repositories;

import androidx.lifecycle.LiveData;

import com.app.facturation.model.Client;
import com.app.facturation.model.Facture;
import com.app.facturation.model.Produit;

import java.util.List;

public interface IDataRepository {
    public LiveData<List<Client>> getClients();
    public void ajouterClient(Client client);
    public LiveData<List<Produit>> getProduits();
    public void ajouterProduit(Produit produit);
    public LiveData<List<Facture>> getFactures();
    public void ajouterFacture(Facture facture);
}
