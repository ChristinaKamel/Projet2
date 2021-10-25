package com.app.facturation.viewModels;

import androidx.lifecycle.ViewModel;

import com.app.facturation.model.Client;
import com.app.facturation.model.Facture;
import com.app.facturation.repositories.IDataRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AjouterFactureViewModel extends ViewModel {

    @Inject
    IDataRepository dataRepository;
    private final Facture factureAAjouter;

    @Inject
    public AjouterFactureViewModel() {
        factureAAjouter = new Facture(null, -1, "", "");
    }

    public void ajouterFacture() {
        dataRepository.ajouterFacture(factureAAjouter);
    }

    public Facture getFactureAAjouter() {
        return factureAAjouter;
    }
}

