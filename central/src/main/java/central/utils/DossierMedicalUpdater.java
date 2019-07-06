package central.utils;

import central.dto.DTOAntecedentMedical;
import central.dto.DTODossierMedical;
import central.dto.DTOPatient;
import central.dto.DTOVisiteMedicale;
import central.models.DossierMedical;
import org.sqlite.JDBC;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class DossierMedicalUpdater {

  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  // Retourne le ID du nouveau dossier
  public static int modifierDossierMedical(DossierMedical modification) {
    try {
      int idPatient = JDBCConnection.getDossierDAO().queryForId(String.valueOf(modification.id)).idPatient;
      DTODossierMedical dtoDossier = DossierMedicalMapper.mapToDTO(modification, idPatient);
      JDBCConnection.getDossierDAO().create(dtoDossier);
      List<DTOAntecedentMedical> dtoAntecedents = DossierMedicalMapper.convertirAntecedentVersDto(modification, dtoDossier.id);
      List<DTOVisiteMedicale> dtoVisites = DossierMedicalMapper.convertirVisitesVersDto(modification, dtoDossier.id);


      addAntecedentsToDatabase(dtoAntecedents);
      addVisitesToDatabase(dtoVisites);
      updatePatient(dtoDossier.idPatient, dtoDossier.id);
      return dtoDossier.id;

    }
    catch (SQLException sqle) {
      System.out.println(sqle.getCause());
    }
    return -1;
  }

  public static int annulerDerniereModification(DossierMedical dossier){
    try{


      DTODossierMedical dossierDto = JDBCConnection.getDossierDAO().queryForId(String.valueOf(dossier.id));
      DTODossierMedical versionPrecedente = JDBCConnection.getDossierDAO().queryForId(String.valueOf(dossierDto.etatPrecedent));

      JDBCConnection.getDossierDAO().create(versionPrecedente);

      int idDossierPrecedent = dossierDto.id;
      int nouvelId = versionPrecedente.id;

      List<DTOAntecedentMedical> dtoAntecedents = JDBCConnection
              .getAntecedentDAO()
              .queryForEq("idDossier", dossierDto.etatPrecedent)
              .stream()
              .map(a -> {
                a.idDossier = nouvelId;
                return a;
              })
              .collect(Collectors.toList());
      List<DTOVisiteMedicale> dtoVisites = JDBCConnection
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
      return versionPrecedente.id;

    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }
    catch(Exception e){
      System.out.println(e.toString());

    }
    return -1;
  }


  private static void updatePatient(int patientId, int etatId) throws SQLException{
    DTOPatient patient = JDBCConnection.getPatientDAO().queryForId(String.valueOf(patientId));
    patient.etatDossier = etatId;
    JDBCConnection.getPatientDAO().update(patient);
  }


  private static void addAntecedentsToDatabase(List<DTOAntecedentMedical> antecedents) throws  SQLException{
    for(int i = 0; i < antecedents.size(); ++i){
      JDBCConnection.getAntecedentDAO().create(antecedents.get(i));
    }
  }

  private static void addVisitesToDatabase(List<DTOVisiteMedicale> visites) throws  SQLException{
    for(int i = 0; i < visites.size(); ++i){
      JDBCConnection.getVisiteDAO().create(visites.get(i));
    }
  }

}
