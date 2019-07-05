package central.dao;


import central.dto.DTOEtablissementSante;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOEtablissementSante extends BaseDaoImpl<DTOEtablissementSante, String> {



  public DAOEtablissementSante(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOEtablissementSante.class);
  }


}



