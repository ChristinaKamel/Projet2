package com.app.facturation.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.facturation.databinding.ElementAjouterFactureProduitBinding;
import com.app.facturation.model.Produit;
import com.app.facturation.model.Quantite;
import com.app.facturation.utils.MonPair;

import java.util.List;

public class SelectionProduitsAdapter extends RecyclerView.Adapter<SelectionProduitsAdapter.ProduitViewHolder> {

    private List<MonPair<Produit, Quantite>> produits;
    private FactureProduitClickListener factureProduitClickListener;

    public SelectionProduitsAdapter(FactureProduitClickListener factureProduitClickListener) {
        this.factureProduitClickListener = factureProduitClickListener;
    }

    public void setProduits(List<MonPair<Produit, Quantite>> produits) {
        this.produits = produits;
        Log.e(produits.size() + "", produits.size() + "");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ElementAjouterFactureProduitBinding binding = ElementAjouterFactureProduitBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ProduitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitViewHolder holder, int position) {
        if (position < produits.size()) {
            holder.bind(produits.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return produits == null ? 0 : produits.size();
    }

    class ProduitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ElementAjouterFactureProduitBinding binding;

        public ProduitViewHolder(ElementAjouterFactureProduitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MonPair<Produit, Quantite> pair) {
            if (pair != null) {
                binding.setPairProduitQuantite(pair);
            }
            binding.editTextProduit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            factureProduitClickListener.onFactureProduitClicked(getAdapterPosition());
        }
    }
}
