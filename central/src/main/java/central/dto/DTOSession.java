package central.dto;

import central.dao.DAOSession;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "SESSION", daoClass = DAOSession.class)
public class DTOSession {

  @DatabaseField(columnName = "id", generatedId = true)
  public int id;

  @DatabaseField(columnName = "idUsager")
  public String codeUsager;

  @DatabaseField(columnName = "token")
  public String token;

  @DatabaseField(columnName = "expiration")
  public Date expiration;




}
