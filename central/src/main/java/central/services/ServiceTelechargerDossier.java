package central.services;


import central.data.DaoProvider;
import central.dto.DTOPatient;
import central.models.DossierMedical;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceTelechargerDossier{
    
    public ServiceTelechargerDossier(){

    }




    public Optional<DossierMedical> telechargerDossierPatient(String codeRAMQ){
        return DaoProvider.getDossierDAO().obtenirDossier(codeRAMQ);
    }

  
}