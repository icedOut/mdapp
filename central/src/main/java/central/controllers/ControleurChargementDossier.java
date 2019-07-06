package central.controllers;

import central.models.DossierMedical;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static central.utils.DossierMedicalProvider.*;

@Controller
public class ControleurChargementDossier {

    @GetMapping(value = "/dossier")
    @ResponseBody
    public ResponseEntity telechargerDossierPatient(@RequestParam int id){
        Optional<DossierMedical> dossier = getDossier(id);
        if(dossier.isPresent()){
            return ResponseEntity.status(200).body(dossier.get());
        }
        return ResponseEntity.status(400).body("sorry");

    }

}