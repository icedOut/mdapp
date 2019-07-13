package central.mapper;



import central.dto.DTOPatient;
import central.mapper.CoordonneeMapper;
import central.mapper.DossierMedicalMapper;
import central.models.Genre;
import central.models.Parent;
import central.models.Patient;
import central.utils.DateFormatter;

public class PatientMapper {


  public static Patient getPatientFromDto(DTOPatient dtoPatient) {
    Patient patient = new Patient();
    patient.codeRAMQ = dtoPatient.codeRAMQ;
    patient.genre = Genre.valueOf(dtoPatient.genre);
    patient.id = dtoPatient.id;
    patient.nas = dtoPatient.nas;
    patient.nom = dtoPatient.nom;
    patient.prenom = dtoPatient.prenom;
    patient.coordonnee = CoordonneeMapper.getCoordonneeFromPatientDto(dtoPatient);
    patient.parent1 = new Parent(dtoPatient.nomParent1, dtoPatient.prenomParent1);
    patient.parent2 = new Parent(dtoPatient.nomParent2, dtoPatient.prenomParent2);
    try {
      patient.dateNaissance = DateFormatter.stringToDate(dtoPatient.dateNaissance);

    } catch (Exception e) {

    }
    return patient;
  }



}
