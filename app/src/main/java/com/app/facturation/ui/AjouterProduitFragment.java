package com.app.facturation.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.facturation.R;
import com.app.facturation.databinding.FragmentAjouterProduitBinding;
import com.app.facturation.databinding.FragmentFacturesBinding;
import com.app.facturation.viewModels.AjouterProduitViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AjouterProduitFragment extends Fragment {

    private AjouterProduitViewModel viewModel;
    private FragmentAjouterProduitBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentAjouterProduitBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(AjouterProduitViewModel.class);
    }
}