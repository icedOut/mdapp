package central.dao;


import central.models.Session;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOSession extends BaseDaoImpl<Session, String> {



  public DAOSession(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, Session.class);
  }


}



