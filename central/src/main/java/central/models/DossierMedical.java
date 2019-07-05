package central.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DossierMedical {

  public int id;

  public List<DossierMedical> modifications;
  public List<VisiteMedicale> visites;
  public List<AntecedentMedical> antecedents;

  public DossierMedical(){
    this.modifications = new ArrayList<DossierMedical>();
    this.visites = new ArrayList<VisiteMedicale>();
    this.antecedents = new ArrayList<AntecedentMedical>();

  }





}
