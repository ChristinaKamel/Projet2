package com.app.facturation.repositories;

import androidx.lifecycle.LiveData;

import com.app.facturation.model.Client;
import com.app.facturation.model.Facture;
import com.app.facturation.model.Produit;
import com.app.facturation.liveData.ClientsLiveData;
import com.app.facturation.liveData.FacturesLiveData;
import com.app.facturation.liveData.ProduitsLiveData;
import com.app.facturation.utils.FirebaseUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataReprositoryFirestore implements IDataRepository {

    public static final String COLLECTION_CLIENTS = "Clients";
    public static final String COLLECTION_PRODUITS = "Produits";
    public static final String COLLECTION_FACTURES = "Factures";

    private final ClientsLiveData clientsLiveData;
    private final ProduitsLiveData produitsLiveData;
    private final FacturesLiveData facturesLiveData;

    @Inject
    public DataReprositoryFirestore() {
        clientsLiveData = new ClientsLiveData();
        produitsLiveData = new ProduitsLiveData();
        facturesLiveData = new FacturesLiveData();
    }

    @Override
    public LiveData<List<Client>> getClients() {
        return clientsLiveData;
    }

    @Override
    public void ajouterClient(Client client) {
        FirebaseUtils
                .getInstanceFirestore()
                .collection(COLLECTION_CLIENTS)
                .add(client);
    }

    @Override
    public LiveData<List<Produit>> getProduits() {
        return produitsLiveData;
    }

    @Override
    public void ajouterProduit(Produit produit) {
        FirebaseUtils
                .getInstanceFirestore()
                .collection(COLLECTION_PRODUITS)
                .add(produit);
    }

    @Override
    public LiveData<List<Facture>> getFactures() {
        return facturesLiveData;
    }

    @Override
    public void ajouterFacture(Facture facture) {
        FirebaseUtils
                .getInstanceFirestore()
                .collection(COLLECTION_FACTURES)
                .add(facture);
    }
}
