package com.cadettesdelacyber.CyberChall.controllers;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SousModuleService;

@Controller
public class HomeController {
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private SousModuleService sousModuleService;

    // ============================================================
    // Section 1: Page d'accueil --> connexion (si connecté --> accueil-admin
    // ============================================================

	@GetMapping("/")
	public String index(HttpSession sessionHttp) {
	    Boolean estConnecte = (Boolean) sessionHttp.getAttribute("estConnecte");

	    if (Boolean.TRUE.equals(estConnecte)) {
	        return "redirect:/admin/accueil-admin";
	    }

	    return "index";
	}

    // ============================================================
    // Section 2: Page d'accueil Admin :  IMPORTANT !! user externe accède a cette page par QRcode avec sous-modules selectionnés
    // ============================================================

	@GetMapping("/admin/accueil-admin")
	public String accueilAdmin(HttpSession session,
	                           @RequestParam(required = false) List<Long> sousModules,  // Utiliser Long si ce sont des IDs
	                           Model model) {
	    
	    Admin admin = (Admin) session.getAttribute("admin");
	    
	    if (admin != null) {
	        // Cas 1 : un admin est connecté → il voit tous les modules
	        model.addAttribute("estConnecte", true);
	        model.addAttribute("admin", admin);
	        model.addAttribute("modules", moduleService.getAllModules());  // Récupère tous les modules disponibles
	        model.addAttribute("isSessionMode", false);
	        System.out.println("Admin connecté, récupération de tous les modules");
	        
	    } else if (sousModules != null && !sousModules.isEmpty()) {
	        // Cas 2 : un élève accède via lien/QR → modules limités
	        List<SousModule> sousModulesList = sousModuleService.findSousModulesByIds(sousModules);
	        
	        // Log pour vérifier les sous-modules récupérés
	        System.out.println("Sous-modules récupérés : ");
	        for (SousModule sm : sousModulesList) {
	            System.out.println("ID: " + sm.getId() + ", Titre: " + sm.getTitre() + ", Lien: " + sm.getLink());
	        }
	        
	        model.addAttribute("estConnecte", false);
	        model.addAttribute("modules", sousModulesList);  // Passe la liste d'objets SousModule
	        model.addAttribute("isSessionMode", true);
	    } else {
	        // Accès non autorisé
	        return "redirect:/";
	    }
	    
	    return "admin/accueil-admin";  
	}

    // ============================================================
    // Section 3: Module cyberattaque
    // ============================================================
	
	//modules/cyberattaque
	@GetMapping("/module-cyberattaque")
    public String showModuleCyberattaque() {
        return "modules/cyberattaque/module-cyberattaque";
    }
	

    @GetMapping("/modules/cyberattaque/base-cyberattaque")
    public String showBaseCyberattaque() {
        return "modules/cyberattaque/base-cyberattaque";
    }
    
    @GetMapping("/modules/cyberattaque/quiz-cyberattaque")
    public String showQuizCyberattaques() {
        return "modules/cyberattaque/quiz-cyberattaque";
    }
    
    // ============================================================
    // Section 4: Module reseaux
    // ============================================================
    

    @GetMapping("/module-reseaux")
    public String showModuleReseaux() {
        return "modules/reseau/module-reseaux";
    }

    @GetMapping("/base-reseau")
    public String showBaseReseaux() {
        return "modules/reseau/base-reseaux";
    }
    
    @GetMapping("/quiz-reseau")
    public String showQuizzReseaux() {
        return "modules/reseau/quiz-reseau";
    }
    
    // ============================================================
    // Section 5: Module Securite
    // ============================================================
    
    @GetMapping("/module-securite")
    public String showModuleSecurite() {
        return "modules/securite/module-securite";
    }
    
    @GetMapping("/base-securite")
    public String showBaseSecurite() {
        return "modules/securite/base-securite";
    }
    
    @GetMapping("/quiz-securite")
    public String showQuizSecurite() {
        return "modules/securite/quiz-securite";
    }
    
    // ============================================================
    // Section 6: Challenge mot de passe
    // ============================================================

    @GetMapping("/modules/challenge")
    public String afficherChallengeMotdepasse() {
        return "modules/challenge";
    }

    // ============================================================
    // Section 7: Contact
    // ============================================================

    @GetMapping("/contact")
    public String afficherFormulaireContact() {
        return "contact";
    }
 
}