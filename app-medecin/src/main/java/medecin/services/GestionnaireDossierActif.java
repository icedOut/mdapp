package medecin.services;

import medecin.models.AntecedentMedical;
import medecin.models.DossierMedical;
import medecin.models.VisiteMedicale;
import medecin.utils.ClientHTTP;


public class GestionnaireDossierActif {


  private static DossierMedical dossierActif;
  private static DossierMedical modificationEnCours;

  private static long lastModificationTime = 0;

  public GestionnaireDossierActif(){
    this.lastModificationTime = System.currentTimeMillis();
  }

  public static DossierMedical chargerUnDossier(String codeRAMQ){
    String token = GestionnaireSession.getToken();
    dossierActif = ClientHTTP.getDossier(codeRAMQ, token);
    System.out.println("Le dossier actif est: ");
    System.out.println(dossierActif);
    return dossierActif;
  }


  public static DossierMedical ajouterAntecedent(AntecedentMedical antecedent){

    dossierActif.antecedents.add(antecedent);
    enregistrerModification(dossierActif);
    return dossierActif;
  }

  public static DossierMedical ajouterVisite(VisiteMedicale visite){

    dossierActif.visites.add(visite);
    enregistrerModification(dossierActif);
    return dossierActif;
  }


  public static DossierMedical enregistrerModification(DossierMedical modification){
    modificationEnCours = modification;
    if(System.currentTimeMillis() + 3000 > lastModificationTime){
      //lastModificationTime = System.currentTimeMillis();
      System.out.println("will save to db");
      String token = GestionnaireSession.getToken();
      DossierMedical nouvelEtat = ClientHTTP.envoyerModification(modificationEnCours, token);
      if(nouvelEtat != null){
        dossierActif = nouvelEtat;
      }
    }
    return dossierActif;
  }

  public static DossierMedical annulerDerniereModification(){
    String token = GestionnaireSession.getToken();
    DossierMedical nouvelEtat = ClientHTTP.annulerModification(dossierActif, token);
    if(nouvelEtat != null){
      dossierActif = nouvelEtat;
    }
    return dossierActif;
  }
}
