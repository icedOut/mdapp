package central.mapper;

import central.dto.DTOEtablissementSante;
import central.dto.DTOVisiteMedicale;
import central.models.AntecedentMedical;
import central.models.EtablissementSante;
import central.models.VisiteMedicale;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


  public static List<DTOEtablissementSante> getAllEtablissementDTOFromVisites(List<VisiteMedicale> visites){
    return visites.stream().map(v -> {
      DTOEtablissementSante e = new DTOEtablissementSante();
      e.nom = v.etablissement.nom;
      return e;
    }).collect(Collectors.toCollection(ArrayList::new));
  }




}
