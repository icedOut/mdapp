package central.dto;


import central.dao.DAOVisiteMedicale;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "VISITE_MEDICALE", daoClass = DAOVisiteMedicale.class)
public class DTOVisiteMedicale {


  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "idDossier")
  public int idDossier;

  @DatabaseField(columnName = "dateVisite")
  public String dateVisite;

  @DatabaseField(columnName = "notes")
  public String notes;

  @DatabaseField(columnName = "resume")
  public String resume;

  @DatabaseField(columnName = "diagnostic")
  public String diagnostic;

  @DatabaseField(columnName = "nomTraitement")
  public String nomTraitement;

  @DatabaseField(columnName = "medicament")
  public String medicament;

  @DatabaseField(columnName = "idEtablissement")
  public int idEtablissement;
}
