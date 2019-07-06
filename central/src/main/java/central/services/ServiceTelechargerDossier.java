package central.services;


import central.dto.DTOPatient;
import central.models.DossierMedical;
import central.utils.DossierMedicalProvider;
import central.utils.JDBCConnection;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceTelechargerDossier{
    
    public ServiceTelechargerDossier(){

    }




    public Optional<DossierMedical> telechargerDossierMedical(String codeRAMQ){
        try{
            DTOPatient dtoPatient = JDBCConnection
                    .getPatientDAO()
                    .queryForEq("codeRAMQ", codeRAMQ).get(0);
            return DossierMedicalProvider.getDossier(dtoPatient.etatDossier);

        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
        }
        catch(NullPointerException ne){
            System.out.println(ne.toString());
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return Optional.of(null);
    }
}