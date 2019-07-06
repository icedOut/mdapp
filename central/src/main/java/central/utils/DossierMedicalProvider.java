package central.utils;

import central.dto.*;
import central.models.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DossierMedicalProvider {



  public static Optional<DossierMedical> getDossier(int dossierId){
    try{
      DTODossierMedical dbDossier = JDBCConnection.getDossierDAO().queryForId(String.valueOf(dossierId));
      DTOPatient dbPatient = JDBCConnection.getPatientDAO().queryForId(String.valueOf(dbDossier.idPatient));

      List<DTOVisiteMedicale> dbVisites = JDBCConnection
              .getVisiteDAO()
              .queryForEq("idDossier", dbDossier.id);


      List<DTOAntecedentMedical> dbAntecedents = JDBCConnection
              .getAntecedentDAO()
              .queryForEq("idDossier", dbDossier.id);


      DossierMedical dossier = DossierMedicalMapper.dtoToDossier(dbDossier, dbVisites, dbAntecedents, dbPatient);
      return Optional.of(dossier);
    }
    catch(SQLException e){
      return Optional.of(null);
    }
  }

}
