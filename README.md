# SystemeCentral
___


### Base de données
Le scipt de base de données est dans le répertoire /central/
Pour créer une BD neuve à partir du script, il faut exécuter la commande suivante:
```
sqlite3 ramq.db < ramq.sql
```

Assurez-vous d'exécuter cette commande là dans le répertoire `/central/`. Aurement dit, votre répertoire courant (`pwd`) doit être `/central/`.

Il faut absolument que la base de données soit dans le répertoire `/central/`, sinon la référence ne fonctionnera pas.


### Démarrer le serveur
Il faut installer Maven, et ensuite lancer la commande suivante dans le répertoire `/central/`:

```
mvn clean install package spring-boot:run
```
Le serveur écoute sur le port `23000`. Assurez vous que ce port est disponible.


# AppMedecin

### Démarrer le programme
Il faut installer Maven, et ensuite lancer la commande suivante dans le répertoire `/medecin/`:

```
mvn clean install package exec:exec
```

### Coment utiliser le programme
Pour se connecter, il faut utiliser les identifiants suivants:
* username: `medecin1`
* password: `admin`

Pour charger un dossier, il y a trois dossiers dans la base de données
* RAMQ00000001
* RAMQ00000002
* RAMQ00000003