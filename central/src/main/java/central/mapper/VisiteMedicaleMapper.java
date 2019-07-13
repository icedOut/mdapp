package central.mapper;

import central.data.DaoProvider;
import central.dto.DTOEtablissementSante;
import central.dto.DTOVisiteMedicale;
import central.models.DossierMedical;
import central.models.VisiteMedicale;
import central.services.EtablissementProvider;
import central.utils.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class VisiteMedicaleMapper {



  public static List<VisiteMedicale> getVisitesFromDtos(List<DTOVisiteMedicale> dbVisites) {
    return dbVisites.stream().map(v -> getVisiteFromDto(v)).collect(Collectors.toList());
  }


  private static VisiteMedicale getVisiteFromDto(DTOVisiteMedicale dbVisite) {
    VisiteMedicale visite = new VisiteMedicale();
    visite.id = dbVisite.id;
    visite.diagnostic = dbVisite.diagnostic;
    visite.notes = dbVisite.notes;
    visite.resume = dbVisite.resume;
    visite.traitement.nomTraitement = dbVisite.nomTraitement;
    visite.traitement.medicament = dbVisite.medicament;
    try {

      visite.dateVisite = DateFormatter.stringToDate(dbVisite.dateVisite);
      visite.etablissement.id = dbVisite.idEtablissement;
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return visite;
  }



  public static List<DTOVisiteMedicale> getVisitesDtoFromDossier(DossierMedical modif, int newId) {
    return modif.visites.stream().map(v -> {
      DTOVisiteMedicale dtoVisite = new DTOVisiteMedicale();
      dtoVisite.dateVisite = DateFormatter.dateToString(v.dateVisite);
      dtoVisite.idDossier = newId;
      dtoVisite.diagnostic = v.diagnostic;
      dtoVisite.notes = v.notes;
      dtoVisite.resume = v.resume;
      dtoVisite.medicament = v.traitement.medicament;
      dtoVisite.nomTraitement = v.traitement.nomTraitement;
      dtoVisite.idEtablissement = v.etablissement.id;
      return dtoVisite;
    }).collect(Collectors.toList());
  }
}
