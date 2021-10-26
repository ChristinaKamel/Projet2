# Projet C2: Une application Android de Facturatiom
## Brief des besoins
Plusieurs petites entreprises ou “small business" ont besoin d’envoyer des factures à leurs clients. Un nombre important de ces entreprises ne possèdent pas un ordinateur et comptent faire toutes leurs opérations y compris la facturation depuis leur smartphone. On propose de faire une application Android pour ce besoin. 

## La specification
### Les besoins fonctionnels
En étudiant le marché sur Google Play Store, j’ai examiné les applications de facturation les plus populaires. Afin de déterminer les besoins fonctionnels de l’application à développer, j’ai compilé une liste comprenant les fonctionnalités de ces applications ainsi que celles qui sont demandés par ses utilisateurs (dans la section “reviews”). Voici quelques éléments de cette liste:

Définir une liste de clients (Nom, prénom, email, numéro de téléphone, …)

Définir une liste d'éléments à vendre (Nom, prix, …) 

Définir des factures (Client, date, liste d'éléments, …)

Envoyer cette facture au client sous forme de lien.

Une notification lorsqu’une facture est lue par un client.

### Diagrame de flux


## L'architecture
L'architecture de cette application suit les meilleures pratiques de Google en utilisant les "Android Architecture Components"

<img src="https://user-images.githubusercontent.com/37004573/138803969-3393f98a-edf5-4db2-8947-3804e861a86f.png" width="600">

*Activity / Fragment (UI): Pour chaque "view", un fichier XML ("layout") et un fragment. Un fragment observe les changements de donnees des variables "liveData" en les affichant. La navigation se fait entre les fragments.

*ViewModel: Contient des variables "LiveData" qui les cherche de la "repository". Le type LiveData est defini par la librairie Android et permet d'observer les changements des donnees qu'il contient (Similairement a un Observable).

*Repository: Injectee sous forme d'interface dans les ViewModel. Une instance de Repository contient du code pour communiquer avec les sources du donnees. Dans notre cas cette instance contient du code pour sauvegarder et telecharger les donnees de factures depuis Firebase Firestore. Dans le cas ou, a l'avenir, on ne veut plus utiliser Firebase Firestore, il suffit de changer ce fichier, tout en implementant l'interface principale.

*Remote data source: La source de donnees pour ce projet est [Firebase Firestore](https://firebase.google.com/products/firestore), une base de donnees NoSQL. Ces donnees sont structures dans la forme suivante:

```
{utilisateurs: { //collection
    <id_utilisateur>: { //document
      Clients: { //collection
        //Chaque client est sauvegarde dans un document suivant le modele 
        //Projet2/app/src/main/java/com/app/facturation/model/Client
      }, 
      Produits: { //collection
        //Chaque produit est sauvegarde dans un document suivant le modele
        //Projet2/app/src/main/java/com/app/facturation/model/Produit
      },
      Factures: { //collection
        //Chaque produit est sauvegarde dans un document suivant le modele
        //Projet2/app/src/main/java/com/app/facturation/model/Facture
      }
    }
  }
}
```


*[Firebase Functions](https://firebase.google.com/products/functions): Deux fonctions, backend, serverless sont definis a l'aide de Firebase Functions:

  ![Screenshot 2021-10-26 071237](https://user-images.githubusercontent.com/37004573/138807699-3d25f603-6ec0-4bab-a1a2-2849f5a6b176.png)

    getFacture, est utilisee pour fournir les factures depuis firestore sous forme HTML depuis le lien https://us-central1-appfacturation-ef26c.cloudfunctions.net/getFacture?idFacture={idFacture}
    Une fois cette fonction est invoquee, elle modifie le document firestore de la facture correspondante (estVue=true) qui declenche la fonction declencherNotification, qui a son tour envoie une notification en utilisant Firebase Cloud Messaging.
    Code de ces fonctions: Projet2/tree/main/functions/
    
    
*[Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging): J'ai utilise le firebase cloud messaging pour envoyer des push notifications qui signalent a l'utilisateur que la notification qui l'a envoye a ete lue par son client. Pour cela, je sauvegarde le FCMToken(un identifiant pour envoyer des notifications) dans firestore avec chaque facture. Ces notifications sont declenches par la fonction declencherNotification (Projet2/tree/main/functions/declencherNotification.js)




## Le code 
Les fichiers JAVA: Projet2/app/src/main/java/com/app/facturation/

Les fichiers XML: Projet2/tree/main/app/src/main/res/layout/

Les fichiers Firebase Functions: Projet2/tree/main/functions/

### Installation
Cette application est executee avec la derniere version [Android Studio](https://developer.android.com/studio)

