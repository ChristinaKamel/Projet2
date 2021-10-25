package com.app.facturation.liveData;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.app.facturation.model.Produit;
import com.app.facturation.repositories.DataReprositoryFirestore;
import com.app.facturation.utils.FirebaseUtils;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProduitsLiveData extends LiveData<List<Produit>> implements EventListener<QuerySnapshot> {

    private final List<Produit> produits;

    public ProduitsLiveData() {
        produits = new ArrayList<>();
        FirebaseUtils
                .getInstanceFirestore()
                .collection(DataReprositoryFirestore.COLLECTION_PRODUITS)
                .addSnapshotListener(this);
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        if (error != null) {
            return;
        }
        if (value != null) {
            for (DocumentChange change : value.getDocumentChanges()) {
                DocumentSnapshot documentSnapshot = change.getDocument();
                if (change.getType().equals(DocumentChange.Type.ADDED)) {
                    produits.add(documentSnapshot.toObject(Produit.class));
                }
            }
            setValue(produits);
        }
    }

}

