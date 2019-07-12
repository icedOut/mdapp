package central.dao;


import central.dto.DTOUtilisateur;
import central.mapper.UtilisateurMapper;
import central.models.Utilisateur;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;


public class DAOUtilisateur extends BaseDaoImpl<DTOUtilisateur, String> {



  public DAOUtilisateur(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, DTOUtilisateur.class);
  }



  public Utilisateur getUtilisateur(String codeUsager){
    try{

      DTOUtilisateur userDto = queryForId(codeUsager);
      if(userDto == null) return null;
      Utilisateur user = UtilisateurMapper.mapFromDto(userDto);
      return user;
    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
      return null;
    }
  }



}



