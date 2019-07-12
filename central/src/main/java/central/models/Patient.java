package central.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


public class Patient extends Utilisateur{

  public int id;
  public String nom;
  public String prenom;
  public String codeRAMQ;
  public Date dateNaissance;
  public String nas;
  public Genre genre;
  public Coordonnee coordonnee;
  public Parent parent1;
  public Parent parent2;

  public Patient(){
    this.coordonnee = new Coordonnee();
  }



}
