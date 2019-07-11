package central.mapper;

import central.dto.DTOPatient;
import central.models.AdresseResidentielle;

public class AddresseMapper {

  public static AdresseResidentielle getAddresseFromPatientDto(DTOPatient dtoPatient) {
    AdresseResidentielle adr = new AdresseResidentielle();
    adr.codePostal = dtoPatient.codePostal;
    adr.nomRue = dtoPatient.nomRue;
    adr.numAppartement = dtoPatient.numAppartement;
    adr.numPorte = dtoPatient.numPorte;
    adr.ville = dtoPatient.ville;
    return adr;
  }

}
