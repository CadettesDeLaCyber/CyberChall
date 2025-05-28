package com.cadettesdelacyber.CyberChall.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Module;

import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;
import com.cadettesdelacyber.CyberChall.repositories.AdminRepository;
import com.cadettesdelacyber.CyberChall.repositories.SessionTemporaireRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionTemporaireService {

	@Autowired
	private SessionTemporaireRepository sessionTemporaireRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// === Authentification ===
	public boolean authenticate(String username, String password) {
		Admin admin = adminRepository.findByUsername(username);
		if (admin != null) {
			return passwordEncoder.matches(password, admin.getPassword());

		}
		return false; // L'admin n'existe pas ou le mot de passe est incorrect
	}

	public List<SessionTemporaire> getSessionsParAdmin(Admin admin) {
		return sessionTemporaireRepository.findByAdmin(admin);
	}

	public SessionTemporaire creerSessionTemporaire(List<Module> modules, Admin admin) {
		if (modules.size() < 2 || modules.size() > 4) {
			throw new IllegalArgumentException("La session doit contenir entre 2 et 4 modules.");
		}

		SessionTemporaire session = new SessionTemporaire();
		session.setToken(UUID.randomUUID().toString());
		session.setDateCreation(LocalDateTime.now());
		session.setDuree(1); // <-- durée fixée à 1 mois ici
		session.setAdmin(admin);
		session.setModules(modules);

		return sessionTemporaireRepository.save(session);
	}

	public Optional<SessionTemporaire> getSessionParToken(String token) {
		return sessionTemporaireRepository.findByToken(token);
	}

	// 🔍 Récupérer toutes les sessions temporaires
	public List<SessionTemporaire> getToutesSessions() {
		return sessionTemporaireRepository.findAll();
	}

	public String getRoleByToken(HttpServletRequest request) {
		String token = extractSessionToken(request);
		if (token != null) {
			return (String) request.getSession().getAttribute("role");
		}
		return null;
	}

	public String getUsernameByToken(HttpServletRequest request) {
		String token = extractSessionToken(request);
		if (token != null) {
			return (String) request.getSession().getAttribute("username");
		}
		return null;
	}

	private String extractSessionToken(HttpServletRequest request) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("SESSION_TOKEN".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public void supprimerParId(Long id) {
		sessionTemporaireRepository.deleteById(id);
	}

	public SessionTemporaire findById(Long id) {
		return sessionTemporaireRepository.findById(id).orElse(null);
	}

}