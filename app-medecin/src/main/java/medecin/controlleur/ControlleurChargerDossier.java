package medecin.controlleur;

import medecin.models.DossierMedical;
import medecin.services.GestionnaireDossierActif;

public class ControlleurChargerDossier {



  public static DossierMedical chargerDossier(String codeRAMQ){
    DossierMedical dossier = GestionnaireDossierActif.chargerUnDossier(codeRAMQ);
    return dossier;
  }



}
