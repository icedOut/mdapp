package medecin.utils;

import com.google.gson.Gson;
import medecin.models.DossierMedical;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHTTP {



  private static HttpClient client = null;
  private static Gson g = new Gson();

  private ClientHTTP(){

  }

  private static void connect(){
    client = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
  }


  public static DossierMedical getDossier(String codeRAMQ){
    if(client == null) connect();
    String port = Config.getConfig().getProperty("systeme_central.port");
    String host = Config.getConfig().getProperty("host_name");
    String url = Config.getConfig().getProperty("telecharger_dossier_url");
    String fullUrl = new StringBuilder()
            .append(host)
            .append(port)
            .append(url)
            .append(String.format("?codeRAMQ=%s", codeRAMQ)).toString();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(fullUrl))
            .header("Content-Type", "application/json")
            .GET()
            .build();
    try{
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if(response.statusCode() != 200) return null;
      DossierMedical dossier = g.fromJson(response.body(), DossierMedical.class);
      return dossier;
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return null;
  }




  public static DossierMedical envoyerModification(DossierMedical modification) {
    try{
      String url = preparerModificationUrl();
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(g.toJson(modification)))
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if(response.statusCode() != 200) return null;
      DossierMedical dossierModifie = g.fromJson(response.body(), DossierMedical.class);
      return dossierModifie;
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return null;
  }


  public static DossierMedical annulerModification(DossierMedical modification){
    // TODO
    return modification;
  }

  private static String preparerModificationUrl(){
    if(client == null) connect();
    String port = Config.getConfig().getProperty("systeme_central.port");
    String host = Config.getConfig().getProperty("host_name");
    String url = Config.getConfig().getProperty("modification_url");
    return new StringBuilder()
            .append(host)
            .append(port)
            .append(url)
            .toString();
  }


}
