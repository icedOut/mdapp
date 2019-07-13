package medecin.controlleur;

import medecin.models.AntecedentMedical;
import medecin.models.DossierMedical;
import medecin.models.VisiteMedicale;
import medecin.services.GestionnaireDossierActif;

public class ControllerModifierDossier {



  public static DossierMedical ajouterVisite(VisiteMedicale visite){
    DossierMedical dossierModifie = GestionnaireDossierActif.ajouterVisite(visite);
    return dossierModifie;
  }

  public static DossierMedical ajouterAntecedent(AntecedentMedical antecedent){
    DossierMedical dossierModifie = GestionnaireDossierActif.ajouterAntecedent(antecedent);
    return dossierModifie;
  }

  public static DossierMedical modifierDossier(DossierMedical dossierModifier){
    DossierMedical dossierModifie = GestionnaireDossierActif.enregistrerModification(dossierModifier);
    return dossierModifie;
  }

  public static DossierMedical annnulerDerniereModification(){
    DossierMedical dossierModifie = GestionnaireDossierActif.annulerDerniereModification();
    return dossierModifie;
  }



}