package central.mapper;

import central.dto.DTOPatient;
import central.models.Coordonnee;

public class CoordonneeMapper {
  public static Coordonnee getCoordonneeFromPatientDto(DTOPatient dtoPatient) {
    Coordonnee coords = new Coordonnee();
    coords.courriel = dtoPatient.courriel;
    coords.adresse = AddresseMapper.getAddresseFromPatientDto(dtoPatient);
    coords.telephone = TelephoneMapper.mapTelephone(dtoPatient);
    return coords;
  }
}
