package medecin.models;

import medecin.utils.DateFormatter;

import java.util.Date;

public class VisiteMedicale {




  public VisiteMedicale(){
    this.notes = "";
    this.resume = "";
    this.diagnostic = "";
    this.traitement = new Traitement();
    this.etablissement = new EtablissementSante();
    this.dateVisite = new Date();
  }

  public int id;
  public Date dateVisite;
  public String notes;
  public String resume;
  public String diagnostic;
  public Traitement traitement;
  public EtablissementSante etablissement;




  @Override
  public String toString(){
    return new StringBuilder()
            .append(DateFormatter.dateToString(this.dateVisite))
            .append(" : ")
            .append(this.etablissement.nom)
            .toString();
  }

}
