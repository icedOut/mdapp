package central.controllers;

import central.services.ServiceGestionSession;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FiltreAuthentification extends OncePerRequestFilter {



  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {


    Cookie[] cookies = request.getCookies();
    Optional<Cookie> tokenCookie = Arrays
            .stream(cookies)
            .filter(c -> c.getName().compareTo("ramq_token") == 0)
            .findAny();

    if (tokenCookie.isPresent()) {

      String token = tokenCookie.get().getValue();
      boolean authentifier = ServiceGestionSession.verifierSessionActive(token);
      if (!authentifier) {
        response.setStatus(400);
        response.getWriter().write("Erreur d'authentification");
        return;
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }

}
