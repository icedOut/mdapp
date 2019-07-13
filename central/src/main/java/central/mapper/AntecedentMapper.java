package central.mapper;

import central.dto.DTOAntecedentMedical;
import central.models.AntecedentMedical;
import central.models.DossierMedical;
import central.utils.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class AntecedentMapper {



  public static List<AntecedentMedical> getAntecedentsFromDtos(List<DTOAntecedentMedical> antecedentsDB) {
    return antecedentsDB.stream().map(a -> getAntecendentFromDto(a)).collect(Collectors.toList());
  }

  public static AntecedentMedical getAntecendentFromDto(DTOAntecedentMedical dbAntecedent) {
    AntecedentMedical ante = new AntecedentMedical();
    ante.diagnostic = dbAntecedent.diagnostic;
    ante.id = dbAntecedent.id;
    ante.traitement.medicament = dbAntecedent.medicament;
    ante.traitement.nomTraitement = dbAntecedent.nomTraitement;
    try {
      ante.debutMaladie = DateFormatter.stringToDate(dbAntecedent.debutMaladie);
      ante.debutMaladie = DateFormatter.stringToDate(dbAntecedent.debutMaladie);
      ante.finMaladie = DateFormatter.stringToDate(dbAntecedent.finMaladie);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return ante;
  }

  public static List<DTOAntecedentMedical> getAntecedentsDtosFromDossier(DossierMedical modif, int newId) {
    return modif.antecedents.stream().map(a -> {
      DTOAntecedentMedical dtoAnte = new DTOAntecedentMedical();
      dtoAnte.finMaladie = a.finMaladie == null ? null : DateFormatter.dateToString(a.finMaladie);
      dtoAnte.debutMaladie = DateFormatter.dateToString(a.debutMaladie);
      dtoAnte.diagnostic = a.diagnostic;
      dtoAnte.medicament = a.traitement.medicament;
      dtoAnte.nomTraitement = a.traitement.nomTraitement;
      dtoAnte.idDossier = newId;
      return dtoAnte;
    }).collect(Collectors.toList());
  }
}
