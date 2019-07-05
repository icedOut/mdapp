package central.dao;


import central.dto.DTOUtilisateur;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOUtilisateur extends BaseDaoImpl<DTOUtilisateur, String> {



  public DAOUtilisateur(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOUtilisateur.class);
  }


}



