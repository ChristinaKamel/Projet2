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
import com.app.facturation.adapters.ProduitClickListener;
import com.app.facturation.adapters.ProduitsAdapter;
import com.app.facturation.databinding.FragmentChoisirProduitBinding;
import com.app.facturation.model.Produit;
import com.app.facturation.viewModels.ProduitsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChoisirProduitFragment extends Fragment implements View.OnClickListener, ProduitClickListener {

    public ProduitsViewModel produitsViewModel;
    private FragmentChoisirProduitBinding binding;

    public ChoisirProduitFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentChoisirProduitBinding.inflate(inflater, container, false);
        initRecyclerView();
        addClickListeners();
        return binding.getRoot();
    }

    private void initViewModels() {
        produitsViewModel = new ViewModelProvider(this).get(ProduitsViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        ProduitsAdapter adapter = new ProduitsAdapter(this);
        produitsViewModel.getProduits().observe(getViewLifecycleOwner(), produits -> adapter.setProduits(produits));
        binding.recyclerViewProduits.setAdapter(adapter);
        binding.recyclerViewProduits.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonAdd.getId()) {
            Navigation.findNavController(getView()).navigate(R.id.action_choisirProduitFragment_to_ajouterProduitFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onProduitClicked(Produit produit) {
        Bundle resultBundle = new Bundle();
        resultBundle.putSerializable("PRODUIT", produit);
        requireActivity().getSupportFragmentManager().setFragmentResult("PRODUIT_CHOISI", resultBundle);
        Navigation.findNavController(getView()).navigateUp();
    }
}