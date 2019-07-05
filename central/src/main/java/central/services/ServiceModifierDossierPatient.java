package central.services;


import central.dto.DTODossierMedical;
import central.models.DossierMedical;
import central.utils.DossierMedicalProvider;
import central.utils.DossierMedicalUpdater;
import central.utils.JDBCConnection;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceModifierDossierPatient{
    public ServiceModifierDossierPatient(){
        
    }




    public DossierMedical annulerDerniereModification(int dossierId){
        try{
            DTODossierMedical dossierDto = JDBCConnection.getDossierDAO().queryForId(String.valueOf(dossierId));
            Optional<DossierMedical> ancienneVersion = DossierMedicalProvider.getDossier(dossierDto.etatPrecedent);
            if(!ancienneVersion.isPresent()) return null;

            int idDossierRestaurer = DossierMedicalUpdater.updateDossierMedical(ancienneVersion.get());
            Optional<DossierMedical> dossierRestaurer = DossierMedicalProvider.getDossier(dossierDto.etatPrecedent);
            if(!dossierRestaurer.isPresent()) return null;
            return dossierRestaurer.get();
        }
        catch(SQLException sqle){
            System.out.println(sqle.getCause());
            return null;
        }

    }
}