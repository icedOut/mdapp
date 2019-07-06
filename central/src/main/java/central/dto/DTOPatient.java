package central.dto;

import central.dao.DAOPatient;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "PATIENT", daoClass = DAOPatient.class)
public class DTOPatient{

  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "codeUsager")
  public int codeUsager;

  @DatabaseField(columnName = "nom")
  public String nom;

  @DatabaseField(columnName = "prenom")
  public String prenom;

  @DatabaseField(columnName = "codeRAMQ")
  public String codeRAMQ;

  @DatabaseField(columnName = "dateNaissance")
  public String dateNaissance;

  @DatabaseField(columnName = "nas")
  public String nas;

  @DatabaseField(columnName = "genre")
  public String genre;

  @DatabaseField(columnName = "courriel")
  public String courriel;

  @DatabaseField(columnName = "numeroTelephone")
  public String numeroTelephone;

  @DatabaseField(columnName = "typeTelephone")
  public String typeTelephone;

  @DatabaseField(columnName = "numPorte")
  public String numPorte;

  @DatabaseField(columnName = "numAppartement")
  public String numAppartement;

  @DatabaseField(columnName = "nomRue")
  public String nomRue;

  @DatabaseField(columnName = "ville")
  public String ville;

  @DatabaseField(columnName = "codePostal")
  public String codePostal;

  @DatabaseField(columnName = "nomParent1")
  public String nomParent1;

  @DatabaseField(columnName = "prenomParent1")
  public String prenomParent1;

  @DatabaseField(columnName = "nomParent2")
  public String nomParent2;

  @DatabaseField(columnName = "prenomParent2")
  public String prenomParent2;


}
