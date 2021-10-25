package com.app.facturation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.facturation.R;
import com.app.facturation.adapters.ClientsAdapter;
import com.app.facturation.databinding.FragmentClientsBinding;
import com.app.facturation.model.Client;
import com.app.facturation.viewModels.ClientsViewModel;
import com.app.facturation.viewModels.FacturesViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ClientsFragment extends Fragment implements View.OnClickListener {

    private ClientsViewModel clientsViewModel;
    private FragmentClientsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModel();
        binding = FragmentClientsBinding.inflate(inflater, container, false);
        addClickListeners();
        initRecyclerView();
        return binding.getRoot();
    }

    private void initViewModel() {
        clientsViewModel = new ViewModelProvider(this).get(ClientsViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        ClientsAdapter adapter = new ClientsAdapter();
        clientsViewModel.getClients().observe(getViewLifecycleOwner(), clients -> adapter.setClients(clients));
        binding.recyclerViewClients.setAdapter(adapter);
        binding.recyclerViewClients.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonAdd.getId()) {
            Navigation.findNavController(getView()).navigate(R.id.action_navigation_clients_to_ajouterClientFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}