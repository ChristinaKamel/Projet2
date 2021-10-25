package com.app.facturation.utils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {

    public static FirebaseFirestore instanceFirestore;

    public static FirebaseFirestore getInstanceFirestore() {
        if (instanceFirestore == null) {
            instanceFirestore = FirebaseFirestore.getInstance(FirebaseApp.getInstance());
        }
        return instanceFirestore;
    }

}
