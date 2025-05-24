package com.cadettesdelacyber.CyberChall.controllers;

import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // 1️⃣ Affichage global : modules + formulaire d'ajout
    @GetMapping("")
    public String afficherModules(Model model) {
        List<Module> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);
        model.addAttribute("nouveauModule", new Module());
        return "modules/list-modules"; // Page unique
    }

    // 2️⃣ Création d’un nouveau module
    @PostMapping("/create")
    public String createModule(@ModelAttribute("nouveauModule") Module module) {
        moduleService.createModule(module);
        return "redirect:/modules";
    }

    // 3️⃣ Modifier un module existant
    @PostMapping("/edit/{id}")
    public String updateModule(@PathVariable Long id, @ModelAttribute Module updatedModule) {
        moduleService.updateModule(id, updatedModule);
        return "redirect:/modules";
    }

    // 4️⃣ Supprimer un module complet
    @PostMapping("/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return "redirect:/modules";
    }

    // 5️⃣ Supprimer un sous-module spécifique d’un module
    @PostMapping("/remove-submodule/{moduleId}")
    public String removeSousModule(@PathVariable Long moduleId, @RequestParam int index) {
        moduleService.removeSousModuleByIndex(moduleId, index);
        return "redirect:/modules";
    }

    // (Optionnel) Ajouter un sous-module à un module
    @PostMapping("/add-submodule/{moduleId}")
    public String addSousModule(@PathVariable Long moduleId, @RequestParam String sousModule) {
        moduleService.addSousModule(moduleId, sousModule);
        return "redirect:/modules";
    }
    
    
}