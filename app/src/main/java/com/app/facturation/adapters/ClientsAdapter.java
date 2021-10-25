package com.app.facturation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.facturation.R;
import com.app.facturation.databinding.ElementClientBinding;
import com.app.facturation.model.Client;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientViewHolder> {

    private List<Client> clients;

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ElementClientBinding binding = ElementClientBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ClientViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.bind(clients.get(position));
    }

    @Override
    public int getItemCount() {
        return clients == null ? 0 : clients.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {

        private ElementClientBinding binding;

        public ClientViewHolder(@NonNull ElementClientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Client client) {
            binding.textViewClientName.setText(client.getNomClient());
        }
    }
}
