package medecin.models;


import medecin.utils.DateFormatter;

import javax.print.DocFlavor;
import java.util.Date;

public class AntecedentMedical {
  public int id;
  public int idDossier;
  public Date debutMaladie;
  public Date finMaladie;
  public String diagnostic;
  public Traitement traitement;

  public AntecedentMedical(){
    this.debutMaladie = new Date();
    this.diagnostic = "";
    this.traitement = new Traitement();
  }



  public String toString() {
    return new StringBuilder()
            .append(DateFormatter.dateToString(this.debutMaladie))
            .append(" : ")
            .append(this.diagnostic)
            .toString();
  }
}
