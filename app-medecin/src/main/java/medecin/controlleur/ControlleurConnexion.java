package medecin.controlleur;

import medecin.services.GestionnaireAuthentification;

public class ControlleurConnexion {




  public static boolean connexion(String codeUsager, String motDePasse){
    return GestionnaireAuthentification.authentifierUsager(motDePasse, codeUsager);
  }


  public static void deconnexion(){
    GestionnaireAuthentification.deconnexion();
  }
}
