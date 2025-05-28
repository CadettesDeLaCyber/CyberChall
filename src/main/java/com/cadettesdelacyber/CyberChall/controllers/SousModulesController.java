package com.cadettesdelacyber.CyberChall.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.services.SousModuleService;

@Controller
@RequestMapping("/modules")
public class SousModulesController {
	
	@Autowired
	private SousModuleService sousModuleService;
	
	// page d'accueil des sous-modules: modules/accueil-modules : modules/accueil-modules
	@GetMapping("/accueil-modules/{id}")
	public String afficherModuleDepuisSousModule(@PathVariable Long id, Model model) {
	    SousModule sousModule = sousModuleService.findById(id);

	    if (sousModule == null) {
	        return "redirect:/modules"; // Ou afficher une page d'erreur
	    }

	    model.addAttribute("sousModule", sousModule); // ✅ PAS juste sousModule.getImageUrl()
	    return "modules/accueil-modules";
	}
	
	// ============================================================
    // Section 1: Sous-modules Cyberattaques : Affichages ==> GET
    // ============================================================
    @GetMapping("/cyberattaque/exploitation-vulnerabilite")
    public String showExploitationVulnerabilitePage() {
        return "modules/cyberattaque/exploitation-vulnerabilite";  
    }
    
    @GetMapping("/cyberattaque/force-brute")
    public String showForceBrutePage() {
        return "modules/cyberattaque/force-brute";  
    }
    
    @GetMapping("/cyberattaque/types-cyberattaques")
    public String showTypesCyberattaque() {
        return "modules/cyberattaque/types-cyberattaques";
    }
    
    // =================================================================
    // Section 2: Sous-modules Protections des données : Affichages ==> GET
    // =================================================================
    @GetMapping("/protection/donnee-perso")
    public String showProtectionDonneePage() {
        return "modules/protection/donnee-perso";  
    }
    
    @GetMapping("/protection/rgpd")
    public String showRgpdPage() {
        return "modules/protection/rgpd";  
    }
    
    @GetMapping("/protection/tracking")
    public String showTrackingPage() {
        return "modules/protection/tracking";  
    }
    
    // =================================================================
    // Section 3: Sous-modules Réseaux : Affichages ==> GET
    // =================================================================
    @GetMapping("/reseau/algorithmes")
    public String showAlgorithmesPage() {
        return "modules/reseau/algorithmes";  
    }
    
    @GetMapping("/reseau/bots-fake")
    public String showBotsFakePage() {
        return "modules/reseau/bots-fake";  
    }
    
    @GetMapping("/reseau/cyber-harcelement")
    public String showcyberHarcelementPage() {
        return "modules/reseau/cyber-harcelement";  
    }
    
    @GetMapping("/reseau/deepfakes")
    public String showDeepfakesPage() {
        return "modules/reseau/deepfakes";  
    }
    
    @GetMapping("/reseau/e-reutation")
    public String showEreputationPage() {
        return "modules/reseau/e-reutation";  
    }
    
    @GetMapping("/reseau/propagande")
    public String showPropagandePage() {
        return "modules/reseau/propagande";  
    }
    
    @GetMapping("/reseau/responsabilite-numerique")
    public String showresponsabiliteNumeriquePage() {
        return "modules/reseau/responsabilite-numerique";  
    }
    
    // =================================================================
    // Section 4: Sous-modules Sécurité des appareils : Affichages ==> GET
    // =================================================================
    @GetMapping("/securite/bluetooth-iot")
    public String showBluetoothIotPage() {
        return "modules/securite/bluetooth-iot";  
    }
    
    @GetMapping("/securite/securite-appareils")
    public String showSecuriteAppareilPage() {
        return "modules/securite/securite-appareils";  
    }
    
    @GetMapping("/securite/wifi-puplic-vpn")
    public String showWifiPublicVpnPage() {
        return "modules/securite/wifi-puplic-vpn";  
    }
}
