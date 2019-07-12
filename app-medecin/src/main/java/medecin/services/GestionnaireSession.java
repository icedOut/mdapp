package medecin.services;

public class GestionnaireSession {


  private String token;
  private static GestionnaireSession instance = new GestionnaireSession();

  private GestionnaireSession(){

  }



  public static String getToken (){
    return instance.token;
  }


  public static void setToken(String token){
    instance.token = token;
  }
}
