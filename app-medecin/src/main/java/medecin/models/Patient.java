package medecin.models;

import java.util.Date;


public class Patient implements Utilisateur{

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
