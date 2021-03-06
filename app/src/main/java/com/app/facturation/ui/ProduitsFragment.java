package com.app.facturation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.facturation.R;
import com.app.facturation.adapters.ProduitsAdapter;
import com.app.facturation.databinding.FragmentProduitsBinding;
import com.app.facturation.viewModels.ProduitsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProduitsFragment extends Fragment implements View.OnClickListener {

    private ProduitsViewModel produitsViewModel;
    private FragmentProduitsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModel();
        binding = FragmentProduitsBinding.inflate(inflater, container, false);
        addClickListeners();
        initRecyclerView();
        return binding.getRoot();
    }

    private void initViewModel() {
        produitsViewModel = new ViewModelProvider(this).get(ProduitsViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        ProduitsAdapter adapter = new ProduitsAdapter();
        produitsViewModel.getProduits().observe(getViewLifecycleOwner(), produits -> adapter.setProduits(produits));
        binding.recyclerViewProduits.setAdapter(adapter);
        binding.recyclerViewProduits.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonAdd.getId()) {
            Navigation.findNavController(getView()).navigate(R.id.action_navigation_produits_to_ajouterProduitFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}