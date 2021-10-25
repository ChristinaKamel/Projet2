package com.app.facturation.viewModels;

import androidx.lifecycle.ViewModel;

import com.app.facturation.model.Facture;
import com.app.facturation.model.Produit;
import com.app.facturation.repositories.IDataRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AjouterProduitViewModel extends ViewModel {

    @Inject
    IDataRepository dataRepository;
    private final Produit produitAAjouter;

    @Inject
    public AjouterProduitViewModel() {
        produitAAjouter = new Produit("", 0, "");
    }

    public void ajouterProduit() {
        dataRepository.ajouterProduit(produitAAjouter);
    }

    public Produit getProduitAAjouter() {
        return produitAAjouter;
    }
}
