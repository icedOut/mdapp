package central.dao;


import central.data.DaoProvider;
import central.dto.DTOPatient;
import central.dto.DTOUtilisateur;
import central.mapper.UtilisateurMapper;
import central.models.ReponseAuthentification;
import central.models.Utilisateur;
import central.services.ServiceGestionSession;
import central.utils.Hasher;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOPatient extends BaseDaoImpl<DTOPatient, String> {



  public DAOPatient(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOPatient.class);
  }




}



