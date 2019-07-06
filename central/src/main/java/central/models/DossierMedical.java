package central.models;


import java.util.ArrayList;
import java.util.List;


public class DossierMedical {

  public int id;
  public List<VisiteMedicale> visites;
  public List<AntecedentMedical> antecedents;
  public Patient patient;

  public DossierMedical(){
    this.visites = new ArrayList<>();
    this.antecedents = new ArrayList<>();

  }





}
