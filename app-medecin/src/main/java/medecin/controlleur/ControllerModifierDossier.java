package medecin.controlleur;

import medecin.models.AntecedentMedical;
import medecin.models.DossierMedical;
import medecin.models.VisiteMedicale;
import medecin.services.GestionnaireDossierActif;

public class ControllerModifierDossier {



  public static DossierMedical ajouterVisite(){
    DossierMedical dossierModifie = GestionnaireDossierActif.ajouterVisite();
    return dossierModifie;
  }

  public static DossierMedical ajouterAntecedent(){
    System.out.println("add ante");
    DossierMedical dossierModifie = GestionnaireDossierActif.ajouterAntecedent();
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
