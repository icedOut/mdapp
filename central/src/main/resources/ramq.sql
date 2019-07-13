
CREATE TABLE UTILISATEUR(
  codeUsager TEXT UNIQUE,
  typePermission VARCHAR(60) CHECK(typePermission IN('CREER_DOSSIER','LIRE_DOSSIER','MODIFIER_DOSSIER','RECONSTITUER_DOSSIER')),
  hash VARCHAR(40) UNIQUE,
  salt VARCHAR(40) UNIQUE,  
  PRIMARY KEY(codeUsager)
);

CREATE TABLE PATIENT(
  id INTEGER UNIQUE,
  codeUsager TEXT NOT NULL,
  etatDossier INTEGER NOT NULL,
  nom VARCHAR(40) NOT NULL,
  prenom VARCHAR(40) NOT NULL,
  codeRAMQ VARCHAR(40) NOT NULL,
  dateNaissance DATE NOT NULL,
  nas VARCHAR(40) NOT NULL,
  genre VARCHAR(40) CHECK(Genre IN('HOMME','FEMME')),
  courriel VARCHAR(40) NOT NULL,
  numeroTelephone VARCHAR(40) NOT NULL,
  typeTelephone VARCHAR(40) CHECK(TypeTelephone IN('MOBILE','MAISON','TRAVAIL')),
  numPorte  VARCHAR(40) NOT NULL,
  numAppartement VARCHAR(40),
  nomRue VARCHAR(40) NOT NULL,
  ville VARCHAR(40) NOT NULL,
  codePostal VARCHAR(40) NOT NULL,
  nomParent1 VARCHAR(40) NOT NULL,
  prenomParent1 VARCHAR(40) NOT NULL,
  nomParent2 VARCHAR(40) NOT NULL,
  prenomParent2 VARCHAR(40) NOT NULL,

  PRIMARY KEY(id),
  FOREIGN KEY(codeUsager) REFERENCES UTILISATEUR(codeUsager)
);

CREATE TABLE PERSONNEL_SANTE(
  idPersonnel INTEGER,
  codeUsager VARCHAR(40) NOT NULL,
  PRIMARY KEY (idPersonnel),
  FOREIGN KEY (codeUsager) REFERENCES UTILISATEUR(codeUsager)
);



CREATE TABLE DOSSIER_MEDICAL(
  id INTEGER NOT NULL,
  idPatient INTEGER NOT NULL,
  etatPrecedent INTEGER,
  dateModif DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (idPatient) REFERENCES PATIENT(id)
);



CREATE TABLE ANTECEDENT_MEDICAL(
  id INTEGER NOT NULL,
  idDossier INTEGER,
  debutMaladie DATE,
  finMaladie DATE,
  diagnostic TEXT,
  nomTraitement TEXT,
  medicament TEXT,
  PRIMARY KEY(id)
  FOREIGN KEY(idDossier) REFERENCES DOSSIER_MEDICAL(id)
);

CREATE TABLE ETABLISSEMENT_MEDICAL(
  id INTEGER NOT NULL,
  nom VARCHAR(40) NOT NULL,
  PRIMARY KEY(id)
);


CREATE TABLE VISITE_MEDICALE(
  id INTEGER NOT NULL,
  idDossier INTEGER NOT NULL,
  dateVisite DATE,
  notes VARCHAR(40) NOT NULL,
  resume VARCHAR(250) NOT NULL,
  diagnostic VARCHAR(40) NOT NULL,
  nomTraitement TEXT,
  medicament TEXT,
  idEtablissement INTEGER,
  PRIMARY KEY(id),
  FOREIGN KEY(idEtablissement) REFERENCES ETABLISSEMENT_MEDICAL(id)
  FOREIGN KEY(idDossier) REFERENCES DOSSIER_MEDICAL(id)
);



CREATE TABLE SESSION(
  id INTEGER NOT NULL,
  idUsager TEXT NOT NULL,
  token TEXT,
  expiration DATE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(idUsager) REFERENCES UTILISATEUR(codeUsager)
);

INSERT INTO ETABLISSEMENT_MEDICAL VALUES(1, "Clinique Super Bonne Inc.");
INSERT INTO ETABLISSEMENT_MEDICAL VALUES(2, "Clinique Super Malade Inc.");

INSERT INTO UTILISATEUR VALUES("ocampeau", "LIRE_DOSSIER","ace67f9ce490d9f198ed0611039e34cd3dadf9c7d27ffc80d470b74af966c656","dcda9953-3a22-4c51-a24b-3b5141bdcea6");
INSERT INTO UTILISATEUR VALUES("tselanne", "LIRE_DOSSIER","ace67f9ce490d9f198ed0611039e34cd3dadf9c7d27ffc80d470b74af966c65","dcda9953-3a22-4c51-a24b-3b5141bdcea99");
INSERT INTO UTILISATEUR VALUES("pkariya", "LIRE_DOSSIER","ace67f9ce490d9f198ed0611039e34cd3dadf9c7d27ffc80d470b74af966c6","dcda9953-3a22-4c51-a24b-3b5141bdce987");

INSERT INTO UTILISATEUR VALUES("medecin1", "MODIFIER_DOSSIER","6041436bbe6d0d38341aa562de906ef90a67292ad32f81f5c24c925ed0118534","dcda9953-3a22-4c51-a24b-3b5141bdcea");

INSERT INTO PATIENT VALUES(1, "ocampeau", 1, "Olivier", "Campeau", "RAMQ00000001","1987-23-02", "000-111-222", "HOMME", "olivier@ramq.com","514-000-0000", "MOBILE", "23", "B2", "Principale", "Montreal", "H0H 0H0", "John", "Leclair", "Mario", "Lemieux");
INSERT INTO PATIENT VALUES(2, "tselanne", 2, "Teemu", "Selanne", "RAMQ00000002","1981-23-02", "000-111-223", "HOMME", "selanne@ramq.com","514-000-0001", "MOBILE", "24", "B4", "Principale", "Montreal", "H0H 0H0", "John", "Leclair", "Mario", "Lemieux");
INSERT INTO PATIENT VALUES(3, "pkariya", 3, "Paul", "Kariya", "RAMQ00000003","1979-23-02", "000-111-224", "HOMME", "kariya@ramq.com","514-000-0002", "MOBILE", "25", "B5", "Principale", "Montreal", "H0H 0H0", "John", "Leclair", "Mario", "Lemieux");

INSERT INTO PERSONNEL_SANTE VALUES(1, "medecin1");


INSERT INTO DOSSIER_MEDICAL VALUES(1, 1, 1, '2019-07-05 22:52:49');
INSERT INTO DOSSIER_MEDICAL VALUES(2, 2, 2, '2019-07-05 22:52:49');
INSERT INTO DOSSIER_MEDICAL VALUES(3, 3, 3, '2019-07-05 22:52:49');





INSERT INTO ANTECEDENT_MEDICAL VALUES(1, 1, '2019-07-05 22:52:09', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");
INSERT INTO ANTECEDENT_MEDICAL VALUES(2, 2, '2019-07-06 23:36:40', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");
INSERT INTO ANTECEDENT_MEDICAL VALUES(3, 3, '2019-07-07 04:22:17', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");
INSERT INTO ANTECEDENT_MEDICAL VALUES(4, 1, '2019-07-08 14:59:24', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");
INSERT INTO ANTECEDENT_MEDICAL VALUES(5, 2, '2019-07-09 08:22:45', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");
INSERT INTO ANTECEDENT_MEDICAL VALUES(6, 3, '2019-07-10 07:22:19', '2019-07-05 22:52:49', "Grippe d'homme", "De la patience", "Kraft Dinner");


INSERT INTO VISITE_MEDICALE VALUES(1, 1, '2019-07-05 22:52:49', "Rien a signalé", "Blablabla", "Voici un diagnostic", "Traitement", "Medicament", 1);
INSERT INTO VISITE_MEDICALE VALUES(2, 1, '2019-07-05 22:52:49', "Vous allez mourir", "Rien d'autre a faire que d'attendre", "Voici un diagnostic", "Traitement", "Medicament", 1);
INSERT INTO VISITE_MEDICALE VALUES(3, 1, '2019-07-05 22:52:49', "Cette personne là il faut la laisser parler", "C'est plate...", "Voici un diagnostic", "Traitement", "Medicament", 1);
INSERT INTO VISITE_MEDICALE VALUES(4, 2, '2019-07-05 22:52:49', "Sauvez vous", "...j'en ai marre", "Voici un diagnostic", "Traitement", "Medicament", 1);
INSERT INTO VISITE_MEDICALE VALUES(5, 3, '2019-07-05 22:52:49', "J'ai hate a ma retraite", "je suis plus de travailler!", "Voici un diagnostic", "Traitement", "Medicament", 1);


