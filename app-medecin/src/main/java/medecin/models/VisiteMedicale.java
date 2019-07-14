package medecin.models;

import medecin.utils.DateFormatter;

import java.util.Date;

public class VisiteMedicale implements VisitePrototype{






  public int id;
  public Date dateVisite;
  public String notes;
  public String resume;
  public String diagnostic;
  public Traitement traitement;
  public EtablissementSante etablissement;


  public VisiteMedicale(String nomEtablissement, int idOfEtablissement){
    this.notes = "";
    this.resume = "";
    this.diagnostic = "";
    this.traitement = new Traitement();
    this.etablissement = new EtablissementSante(nomEtablissement, idOfEtablissement);
    this.dateVisite = new Date();
  }

  @Override
  public String toString(){
    return new StringBuilder()
            .append(DateFormatter.dateToString(this.dateVisite))
            .append(" : ")
            .append(this.etablissement.nom)
            .toString();
  }

  @Override
  public VisiteMedicale clone() {
    return new VisiteMedicale(this.etablissement.nom, this.etablissement.id);
  }
}
