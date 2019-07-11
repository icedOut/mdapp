package central.mapper;

import central.dto.DTOPatient;
import central.models.Coordonnee;
import services.central.TelephoneMapper;

public class CoordonneeMapper {
  public static Coordonnee getCoordonneeFromPatientDto(DTOPatient dtoPatient) {
    Coordonnee coords = new Coordonnee();
    coords.courriel = dtoPatient.courriel;
    coords.adresse = AddresseMapper.getAddresseFromPatientDto(dtoPatient);
    coords.telephone = TelephoneMapper.getTelephoneFromDto(dtoPatient);
    return coords;
  }
}
