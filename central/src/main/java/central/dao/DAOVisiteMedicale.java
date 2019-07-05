package central.dao;


import central.dto.DTOVisiteMedicale;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOVisiteMedicale extends BaseDaoImpl<DTOVisiteMedicale, String> {



  public DAOVisiteMedicale(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOVisiteMedicale.class);
  }


}



