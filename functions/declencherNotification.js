const functions = require("firebase-functions");
const admin = require("firebase-admin");
const firestore = admin.firestore();

exports.declencherNotification = functions.firestore.document('Factures/{idFacture}')
    .onWrite((change, context) => {
        if (change.after.exists) {
            const docFacture = change.after.data();
            if(docFacture.estVue && !docFacture.utilisateurEstNotifie) {
                envoyerNotification(context.params.idFacture, docFacture);
            }
        }
    });

//Pour ne plus declencher la notification
const updateDatabase = (idFacture) => {
    firestore.collection("Factures")
                .doc(idFacture)
                .update({
                    "utilisateurEstNotifie": true
                });
}

const envoyerNotification = (idFacture, docFacture) => {
    const message = `La facture ${idFacture} est lue`;
    const payload = {
        token: docFacture.fcmToken,
        notification: {
            title: 'Facturation',
            body: message
        },
        data: {
            body: message,
        }
    };
    
    admin.messaging().send(payload).then((response) => {
        updateDatabase(idFacture);
        return {success: true};
    }).catch((error) => {
        return {error: error.code};
    });
}