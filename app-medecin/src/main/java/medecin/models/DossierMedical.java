package medecin.models;


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


  @Override
  public String toString(){
    String dossier = new StringBuilder()
            .append("Nom du patient: " + this.patient.nom)
            .append(System.lineSeparator())
            .append("Prenom du patient: " + this.patient.prenom)
            .append(System.lineSeparator())
            .append("Nombre d'antecedent: " + this.antecedents.size())
            .append(System.lineSeparator())
            .append("Nombre de visite medicale: " + this.visites.size())
            .append(System.lineSeparator())
            .toString();
    return dossier;
  }





}
