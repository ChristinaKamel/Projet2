const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();
const firestore = admin.firestore();

exports.getFacture = functions.https.onRequest((request, response) => {
    let idFacture = request.query.idFacture;
    if (idFacture) {
        firestore.collection("Factures").doc(idFacture).get().then(docFacture => {
            if (docFacture) {
                updateDatabase(idFacture);
                response.send(composerHtml(idFacture, docFacture));
            } else {
                response.send("Cette facture n'existe pas");
            }
        });
    } else {
        response.send("idFacture indefini");
    }
});

//Pour declencher la notification
const updateDatabase = (idFacture) => {
    firestore.collection("Factures")
                .doc(idFacture)
                .update({
                    "estVue": true
                });
}

const composerHtml = (idFacture, docFacture) => {
    let html = `<!doctype html>
    <head>
      <title>Facture #${idFacture}</title>
    </head>
    <body>
        <h1> Facture </h1>
        <h4> Client: ${docFacture.data().client.nomClient}, ${docFacture.data().client.telephone} </h4>
        <h4> Date d'echeance: ${docFacture.data().dateEcheance} </h4>
      <table>
        <tr>
            <th>Produit</th>
            <th>Quantite</th>
            <th>Prix unitaire</th>
            <th>Prix total</th>
        </tr>`;

    docFacture.data().produits.forEach(element => {
        html += `
            <tr>
                <td>${element.first.nomProduit}</td>
                <td>${element.second.quantite}</td>
                <td>${element.first.prixUnitaire}</td>
                <td>${element.first.prixUnitaire * element.second.quantite}</td>
            </tr>`
    });


    html += `</table>
    <h4> Montant total: $${docFacture.data().total} </h4>
    </body>
  </html>`;
  return html;
}