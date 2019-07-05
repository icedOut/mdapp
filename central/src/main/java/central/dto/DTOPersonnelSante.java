package central.dto;

import central.dao.DAOPersonnelSante;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PERSONNEL_SANTE", daoClass = DAOPersonnelSante.class)
public class DTOPersonnelSante {

  @DatabaseField(columnName = "idPersonnel", generatedId = true)
  public int idPersonnel;

  @DatabaseField(columnName = "codeUsager")
  public String codeUsager;
}
