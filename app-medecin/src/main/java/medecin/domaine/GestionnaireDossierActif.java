package medecin.domaine;

import medecin.models.DossierMedical;
import medecin.utils.ClientHTTP;

public class GestionnaireDossierActif {

  public DossierMedical chargerUnDossier(String codeRAMQ){

    return ClientHTTP.getDossier(codeRAMQ);
  }

  public DossierMedical enregistrerModification(DossierMedical modification){
    return ClientHTTP.envoyerModification(modification);
  }

  public DossierMedical annulerModification(DossierMedical modification){
    return ClientHTTP.annulerModification(modification);
  }

}
