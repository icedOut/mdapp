package central.dao;


import central.dto.DTOPatient;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOPatient extends BaseDaoImpl<DTOPatient, String> {



  public DAOPatient(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOPatient.class);
  }


}



