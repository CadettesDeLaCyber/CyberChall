package com.cadettesdelacyber.CyberChall.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cadettesdelacyber.CyberChall.services.SessionTemporaireService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionTemporaireService sessionTemporaireService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Utilisation des méthodes de SessionService pour obtenir le rôle et le username depuis la session
        String role = sessionTemporaireService.getRoleByToken(request);
        String username = sessionTemporaireService.getUsernameByToken(request);

        // Si le rôle et le username sont récupérés correctement, on les ajoute dans les attributs de la requête
        if (role != null && username != null) {
            request.setAttribute("estConnecte", true);
            request.setAttribute("role", role);
            request.setAttribute("username", username);
        } else {
            // Sinon, on considère que l'utilisateur est un invité et on ajoute des valeurs par défaut
            request.setAttribute("estConnecte", false);
            request.setAttribute("username", "Invité");
            request.setAttribute("role", "anonyme");
        }

        return true;
    }

    // Méthode pour extraire le token de la session à partir du cookie
    private String extractSessionToken(HttpServletRequest request) {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) { // Changement ici vers jakarta.servlet.http.Cookie
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    token = cookie.getValue();  // Récupère la valeur du cookie "SESSION_TOKEN"
                }
            }
        }
        return token;
    }
}