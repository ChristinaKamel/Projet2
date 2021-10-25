package com.app.facturation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.facturation.databinding.FragmentAjouterProduitBinding;
import com.app.facturation.viewModels.AjouterProduitViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AjouterProduitFragment extends Fragment implements View.OnClickListener {

    private AjouterProduitViewModel viewModel;
    private FragmentAjouterProduitBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentAjouterProduitBinding.inflate(inflater, container, false);
        initDataBinding();
        addListeners();
        return binding.getRoot();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(AjouterProduitViewModel.class);
    }

    private void initDataBinding() {
        binding.setProduit(viewModel.getProduitAAjouter());
    }

    private void addListeners() {
        binding.buttonDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonDone.getId()) {
            viewModel.ajouterProduit();
            Navigation.findNavController(getView()).navigateUp();
        }
    }
}