package central.dto;

import central.dao.DAOEtablissementSante;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ETABLISSEMENT_MEDICAL", daoClass = DAOEtablissementSante.class)
public class DTOEtablissementSante {

  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "nom")
  public String nom;
}
