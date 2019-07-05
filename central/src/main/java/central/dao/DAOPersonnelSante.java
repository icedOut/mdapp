package central.dao;


import central.dto.DTOPersonnelSante;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOPersonnelSante extends BaseDaoImpl<DTOPersonnelSante, String> {



  public DAOPersonnelSante(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOPersonnelSante.class);
  }


}



