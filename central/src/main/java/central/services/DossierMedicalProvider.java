package central.services;

import central.data.DaoProvider;
import central.dto.*;
import central.mapper.DossierMedicalMapper;
import central.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DossierMedicalProvider {



  public static Optional<DossierMedical> getDossier(int dossierId){
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
      return Optional.of(dossier);
    }
    catch(SQLException e){
      return Optional.of(null);
    }
  }

}
