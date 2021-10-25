package com.app.facturation.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.facturation.R;
import com.app.facturation.databinding.FragmentAjouterClientBinding;
import com.app.facturation.viewModels.AjouterClientViewModel;
import com.app.facturation.viewModels.AjouterProduitViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AjouterClientFragment extends Fragment implements View.OnClickListener {

    private AjouterClientViewModel viewModel;
    private FragmentAjouterClientBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentAjouterClientBinding.inflate(inflater, container, false);
        initDataBinding();
        addListeners();
        return binding.getRoot();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(AjouterClientViewModel.class);
    }

    private void initDataBinding() {
        binding.setClient(viewModel.getClientAAjouter());
    }

    private void addListeners() {
        binding.buttonDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonDone.getId()) {
            viewModel.ajouterClient();
            Navigation.findNavController(getView()).navigateUp();
        }
    }
}