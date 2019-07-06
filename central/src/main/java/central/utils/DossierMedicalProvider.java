package central.utils;

import central.dao.DAODossierMedical;
import central.dto.*;
import central.models.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DossierMedicalProvider {

  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Optional<DossierMedical> getDossier(int dossierId){
    try{
      DossierMedical dossierToCreate = new DossierMedical();
      String id = String.valueOf(dossierId);
      DTODossierMedical dbDossier = JDBCConnection.getDossierDAO().queryForId(id);
      DTOPatient dbPatient = JDBCConnection.getPatientDAO().queryForId(String.valueOf(dbDossier.idPatient));

      List<DTOVisiteMedicale> dbVisites = JDBCConnection
              .getVisiteDAO()
              .queryForEq("idDossier", dbDossier.id);


      List<DTOAntecedentMedical> dbAntecedents = JDBCConnection
              .getAntecedentDAO()
              .queryForEq("idDossier", dbDossier.id);


      dossierToCreate.antecedents = mapAntecedent(dbAntecedents);
      dossierToCreate.visites = mapVisite(dbVisites);
      dossierToCreate.patient = mapPatient(dbPatient);
      dossierToCreate.id = dbDossier.id;

      return Optional.of(dossierToCreate);
    }
    catch(SQLException e){
      return Optional.of(null);
    }
  }

  private static Patient mapPatient(DTOPatient dtoPatient){
    Patient patient = new Patient();
    patient.codeRAMQ = dtoPatient.codeRAMQ;
    patient.genre = Genre.valueOf(dtoPatient.genre);
    patient.id = dtoPatient.id;
    patient.nas = dtoPatient.nas;
    patient.nom = dtoPatient.nom;
    patient.prenom = dtoPatient.prenom;
    patient.coordonnee = mapCoordonner(dtoPatient);
    patient.parent1 = mapParent(dtoPatient.nomParent1, dtoPatient.prenomParent1);
    patient.parent2 = mapParent(dtoPatient.nomParent2, dtoPatient.prenomParent2);
    try{
      patient.dateNaissance = dateFormater.parse(dtoPatient.dateNaissance);

    }
    catch(Exception e){

    }
    return patient;
  }

  private static Coordonnee mapCoordonner(DTOPatient dtoPatient){
    Coordonnee coords = new Coordonnee();
    coords.courriel = dtoPatient.courriel;
    coords.adresse = mapAdresse(dtoPatient);
    coords.telephone = mapTelephone(dtoPatient);
    return coords;
  }

  private static AdresseResidentielle mapAdresse(DTOPatient dtoPatient){
    AdresseResidentielle adr = new AdresseResidentielle();
    adr.codePostal = dtoPatient.codePostal;
    adr.nomRue = dtoPatient.nomRue;
    adr.numAppartement = dtoPatient.numAppartement;
    adr.numPorte = dtoPatient.numPorte;
    adr.ville = dtoPatient.ville;
    return adr;
  }

  private static NumeroTelephone mapTelephone(DTOPatient dtoPatient){
    NumeroTelephone tel = new NumeroTelephone();
    tel.numero = dtoPatient.numeroTelephone;
    tel.type = TypeTelephone.valueOf(dtoPatient.typeTelephone);
    return tel;
  }

  private static Parent mapParent(String nom, String prenom){
      Parent p = new Parent();
      p.nom = nom;
      p.prenom = prenom;
      return p;
  }


  private static List<AntecedentMedical> mapAntecedent(List<DTOAntecedentMedical> antecedentsDB){
    return antecedentsDB.stream().map(a -> mappingLogicAntecendent(a)).collect(Collectors.toList());
  }

  private static AntecedentMedical mappingLogicAntecendent(DTOAntecedentMedical dbAntecedent){
    AntecedentMedical ante = new AntecedentMedical();
    ante.diagnostic = dbAntecedent.diagnostic;
    ante.id = dbAntecedent.id;
    ante.traitement.medicament = dbAntecedent.medicament;
    ante.traitement.nomTraitement = dbAntecedent.nomTraitement;
    try{
      ante.debutMaladie = dateFormater.parse(dbAntecedent.debutMaladie);
      ante.debutMaladie = dateFormater.parse(dbAntecedent.debutMaladie);
      ante.finMaladie = dateFormater.parse(dbAntecedent.finMaladie);
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return ante;
  }


  private static List<VisiteMedicale> mapVisite(List<DTOVisiteMedicale> dbVisites){
    return dbVisites.stream().map(v -> mappingLogicVisite(v)).collect(Collectors.toList());
  }

  private static VisiteMedicale mappingLogicVisite(DTOVisiteMedicale dbVisite){
    VisiteMedicale visite = new VisiteMedicale();
    visite.id = dbVisite.id;
    visite.diagnostic = dbVisite.diagnostic;
    visite.notes = dbVisite.notes;
    visite.resume = dbVisite.resume;
    visite.traitement.nomTraitement = dbVisite.nomTraitement;
    visite.traitement.medicament = dbVisite.medicament;
    try{

      visite.dateVisite = dateFormater.parse(dbVisite.dateVisite);
      DTOEtablissementSante etab = JDBCConnection
              .getEtablissementDAO()
              .queryForId(String.valueOf(dbVisite.idEtablissement));
      visite.etablissement.id = etab.id;
      visite.etablissement.nom = etab.nom;
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return visite;
  }

}
