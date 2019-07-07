package medecin.models;

import java.util.Date;

public class VisiteMedicale {
  public int id;
  public Date dateVisite;
  public String notes;
  public String resume;
  public String diagnostic;
  public Traitement traitement;
  public EtablissementSante etablissement;

  public VisiteMedicale(){
    this.traitement = new Traitement();
    this.etablissement = new EtablissementSante();
  }

}
