package central.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AntecedentMedical {
  public int id;
  public int idDossier;

 //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date debutMaladie;

  //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date finMaladie;
  public String diagnostic;
  public Traitement traitement;

  public AntecedentMedical(){
    this.traitement = new Traitement();
  }

}
