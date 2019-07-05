package central.controllers;

import central.models.DemandeAnnulationModification;
import central.models.DossierMedical;
import central.services.ServiceModifierDossierPatient;
import central.utils.DossierMedicalProvider;
import central.utils.DossierMedicalUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ControleurModificationDossier {

    @PutMapping(value = "/dossier/modification")
    @ResponseBody
    public ResponseEntity modifierDossierPatient(@RequestBody DossierMedical modification, @CookieValue String token){

        //TODO NEED TO CHECK TOKEN
        int idOfNewModif = DossierMedicalUpdater.updateDossierMedical(modification);
        Optional<DossierMedical> newDossier = DossierMedicalProvider.getDossier(idOfNewModif);
        if(newDossier.isPresent()){
            return ResponseEntity.status(200).body(newDossier.get());
        }
        return ResponseEntity.status(200).body(null);

    }


    @DeleteMapping(value = "/dossier/modification")
    @ResponseBody
    public ResponseEntity<DossierMedical> annulerDerniereModification(@RequestBody DossierMedical modification, @CookieValue String token){

        //TODO NEED TO CHECK TOKEN
        ServiceModifierDossierPatient serviceModif = new ServiceModifierDossierPatient();
        DossierMedical dossierRestaurer = serviceModif.annulerDerniereModification(modification.id);
        return ResponseEntity.status(200).body(dossierRestaurer);
    }

}