# SystemeCentral
___


### Base de données

Le scipt de base de données est dans le répertoire `/central/`
Pour créer une BD neuve à partir du script, il faut exécuter la commande suivante:
```
sqlite3 ramq.db < ramq.sql
```

Assurez-vous d'exécuter cette commande là dans le répertoire `/central/`, là où se trouve le script SQL. 

Il faut absolument que la base de données soit dans le répertoire `/central/`, dans le même 
répertoire où se trouve le script `ramq.sql`,  sinon la référence ne fonctionnera pas.


### Démarrer le serveur
Pour démarrer le serveur (le Système central) en utilisant Maven, il faut exécuter la commande 
suivante dans le répertoire `/central/`:

```
mvn clean install spring-boot:run
```
Le serveur écoute sur le port `23000`. Assurez vous que ce port est disponible sur votre machine. 
Nous avons utilisez ce port peu commun justement pour éviter au maximum des conflits de port.


# AppMedecin

### Démarrer le programme
Pour démarrer l'application des médecins (le AppMédecin) en utilisant Maven, il faut exécuter la commande 
suivante dans le répertoire `/app-medecin/`:

```
mvn clean install exec:exec
```

### Comment utiliser le programme
Pour se connecter, il faut utiliser les identifiants suivants:
* username: `medecin1`
* password: `admin`

Pour charger un dossier, il y a trois dossiers existants dans la base de données
* RAMQ00000001
* RAMQ00000002
* RAMQ00000003

Pour de plus amples informations, la dernière page du rapport final PDF remis pour ce travail
contient des captures d'écran qui expliquent comment utiliser l'application.