package com.app.facturation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.facturation.model.Produit;
import com.app.facturation.repositories.IDataRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProduitsViewModel extends ViewModel {

    @Inject
    public IDataRepository dataRepository;

    @Inject
    public ProduitsViewModel() {}

    public LiveData<List<Produit>> getProduits() {
        return dataRepository.getProduits();
    }
}