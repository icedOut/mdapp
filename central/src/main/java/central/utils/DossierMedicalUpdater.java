package central.utils;

import central.dto.DTOAntecedentMedical;
import central.dto.DTODossierMedical;
import central.dto.DTOVisiteMedicale;
import central.models.DossierMedical;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DossierMedicalUpdater {

  static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  // Retourne le ID du nouveau dossier
  public static int updateDossierMedical(DossierMedical modification){
    try{

      int idPatient = obtenirIdPatient(modification);
      DTODossierMedical dtoDossier = mapToDTO(modification, idPatient);

      int idNouvelleModification = creerDossierDansBd(dtoDossier);
      List<DTOAntecedentMedical> dtoAntecedents = convertirAntecedentVersDto(modification, dtoDossier.id);
      for(int i = 0; i < dtoAntecedents.size(); ++i){
        addAntecedentToDatabase(dtoAntecedents.get(i));
      }

      List<DTOVisiteMedicale> dtoVisites = convertirVisitesVersDto(modification, dtoDossier.id);
      for(int i = 0; i < dtoVisites.size(); ++i){
        creerVisiteDansBd(dtoVisites.get(i));
      }

      return idNouvelleModification;
    }
    catch(SQLException e){
      // Si une erreur, on retourne un ID négatif pour indiquer que le dossier n'a pas été crée
      return -1;
    }
  }



  private static int obtenirIdPatient(DossierMedical modification) throws SQLException{
    return JDBCConnection.getDossierDAO().queryForId(String.valueOf(modification.id)).idPatient;
  }


  private static DTODossierMedical mapToDTO(DossierMedical modif, int idPatient) throws SQLException {
    DTODossierMedical dtoDossier = new DTODossierMedical();
    dtoDossier.etatPrecedent = modif.id;
    dtoDossier.idPatient = idPatient;
    dtoDossier.dateModif = dateFormater.format(new Date());
    dtoDossier.id = modif.id;
    return dtoDossier;
  }


  private static List<DTOAntecedentMedical> convertirAntecedentVersDto(DossierMedical modif, int newId) {
    return modif.antecedents.stream().map(a -> {
      DTOAntecedentMedical dtoAnte = new DTOAntecedentMedical();
      dtoAnte.finMaladie = dateFormater.format(a.finMaladie);
      dtoAnte.debutMaladie = dateFormater.format(a.debutMaladie);
      dtoAnte.diagnostic = a.diagnostic;
      dtoAnte.medicament = a.traitement.medicament;
      dtoAnte.nomTraitement = a.traitement.nomTraitement;
      dtoAnte.idDossier = newId;
      return dtoAnte;
    }).collect(Collectors.toList());
  }

  private static void addAntecedentToDatabase(DTOAntecedentMedical antecedent) throws  SQLException{
    JDBCConnection.getAntecedentDAO().create(antecedent);
  }

  private static List<DTOVisiteMedicale> convertirVisitesVersDto(DossierMedical modif, int newId) {
    return modif.visites.stream().map(v -> {
      DTOVisiteMedicale dtoVisite = new DTOVisiteMedicale();
      dtoVisite.dateVisite = dateFormater.format(v.dateVisite);
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

  private static void creerVisiteDansBd(DTOVisiteMedicale visite) throws  SQLException{
    JDBCConnection.getVisiteDAO().create(visite);
  }

  private static int creerDossierDansBd(DTODossierMedical dossier) throws SQLException{
    JDBCConnection.getDossierDAO().create(dossier);
    return dossier.id;
  }


}
