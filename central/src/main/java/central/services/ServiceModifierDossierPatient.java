package central.services;


import central.data.DaoProvider;
import central.models.DossierMedical;

import java.util.Optional;

public class ServiceModifierDossierPatient{


    public ServiceModifierDossierPatient(){
        
    }


    public Optional<DossierMedical> annulerDerniereModification(DossierMedical dossier){
        return DaoProvider.getDossierDAO().obtenirEtatDossierPrecedent(dossier);
    }


    public Optional<DossierMedical> modifierDossierMedical(DossierMedical dossier){
        return DaoProvider.getDossierDAO().modifierDossier(dossier);
    }
}