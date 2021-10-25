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

import com.app.facturation.R;
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
        addClickListeners();
        return binding.getRoot();
    }

    private void initViewModel() {
        facturesViewModel = new ViewModelProvider(this).get(FacturesViewModel.class);
    }

    private void addClickListeners() {
        binding.buttonAdd.setOnClickListener(this);
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