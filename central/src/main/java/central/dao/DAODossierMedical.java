package central.dao;


import central.data.DaoProvider;
import central.dto.*;
import central.mapper.AntecedentMapper;
import central.mapper.DossierMedicalMapper;
import central.mapper.EtablisementMapper;
import central.mapper.VisiteMedicaleMapper;
import central.models.DossierMedical;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DAODossierMedical extends BaseDaoImpl<DTODossierMedical, String> {



  public DAODossierMedical(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTODossierMedical.class);
  }


  public Optional<DossierMedical> obtenirDossier(String codeRAMQ){
    try{
      if(codeRAMQ.startsWith("\uFEFF")){
        codeRAMQ = codeRAMQ.substring(1);
      }
      String code = codeRAMQ;
      Optional<DTOPatient> dtoPatient = DaoProvider
              .getPatientDAO()
              .queryForAll()
              .stream()
              .filter(d -> d.codeRAMQ.compareTo(code) == 0)
              .findFirst();

      return prepareDossier(dtoPatient.get().etatDossier);

    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }
    catch(NullPointerException ne){
      System.out.println(ne.toString());
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return null;
  }



  public Optional<DossierMedical> modifierDossier(DossierMedical modification){
    try {
      int idPatient = DaoProvider.getDossierDAO().queryForId(String.valueOf(modification.id)).idPatient;
      DTODossierMedical dtoDossier = DossierMedicalMapper.mapToDTO(modification, idPatient);
      DaoProvider.getDossierDAO().create(dtoDossier);
      List<DTOAntecedentMedical> dtoAntecedents = AntecedentMapper.getAntecedentsDtosFromDossier(modification, dtoDossier.id);
      List<DTOVisiteMedicale> dtoVisites = VisiteMedicaleMapper.getVisitesDtoFromDossier(modification, dtoDossier.id);

      addAntecedentsToDatabase(dtoAntecedents);
      addVisitesToDatabase(dtoVisites);
      updatePatient(dtoDossier.idPatient, dtoDossier.id);
      return obtenirDossier(modification.patient.codeRAMQ);

    }
    catch (SQLException sqle) {
      System.out.println(sqle.getCause());
    }
    return Optional.empty();
  }



  public Optional<DossierMedical> obtenirEtatDossierPrecedent(DossierMedical modification){
    try{


      DTODossierMedical dossierDto = DaoProvider.getDossierDAO().queryForId(String.valueOf(modification.id));
      DTODossierMedical versionPrecedente = DaoProvider.getDossierDAO().queryForId(String.valueOf(dossierDto.etatPrecedent));

      DaoProvider.getDossierDAO().create(versionPrecedente);

      int idDossierPrecedent = dossierDto.id;
      int nouvelId = versionPrecedente.id;

      List<DTOAntecedentMedical> dtoAntecedents = DaoProvider
              .getAntecedentDAO()
              .queryForEq("idDossier", dossierDto.etatPrecedent)
              .stream()
              .map(a -> {
                a.idDossier = nouvelId;
                return a;
              })
              .collect(Collectors.toList());
      List<DTOVisiteMedicale> dtoVisites = DaoProvider
              .getVisiteDAO()
              .queryForEq("idDossier", dossierDto.etatPrecedent)
              .stream()
              .map(v -> {
                v.idDossier = nouvelId;
                return v;
              })
              .collect(Collectors.toList());


      addAntecedentsToDatabase(dtoAntecedents);
      addVisitesToDatabase(dtoVisites);
      updatePatient(versionPrecedente.idPatient, nouvelId);
      return obtenirDossier(modification.patient.codeRAMQ);

    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }
    catch(Exception e){
      System.out.println(e.toString());

    }
    return  Optional.empty();
  }

  private Optional<DossierMedical> prepareDossier(int dossierId){
    try{
      DTODossierMedical dbDossier = DaoProvider.getDossierDAO().queryForId(String.valueOf(dossierId));
      DTOPatient dbPatient = DaoProvider.getPatientDAO().queryForId(String.valueOf(dbDossier.idPatient));

      List<DTOVisiteMedicale> dbVisites = DaoProvider
              .getVisiteDAO()
              .queryForEq("idDossier", dbDossier.id);



      List<DTOAntecedentMedical> dbAntecedents = DaoProvider
              .getAntecedentDAO()
              .queryForEq("idDossier", dbDossier.id);


      DossierMedical dossier = DossierMedicalMapper.dtoToDossier(dbDossier, dbVisites, dbAntecedents, dbPatient);
      for(int i = 0; i < dossier.visites.size(); ++i){
        DTOEtablissementSante etablissement = DaoProvider
                .getEtablissementDAO()
                .queryForId(String.valueOf(dossier.visites.get(i).etablissement.id));
        dossier.visites.get(i).etablissement.nom = etablissement.nom;
      }

      return Optional.of(dossier);
    }
    catch(SQLException e){
      return Optional.of(null);
    }
  }


  private static void updatePatient(int patientId, int etatId) throws SQLException{
    DTOPatient patient = DaoProvider.getPatientDAO().queryForId(String.valueOf(patientId));
    patient.etatDossier = etatId;
    DaoProvider.getPatientDAO().update(patient);
  }


  private static void addAntecedentsToDatabase(List<DTOAntecedentMedical> antecedents) throws  SQLException{
    for(int i = 0; i < antecedents.size(); ++i){
      DaoProvider.getAntecedentDAO().create(antecedents.get(i));
    }
  }

  private static void addVisitesToDatabase(List<DTOVisiteMedicale> visites) throws  SQLException{
    for(int i = 0; i < visites.size(); ++i){
      DaoProvider.getVisiteDAO().create(visites.get(i));
    }
  }






}



