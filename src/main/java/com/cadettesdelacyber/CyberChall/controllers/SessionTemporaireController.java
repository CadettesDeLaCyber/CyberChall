package com.cadettesdelacyber.CyberChall.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin == null) {
            return "redirect:/";
        }

        List<Module> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);

        List<SessionTemporaire> sessions = sessionTemporaireService.getSessionsParAdmin(admin);

        for (SessionTemporaire sessionTemp : sessions) {
            // ✅ Construction de l’URL avec les modules sélectionnés
            String url = sessionTemp.getModules().stream()
                .map(m -> "modules=" + m.getId())
                .collect(Collectors.joining("&", "http://localhost:4040/session/accueil-temporaire?", ""));
            sessionTemp.setUrlModules(url);

            // ✅ Génération du QR code basé sur l’URL
            try {
                String qrCodeBase64 = QrCodeUtils.generateQRCodeBase64(url, 200, 200);
                sessionTemp.setQrCodeBase64(qrCodeBase64);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("sessionTemporaires", sessions);
        return "session/session";
    }

    // 🔸 Création d’une session temporaire
    @PostMapping("/session")
    public String creerSession(@RequestParam List<Long> moduleIds, HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin == null) {
            return "redirect:/";
        }

        if (moduleIds.size() < 2 || moduleIds.size() > 4) {
            throw new IllegalArgumentException("La session doit contenir entre 2 et 4 modules.");
        }

        List<Module> modules = moduleService.getModuleByIds(moduleIds);

        SessionTemporaire sessionTemporaire = new SessionTemporaire();
        sessionTemporaire.setToken(UUID.randomUUID().toString());
        sessionTemporaire.setDateCreation(LocalDateTime.now());
        sessionTemporaire.setDuree(1);
        sessionTemporaire.setModules(modules);
        sessionTemporaire.setAdmin(admin);

        sessionTemporaireRepository.save(sessionTemporaire);

        return "redirect:/session/session";
    }

    // 🔸 Supprimer une session
    @GetMapping("/supprimer/{id}")
    public String supprimerSession(@PathVariable Long id, HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin == null) {
            return "redirect:/";
        }

        sessionTemporaireService.supprimerParId(id);
        return "redirect:/session/session";
    }

    // 🔸 Affichage accueil-temporaire avec modules en URL
    @GetMapping("/accueil-temporaire")
    public String accueilTemporaire(@RequestParam List<Long> modules, Model model) {
    	// 👉 Log des paramètres reçus
        System.out.println("Modules reçus en paramètre : " + modules);
        List<Module> modulesAAfficher = moduleService.getModuleByIds(modules);
        
     // 👉 Log du résultat de la requête
        System.out.println("➡️ Modules trouvés : " + modulesAAfficher.size());
        for (Module m : modulesAAfficher) {
            System.out.println("   🔹 " + m.getNom());
        }
        System.out.println("Modules récupérés depuis la BDD : " + modulesAAfficher);
        model.addAttribute("modules", modulesAAfficher);
        model.addAttribute("isSessionMode", true);
        return "accueil-temporaire";
    }


    // 🔹 Statistiques
    @GetMapping("/session-statistic")
    public String showSessionStatistics(Model model) {
        return "session/session-statistic";
    }
}