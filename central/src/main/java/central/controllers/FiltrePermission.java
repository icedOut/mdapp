package central.controllers;


import central.models.Session;
import central.models.TypePermission;
import central.services.ServiceGestionSession;
import central.services.ServicePermission;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FiltrePermission extends OncePerRequestFilter {



  @Override
  public void doFilterInternal(HttpServletRequest request,
                       HttpServletResponse response,
                       FilterChain filterChain) throws IOException, ServletException {


    Cookie[] cookies = request.getCookies();
    Optional<Cookie> tokenCookie = Arrays.stream(cookies).filter(c -> c.getName().compareTo("ramq_token") == 0).findAny();
    if(tokenCookie.isPresent()) {

      Session session = ServiceGestionSession.getSession(tokenCookie.get().getValue());
      if(session == null){

        response.setStatus(400);
        response.getWriter().write("Erreur d'authentification");
        return;

      }
      boolean permissionAccepted = ServicePermission.verifierPermission(session.codeUsager, TypePermission.MODIFIER_DOSSIER);
      if (permissionAccepted) {
        filterChain.doFilter(request, response);
        return;
      }
    }

    response.setStatus(400);
    response.getWriter().write("Erreur d'authentification");
    return;



  }
}
