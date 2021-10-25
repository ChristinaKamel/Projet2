package com.app.facturation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.facturation.model.Facture;
import com.app.facturation.repositories.IDataRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FacturesViewModel extends ViewModel{

    @Inject
    public IDataRepository dataRepository;

    @Inject
    public FacturesViewModel() {}

    public LiveData<List<Facture>> getFactures() {
        return dataRepository.getFactures();
    }
}