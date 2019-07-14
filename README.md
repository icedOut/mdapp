# SystemeCentral
___
### Routes HTTP
* GET /dossier?id={id}:
* PUT /dossier/modification
* PUT /dossier/annulation

### Base de données
Le scipt de base de données est dans le répertoire /central/src/main/resources/ramq/sql
Pour créer une BD neuve à partir du script, il faut exécuter la commande suivante:
```
sqlite3 ramq.db < ramq.sql
```

Assurez-vous d'exécuter cette commande là dans le répertoire `/central/src/main/resources/`. Aurement dit, votre répertoire courant (`pwd`) doit être `/central/src/main/resources/`.

Il faut absolument que la base de données soit dans le répertoire `/central/src/main/resources/`, sinon la référence ne fonctionnera pas.


### Démarrer le serveur
Il faut installer Maven, et ensuite lancer la commande suivante dans le répertoire `/central/`:
```
mvn clean install
mvn package
mvn spring-boot:run
```
Le serveur écoute sur le port `23000`. Donc, par exemple, pour appeller en local la route `GET /dossier?id={id}` avec le dossier de ID=1, il faut faire `http://localhost:23000/dossier?id=1`.


# AppMedecin

### Démarrer le programme
Il faut installer Maven, et ensuite lancer la commande suivante dans le répertoire `/medecin/`:
```
mvn clean install
mvn package
mvn clean package exec:exec
```

### Coment utiliser le programme
Pour se connecter, il faut utiliser les identifiants suivants:
* username: `medecin1`
* password: `admin`

Pour charger un dossier, il y a trois dossiers dans la base de données
* RAMQ00000001
* RAMQ00000002
* RAMQ00000003