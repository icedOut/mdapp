package central.dao;


import central.dto.DTODossierMedical;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAODossierMedical extends BaseDaoImpl<DTODossierMedical, String> {



  public DAODossierMedical(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTODossierMedical.class);
  }


}



