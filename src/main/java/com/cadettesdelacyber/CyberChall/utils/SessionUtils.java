package com.cadettesdelacyber.CyberChall.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

import com.cadettesdelacyber.CyberChall.models.Admin;

public class SessionUtils {

	public static void setSessionAndCookie(Admin admin, HttpServletResponse response, HttpSession httpSession) {
		String token = UUID.randomUUID().toString(); // Génère un token unique

		// Stocker les infos de session côté serveur
		httpSession.setAttribute("SESSION_TOKEN", token);
		httpSession.setAttribute("estConnecte", true);
		httpSession.setAttribute("username", admin.getUsername());

		// Créer le cookie avec le token
		Cookie cookie = new Cookie(Constants.SESSION_COOKIE_NAME, token);
		cookie.setPath("/");
		cookie.setMaxAge(Constants.COOKIE_EXPIRATION_TIME);
		response.addCookie(cookie);
	}
}