package com.app.facturation.utils;

import com.google.firebase.BuildConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {

    public static final boolean USE_EMULATORS = BuildConfig.DEBUG;
    public static FirebaseFirestore instanceFirestore;

    public static FirebaseFirestore getInstanceFirestore() {
        if (instanceFirestore == null) {
            instanceFirestore = FirebaseFirestore.getInstance(FirebaseApp.getInstance());
            if (USE_EMULATORS) {
                instanceFirestore.useEmulator("10.0.2.2", 8080);
            }
        }
        return instanceFirestore;
    }

}
