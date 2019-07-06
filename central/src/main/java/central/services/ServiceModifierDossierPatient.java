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


    public Optional<DossierMedical> annulerDerniereModification(DossierMedical dossier){
        int nouvelId = DossierMedicalUpdater.annulerDerniereModification(dossier);
        return DossierMedicalProvider.getDossier(nouvelId);
    }


    public Optional<DossierMedical> modifierDossierMedical(DossierMedical dossier){
        int nouvelId = DossierMedicalUpdater.modifierDossierMedical(dossier);
        return DossierMedicalProvider.getDossier(nouvelId);
    }
}