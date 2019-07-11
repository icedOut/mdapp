package central.services;


import central.models.DossierMedical;

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