package central.dto;


import central.dao.DAODossierMedical;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


import java.util.Date;

@DatabaseTable(tableName = "DOSSIER_MEDICAL", daoClass = DAODossierMedical.class)
public class DTODossierMedical {



  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "idPatient")
  public int idPatient;

  @DatabaseField(columnName = "etatPrecedent")
  public int etatPrecedent;

  @DatabaseField(columnName = "dateModif")
  public String dateModif;


}
