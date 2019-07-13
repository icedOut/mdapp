package medecin.models;


import medecin.utils.Config;

import java.util.Properties;

public class EtablissementSante {

  public int id;
  public String nom;

  public EtablissementSante(){
    this.nom = Config.getConfig().getProperty("nom_etablissement");
    this.id = 2;
    System.out.println(this.nom);
  }
}
