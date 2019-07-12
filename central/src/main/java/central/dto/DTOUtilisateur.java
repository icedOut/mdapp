package central.dto;

import central.dao.DAOUtilisateur;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UTILISATEUR", daoClass = DAOUtilisateur.class)
public class DTOUtilisateur {


  @DatabaseField(id = true, columnName = "codeUsager")
  public String codeUsager;

  @DatabaseField(columnName = "typePermission")
  public String typePermission;

  @DatabaseField(columnName = "hash")
  public String hash;

  @DatabaseField(columnName = "salt")
  public String salt;
}
