package central.controllers;

import central.models.DemandeAuthentification;
import central.models.ReponseAuthentification;
import central.services.ServiceAuthentification;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class ControleurAuthentification {


    private static Gson g = new Gson();

    @PostMapping(value = "/authentifier")
    @ResponseBody
    public ReponseAuthentification authentifierUsager(HttpServletResponse response,  @RequestBody DemandeAuthentification demande){

        ReponseAuthentification authResponse = ServiceAuthentification.authentifier(demande);
        response.addCookie(new Cookie("ramq_token", authResponse.token));
        return authResponse;
    }

    @PutMapping(value = "/deconnexion")
    @ResponseBody
    public void deconnexion(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> tokenCookie = Arrays.stream(cookies).filter(c -> c.getName().compareTo("ramq_token") == 0).findAny();
        if(!tokenCookie.isPresent()){
            response.setStatus(400);
            return;
        }
        ServiceAuthentification.deconnecter(tokenCookie.get().getValue());
        response.setStatus(200);
        return;
    }

}