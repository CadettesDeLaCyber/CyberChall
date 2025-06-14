package com.cadettesdelacyber.CyberChall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;
import com.cadettesdelacyber.CyberChall.services.SessionTemporaireService;
import com.cadettesdelacyber.CyberChall.utils.QrCodeUtils;
import com.cadettesdelacyber.CyberChall.services.AdminService;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    @Autowired
    private SessionTemporaireService sessionTemporaireService;

    @Autowired
    private AdminService adminService;
    

    @GetMapping("/admin/account")
    public String afficherCompte(HttpSession sessionHttp, Model model) {
        Admin admin = (Admin) sessionHttp.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/connexion-admin";
        }

        List<SessionTemporaire> sessionTemporaires = sessionTemporaireService.getSessionsParAdmin(admin);
        model.addAttribute("sessionTemporaires", sessionTemporaires);

        for (SessionTemporaire s : sessionTemporaires) {
            // ✅ Générer le lien avec les modules sélectionnés
            String url = s.getModules().stream()
                .map(Module::getId)
                .map(id -> "modules=" + id)
                .collect(Collectors.joining("&", "http://localhost:4040/session/accueil-temporaire?", ""));
            
            s.setUrlModules(url); // pour affichage

            // ✅ Générer le QR code avec ce même lien
            try {
                String qrBase64 = QrCodeUtils.generateQRCodeBase64(url, 200, 200);
                s.setQrCodeBase64(qrBase64);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("admin", admin);
        return "admin/account";
    }


    // GET - Afficher le formulaire de création de compte
    @GetMapping("/admin/create-account")
    public String showCreateAccount() {
        return "admin/create-account";
    }

    // POST - Traiter le formulaire de création ( EVOLUTION : A FAIRE AVEC EMAIL PLUTOT QUE USERNAME)
    @PostMapping("admin/create-account")
    public String createAccount(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        if (adminService.findByUsername(username) != null) {
            model.addAttribute("error", "Ce nom d'utilisateur existe déjà.");
            return "admin/create-account";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password); // le service encode le mot de passe

        adminService.saveAdmin(admin); // méthode qui encode et enregistre
        System.out.println(admin + "sauvegardé");

        return "redirect:/";
    }
}