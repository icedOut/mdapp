package central.mapper;

import central.dto.DTOEtablissementSante;
import central.models.EtablissementSante;

public class EtablisementMapper {

  public static EtablissementSante mapFromDto(DTOEtablissementSante dto){
    EtablissementSante etablissement = new EtablissementSante();
    etablissement.nom = dto.nom;
    etablissement.id = dto.id;
    return etablissement;
  }


  public static DTOEtablissementSante mapToDto(EtablissementSante etablissement){
    DTOEtablissementSante dto = new DTOEtablissementSante();
    dto.id = etablissement.id;
    dto.nom = etablissement.nom;
    return dto;
  }
}
