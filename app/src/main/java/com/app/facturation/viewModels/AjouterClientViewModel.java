package com.app.facturation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.facturation.model.Client;
import com.app.facturation.repositories.IDataRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AjouterClientViewModel extends ViewModel {

    @Inject
    IDataRepository dataRepository;
    private final Client clientAAjouter;

    @Inject
    public AjouterClientViewModel() {
        clientAAjouter = new Client("", "", "", "");
    }

    public void ajouterClient() {
        dataRepository.ajouterClient(clientAAjouter);
    }

    public Client getClientAAjouter() {
        return clientAAjouter;
    }

}
