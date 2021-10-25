package com.app.facturation.liveData;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.app.facturation.model.Facture;
import com.app.facturation.repositories.DataReprositoryFirestore;
import com.app.facturation.utils.FirebaseUtils;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FacturesLiveData extends LiveData<List<Facture>> implements EventListener<QuerySnapshot> {

    private final List<Facture> factures;

    public FacturesLiveData() {
        factures = new ArrayList<>();
        FirebaseUtils
                .getInstanceFirestore()
                .collection(DataReprositoryFirestore.COLLECTION_FACTURES)
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
                    factures.add(documentSnapshot.toObject(Facture.class));
                }
            }
            setValue(factures);
        }
    }

}
