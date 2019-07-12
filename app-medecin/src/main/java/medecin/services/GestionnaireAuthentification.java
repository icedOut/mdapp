package medecin.services;

import medecin.utils.ClientHTTP;

public class GestionnaireAuthentification {




  public static boolean authentifierUsager(String motDePasse, String username){
    String token = ClientHTTP.seConnecter(username, motDePasse);
    if(token != null){
      System.out.println("Le token est: " + token);
      GestionnaireSession.setToken(token);
    }
    return token != null;
  }


  public static void deconnexion(){
    String token = GestionnaireSession.getToken();
    ClientHTTP.seDeconnecter(token);
  }
}
