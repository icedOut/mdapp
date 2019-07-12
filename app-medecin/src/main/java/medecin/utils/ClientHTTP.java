package medecin.utils;

import com.google.gson.Gson;
import medecin.models.DemandeAuthentification;
import medecin.models.DossierMedical;
import medecin.models.ReponseAuthentification;


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


  public static DossierMedical getDossier(String codeRAMQ, String token){
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
            .setHeader("Cookie", "ramq_token="+token)
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




  public static DossierMedical envoyerModification(DossierMedical modification, String token) {
    try{
      String url = "http://localhost:23000/modification";
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("Content-Type", "application/json")
              .setHeader("Cookie", "ramq_token="+token)
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


  public static DossierMedical annulerModification(DossierMedical modification, String token){
    try{
      String url = "http://localhost:23000/annulation";
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("Content-Type", "application/json")
              .setHeader("Cookie", "ramq_token="+token)
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


  public static String seConnecter(String codeUsager, String motDePasse){
    try{
      if(client == null) connect();
      DemandeAuthentification demande = new DemandeAuthentification();
      demande.codeUsager = codeUsager;
      demande.motDePasse = motDePasse;
      String url = "http://localhost:23000/authentification";
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(g.toJson(demande)))
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if(response.statusCode() != 200) return null;
      ReponseAuthentification reponse = g.fromJson(response.body(), ReponseAuthentification.class);
      return reponse.token;
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
    return null;
  }


  public static void seDeconnecter(String token){
    try{
      if(client == null) connect();
      String url = "http://localhost:23000/deconnexion";
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .header("Content-Type", "application/json")
              .setHeader("Cookie", "ramq_token="+token)
              .PUT(HttpRequest.BodyPublishers.ofString(g.toJson(token)))
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
  }




}
