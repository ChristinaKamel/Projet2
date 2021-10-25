package com.app.facturation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.facturation.databinding.ElementProduitBinding;
import com.app.facturation.model.Produit;

import java.util.List;

public class ProduitsAdapter extends RecyclerView.Adapter<ProduitsAdapter.ProduitViewHolder> {

    private List<Produit> produits;
    private ProduitClickListener produitClickListener;

    public ProduitsAdapter() {
    }

    public ProduitsAdapter(ProduitClickListener produitClickListener) {
        this.produitClickListener = produitClickListener;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ElementProduitBinding binding = ElementProduitBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ProduitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitViewHolder holder, int position) {
        holder.bind(produits.get(position));
    }

    @Override
    public int getItemCount() {
        return produits == null ? 0 : produits.size();
    }

    class ProduitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ElementProduitBinding binding;

        public ProduitViewHolder(@NonNull ElementProduitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Produit produit) {
            binding.textViewNomProduit.setText(produit.getNomProduit());
            binding.textViewPrixUnitaire.setText(String.valueOf(produit.getPrixUnitaire()));
            if (produitClickListener != null) {
                binding.getRoot().setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (produitClickListener != null) {
                produitClickListener.onProduitClicked(produits.get(getAdapterPosition()));
            }
        }
    }

}

