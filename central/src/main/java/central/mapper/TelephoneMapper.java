package central.mapper;

import central.dto.DTOPatient;
import central.models.NumeroTelephone;
import central.models.TypeTelephone;

public class TelephoneMapper {

  public static NumeroTelephone mapTelephone(DTOPatient dtoPatient) {
    NumeroTelephone tel = new NumeroTelephone();
    tel.numero = dtoPatient.numeroTelephone;
    tel.type = TypeTelephone.valueOf(dtoPatient.typeTelephone);
    return tel;
  }

}
