package com.app.facturation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.facturation.databinding.ElementFactureBinding;
import com.app.facturation.model.Facture;

import java.util.List;

public class FacturesAdapter extends RecyclerView.Adapter<FacturesAdapter.FactureViewHolder> {

    private List<Facture> factures;

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    @NonNull
    @Override
    public FactureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ElementFactureBinding binding = ElementFactureBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new FactureViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FactureViewHolder holder, int position) {
        holder.bind(factures.get(position));
    }

    @Override
    public int getItemCount() {
        return factures == null ? 0 : factures.size();
    }

    class FactureViewHolder extends RecyclerView.ViewHolder {

        private ElementFactureBinding binding;

        public FactureViewHolder(@NonNull ElementFactureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Facture facture) {
            binding.textViewDateEcheance.setText(facture.getDateEcheance());
            binding.textViewIdFacture.setText(facture.getIdFacture());
            binding.textViewMontant.setText(String.valueOf(facture.getTotal()));
            if (facture.getClient() != null) {
                binding.textViewNomClient.setText(facture.getClient().getNomClient());
            }
        }
    }
}

