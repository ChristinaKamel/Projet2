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
import com.app.facturation.adapters.FacturesAdapter;
import com.app.facturation.databinding.FragmentFacturesBinding;
import com.app.facturation.viewModels.FacturesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FacturesFragment extends Fragment implements View.OnClickListener {

    private FacturesViewModel facturesViewModel;
    private FragmentFacturesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModel();
        binding = FragmentFacturesBinding.inflate(inflater, container, false);
        initRecyclerView();
        addClickListeners();
        return binding.getRoot();
    }

    private void initViewModel() {
        facturesViewModel = new ViewModelProvider(this).get(FacturesViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        FacturesAdapter adapter = new FacturesAdapter();
        facturesViewModel.getFactures().observe(getViewLifecycleOwner(), factures -> adapter.setFactures(factures));
        binding.recyclerViewFactures.setAdapter(adapter);
        binding.recyclerViewFactures.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonAdd.getId()) {
            Navigation.findNavController(getView()).navigate(R.id.action_navigation_factures_to_ajouterFactureFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}