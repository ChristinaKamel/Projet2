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
import com.app.facturation.adapters.ClientClickListener;
import com.app.facturation.adapters.ClientsAdapter;
import com.app.facturation.databinding.FragmentChoisirClientBinding;
import com.app.facturation.model.Client;
import com.app.facturation.viewModels.ClientsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChoisirClientFragment extends Fragment implements View.OnClickListener, ClientClickListener {

    public ClientsViewModel clientsViewModel;
    private FragmentChoisirClientBinding binding;

    public ChoisirClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        binding = FragmentChoisirClientBinding.inflate(inflater, container, false);
        initRecyclerView();
        addClickListeners();
        return binding.getRoot();
    }

    private void initViewModels() {
        clientsViewModel = new ViewModelProvider(this).get(ClientsViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        ClientsAdapter adapter = new ClientsAdapter(this);
        clientsViewModel.getClients().observe(getViewLifecycleOwner(), clients -> adapter.setClients(clients));
        binding.recyclerViewClients.setAdapter(adapter);
        binding.recyclerViewClients.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.buttonAdd.getId()) {
            Navigation.findNavController(getView()).navigate(R.id.action_choisirClientFragment_to_ajouterClientFragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClientClicked(Client client) {
        Bundle resultBundle = new Bundle();
        resultBundle.putSerializable("CLIENT", client);
        requireActivity().getSupportFragmentManager().setFragmentResult("CLIENT_CHOISI", resultBundle);
        Navigation.findNavController(getView()).navigateUp();
    }
}