package central.dto;

import central.dao.DAOAntecedentMedical;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ANTECEDENT_MEDICAL", daoClass = DAOAntecedentMedical.class)
public class DTOAntecedentMedical {

  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "idDossier")
  public int idDossier;

  @DatabaseField(columnName = "debutMaladie")
  public String debutMaladie;

  @DatabaseField(columnName = "finMaladie")
  public String finMaladie;

  @DatabaseField(columnName = "diagnostic")
  public String diagnostic;

  @DatabaseField(columnName = "nomTraitement")
  public String nomTraitement;

  @DatabaseField(columnName = "medicament")
  public String medicament;

}
