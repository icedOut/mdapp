package central.mapper;

import central.dto.*;
import central.models.*;
import central.utils.DateFormatter;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DossierMedicalMapper {




  public static DossierMedical dtoToDossier(DTODossierMedical dossier,
                                            List<DTOVisiteMedicale> visites,
                                            List<DTOAntecedentMedical> antecedents,
                                            DTOPatient patient) {
    DossierMedical dossierToCreate = new DossierMedical();
    dossierToCreate.antecedents = AntecedentMapper.getAntecedentsFromDtos(antecedents);
    dossierToCreate.visites = VisiteMedicaleMapper.getVisitesFromDtos(visites);
    dossierToCreate.patient = PatientMapper.getPatientFromDto(patient);
    dossierToCreate.id = dossier.id;
    return dossierToCreate;
  }

  public static DTODossierMedical mapToDTO(DossierMedical modif, int idPatient) throws SQLException {
    DTODossierMedical dtoDossier = new DTODossierMedical();
    dtoDossier.etatPrecedent = modif.id;
    dtoDossier.idPatient = idPatient;
    dtoDossier.dateModif = DateFormatter.dateToString(new Date());
    dtoDossier.id = modif.id;
    return dtoDossier;
  }


}