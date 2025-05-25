package com.cadettesdelacyber.CyberChall.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;
import com.cadettesdelacyber.CyberChall.repositories.SessionTemporaireRepository;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SessionTemporaireService;
import com.cadettesdelacyber.CyberChall.utils.QrCodeUtils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionTemporaireController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private SessionTemporaireService sessionTemporaireService;

    @Autowired
    private SessionTemporaireRepository sessionTemporaireRepository;

    // 🔹 Page de gestion des sessions
    @GetMapping("/session")
    public String afficherSessions(Model model, HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin"); // On récupère l'admin depuis la session
        if (admin == null) {
            return "redirect:/"; // Si l'admin n'est pas connecté, rediriger vers la page d'accueil
        }

        // ✅ Récupérer tous les modules pour afficher dans le formulaire
        List<Module> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);

        // ✅ Récupérer les sessions créées par cet admin
        List<SessionTemporaire> sessions = sessionTemporaireService.getSessionsParAdmin(admin);

        for (SessionTemporaire sessionTemp : sessions) {
            String url = "http://localhost:4040/" + sessionTemp.getToken();
            String qrCodeBase64 = null;
            try {
                qrCodeBase64 = QrCodeUtils.generateQRCodeBase64(url, 200, 200); // Générer un QR code pour la session
            } catch (Exception e) {
                e.printStackTrace();
            }
            sessionTemp.setQrCodeBase64(qrCodeBase64); // Ajouter le QR code à la session
        }

        //model.addAttribute("sessions", sessions); // Ajouter la liste des sessions à afficher
        model.addAttribute("sessionTemporaires", sessions);

        return "session/session"; // Afficher la page de gestion des sessions
    }

    // 🔸 Création d’une session temporaire avec des modules sélectionnés
    @PostMapping("/session") 
    public String creerSession(@RequestParam List<Long> moduleIds, HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin"); // Récupérer l'admin depuis la session
        if (admin == null) {
            return "redirect:/"; // Si l'admin n'est pas connecté, rediriger vers la page de login
        }
        
     // ✅ LOG 1 : Affiche les IDs reçus du formulaire
        System.out.println("📥 IDs de modules reçus : " + moduleIds);

        // ✅ Récupérer les modules sélectionnés
        List<Module> modules = moduleService.getModuleByIds(moduleIds);

     // ✅ LOG 2 : Vérifie les modules récupérés
        System.out.println("📦 Modules récupérés depuis la base :");
        for (Module m : modules) {
            System.out.println(" - " + m.getNom());
        }
        // ✅ Créer la session temporaire
        if (modules.size() < 2 || modules.size() > 4) {
            throw new IllegalArgumentException("La session doit contenir entre 2 et 4 modules.");
        }

        SessionTemporaire sessionTemporaire = new SessionTemporaire();
        sessionTemporaire.setToken(UUID.randomUUID().toString()); // Générer un token unique pour la session
        sessionTemporaire.setDateCreation(LocalDateTime.now());
        sessionTemporaire.setDuree(1); // Expiration après 1 mois
        sessionTemporaire.setModules(modules); // Associer les modules à la session
        sessionTemporaire.setAdmin(admin); // Lier cette session à l'admin

        sessionTemporaireRepository.save(sessionTemporaire); // Sauvegarder la session en base de données
        
        // ✅ LOG 3 : Confirmation de sauvegarde
        System.out.println("✅ Session créée avec ID : " + sessionTemporaire.getId());
        System.out.println("📌 Modules associés à la session :");
        for (Module m : sessionTemporaire.getModules()) {
            System.out.println(" - " + m.getNom());
        }
        // Rediriger vers la page de gestion des sessions avec les sessions mises à jour
        return "redirect:/session/session"; // Rediriger après la création
    }

    // 🎯 Accès via lien public/token
 // Accès via lien public/token
    @GetMapping("/{token}")
    public String accederSession(@PathVariable String token, @RequestParam List<Long> modules, Model model) {
        Optional<SessionTemporaire> sessionTempOpt = sessionTemporaireService.getSessionParToken(token);

        //a implemenetr plus tard
        /*
        if (sessionTempOpt.isEmpty() || sessionTempOpt.get().getDateExpiration().isBefore(LocalDateTime.now())) {
            return "redirect:/session/session"; // Si la session est expirée ou introuvable, rediriger vers la page de gestion des sessions
        } */

        SessionTemporaire sessionTemp = sessionTempOpt.get();

        // Filtrer les modules selon les paramètres de l'URL
        List<Module> selectedModules = moduleService.getModuleByIds(modules); // Récupérer les modules par leurs IDs

        model.addAttribute("modules", selectedModules); // Passer uniquement les modules sélectionnés à la vue
        model.addAttribute("isSessionMode", true); // Indiquer que l'on est en mode session temporaire
        model.addAttribute("estConnecte", false); // Indiquer que l'utilisateur n'est pas connecté (mode invité, par exemple)

        // Générer l'URL pour accéder à la session
        StringBuilder url = new StringBuilder("http://localhost:4040/admin/accueil-admin?modules=");
        for (Module module : selectedModules) {
            url.append(module.getId()).append("&modules=");
        }
        // Supprimer le dernier "&modules="
        if (url.length() > 0) {
            url.setLength(url.length() - "&modules=".length());
        }

        model.addAttribute("urlSession", url.toString());

        // Générer le QR code pour la session
        String qrCodeBase64 = null;
        try {
            qrCodeBase64 = QrCodeUtils.generateQRCodeBase64(url.toString(), 200, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("qrCodeBase64", qrCodeBase64); // Passer le QR code à la vue

        return "session/accueil-temporaire"; // Afficher la page d'accueil temporaire pour cette session
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerSession(@PathVariable Long id, HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin == null) {
            return "redirect:/";
        }

        sessionTemporaireService.supprimerParId(id);
        return "redirect:/session/session";
    }

    
 // ======================
    // 4. Statistiques de sessions
    // ======================
    @GetMapping("/session-statistic")
    public String showSessionStatistics(Model model) {
    	return "session/session-statistic";
    }

}