package com.app.facturation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.facturation.R;
import com.app.facturation.adapters.FactureProduitClickListener;
import com.app.facturation.adapters.SelectionProduitsAdapter;
import com.app.facturation.databinding.FragmentAjouterFactureBinding;
import com.app.facturation.model.Client;
import com.app.facturation.model.Produit;
import com.app.facturation.model.Quantite;
import com.app.facturation.utils.MonPair;
import com.app.facturation.viewModels.AjouterFactureViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AjouterFactureFragment extends Fragment implements View.OnClickListener, FactureProduitClickListener {

    private AjouterFactureViewModel viewModel;
    private FragmentAjouterFactureBinding binding;
    private SelectionProduitsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentAjouterFactureBinding.inflate(inflater, container, false);
        initDataBinding();
        initProduitsRecyclerView();
        addListeners();
        return binding.getRoot();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(AjouterFactureViewModel.class);
    }

    private void initDataBinding() {
        binding.setFacture(viewModel.getFactureAAjouter());
    }

    private void initProduitsRecyclerView() {
        adapter = new SelectionProduitsAdapter(this);
        adapter.setProduits(viewModel.getFactureAAjouter().getProduits());
        binding.recyclerViewProduits.setAdapter(adapter);
        binding.recyclerViewProduits.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void addListeners() {
        binding.buttonDone.setOnClickListener(this);
        binding.editTextClient.setOnClickListener(this);
        binding.imageButtonAjouterProduit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonDone.getId()) {
            viewModel.ajouterFacture();
            Navigation.findNavController(getView()).navigateUp();
        } else if (view.getId() == binding.editTextClient.getId()) {
            requireActivity()
                    .getSupportFragmentManager()
                    .setFragmentResultListener("CLIENT_CHOISI", this, (requestKey, result) -> {
                        viewModel.getFactureAAjouter().setClient((Client) result.getSerializable("CLIENT"));
                    });
            Navigation.findNavController(getView()).navigate(R.id.action_ajouterFactureFragment_to_choisirClientFragment);
        } else if (view.getId() == binding.imageButtonAjouterProduit.getId()) {
            viewModel.getFactureAAjouter().ajouterProduit(new Produit("", 0, ""), new Quantite(0));
            adapter.setProduits(viewModel.getFactureAAjouter().getProduits());
        }
    }

    @Override
    public void onFactureProduitClicked(int index) {
        requireActivity()
                .getSupportFragmentManager()
                .setFragmentResultListener("PRODUIT_CHOISI", this, (requestKey, result) -> {
                    viewModel.getFactureAAjouter().getProduits().set(index, new MonPair<>((Produit) result.getSerializable("PRODUIT"), new Quantite(1)));
                });
        Navigation.findNavController(getView()).navigate(R.id.action_ajouterFactureFragment_to_choisirProduitFragment);
    }
}