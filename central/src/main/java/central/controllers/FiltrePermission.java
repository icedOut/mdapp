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
@Order(Ordered.LOWEST_PRECEDENCE)
public class FiltrePermission extends OncePerRequestFilter {



  @Override
  public void doFilterInternal(HttpServletRequest request,
                       HttpServletResponse response,
                       FilterChain filterChain) throws IOException, ServletException {

    String url = request.getRequestURL().toString();
    if(url.contains("authentification")){
      filterChain.doFilter(request, response);
      return;
    }
    Cookie[] cookies = request.getCookies();
    Optional<Cookie> tokenCookie = Arrays.stream(cookies).filter(c -> c.getName().compareTo("ramq_token") == 0).findAny();
    if(tokenCookie.isPresent()) {

      Session session = ServiceGestionSession.obtenirSession(tokenCookie.get().getValue());
      if(session == null){

        response.setStatus(400);
        response.getWriter().write("Vous n'avez pas les permissions");
        return;

      }
      boolean permissionAccepted = ServicePermission.verifierUsagerPossedePermission(session.codeUsager, TypePermission.MODIFIER_DOSSIER);
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
