package central.services;

import central.data.DaoProvider;
import central.dto.DTOEtablissementSante;
import central.mapper.EtablisementMapper;
import central.models.EtablissementSante;

import java.sql.SQLException;

public class EtablissementProvider {



  public static EtablissementSante getEtablissement(String id){
    try{
      DTOEtablissementSante etab = DaoProvider
              .getEtablissementDAO()
              .queryForId(String.valueOf(id));
      return EtablisementMapper.mapFromDto(etab);
    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
      return null;
    }

  }
}
