package central.models;


import java.util.Date;

public class AntecedentMedical {
  public int id;
  public int idDossier;
  public Date debutMaladie;
  public Date finMaladie;
  public String diagnostic;
  public Traitement traitement;

  public AntecedentMedical(){
    this.traitement = new Traitement();
  }

}
