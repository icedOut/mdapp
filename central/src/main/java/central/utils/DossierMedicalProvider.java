package central.utils;

import central.dao.DAODossierMedical;
import central.dto.DTOAntecedentMedical;
import central.dto.DTODossierMedical;
import central.dto.DTOEtablissementSante;
import central.dto.DTOVisiteMedicale;
import central.models.AntecedentMedical;
import central.models.DossierMedical;
import central.models.VisiteMedicale;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DossierMedicalProvider {

  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Optional<DossierMedical> getDossier(int dossierId){
    try{
      DAODossierMedical dossierDao = JDBCConnection.getDossierDAO();

      DossierMedical dossierToCreate = new DossierMedical();
      String id = String.valueOf(dossierId);
      DTODossierMedical dbDossier = dossierDao.queryForId(id);


      List<DTOVisiteMedicale> dbVisites = JDBCConnection
              .getVisiteDAO()
              .queryForEq("idDossier", dbDossier.id);


      List<DTOAntecedentMedical> dbAntecedents = JDBCConnection
              .getAntecedentDAO()
              .queryForEq("idDossier", dbDossier.id);


      dossierToCreate.antecedents = mapAntecedent(dbAntecedents);
      dossierToCreate.visites = mapVisite(dbVisites);
      dossierToCreate.id = dbDossier.id;

      return Optional.of(dossierToCreate);
    }
    catch(Exception e){
      return Optional.of(null);
    }
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
