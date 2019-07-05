package central.dao;


import central.dto.DTOAntecedentMedical;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOAntecedentMedical extends BaseDaoImpl<DTOAntecedentMedical, String> {



  public DAOAntecedentMedical(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOAntecedentMedical.class);
  }


}



