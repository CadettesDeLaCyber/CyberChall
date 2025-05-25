package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.services.AdminService;
import com.cadettesdelacyber.CyberChall.services.SessionTemporaireService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private SessionTemporaireService sessionTemporaireService;
    
    @Autowired
    private AdminService adminService;

    // Affichage page de connexion admin
    @GetMapping("/admin/connexion-admin")
    public String showAdminLoginPage() {
        return "admin/connexion-admin";  // Page de connexion admin
    }

    // Traitement connexion pour les admins
    @PostMapping("/admin/connexion-admin")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession sessionHttp,
                             Model model) {
        System.out.println("🔐 Tentative de login ADMIN avec : " + username + " / " + password);

        // Vérification de l'authentification dans le service
        boolean isAuthenticated = sessionTemporaireService.authenticate(username, password);

        if (isAuthenticated) {
            Admin admin = adminService.findByUsername(username);  // Récupère l'objet admin
            System.out.println("✅ Connexion réussie pour l'admin : " + username);

            // Sauvegarde l'objet admin dans la session HTTP
            sessionHttp.setAttribute("admin", admin);  // <-- Ici

            // autres informations dans la session HTTP
            sessionHttp.setAttribute("estConnecte", true);
            sessionHttp.setAttribute("username", username);

            // Log pour vérifier que l'admin est bien dans la session
            System.out.println("Admin dans la session: " + admin);
     
            // Redirection vers la page d'accueil admin
            return "redirect:/admin/accueil-admin";
        } else {
            System.out.println("❌ Échec de connexion - identifiants incorrects");
            model.addAttribute("error", "Identifiants incorrects");
            return "admin/connexion-admin";  // Retour à la page de connexion admin avec un message d'erreur
        }
    }
}