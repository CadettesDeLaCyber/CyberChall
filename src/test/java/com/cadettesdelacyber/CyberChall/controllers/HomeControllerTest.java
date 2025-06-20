package com.cadettesdelacyber.CyberChall.controllers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.services.ModuleService;
import com.cadettesdelacyber.CyberChall.services.SousModuleService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.List;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	// On mock les services injectés dans le contrôleur
    @MockBean
    private ModuleService moduleService;

    @MockBean
    private SousModuleService sousModuleService;

    @Test
    public void testIndex_NotConnected_ShouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"));
    }
    
    @Test
    public void testIndex_Connected_ShouldRedirectToAdminAccueil() throws Exception {
        mockMvc.perform(get("/")
                .sessionAttr("estConnecte", true))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/admin/accueil-admin"));
    }
    
    // --- Tests pour /admin/accueil-admin ---

    @Test
    public void testAccueilAdmin_AsAdminConnected_ShouldReturnAllModules() throws Exception {
        // Préparation des données
        Module module = new Module(1L, "Cyberattaque", "cyber.jpg", "Description cyber", Collections.emptyList());
        List<Module> modules = List.of(module);

        // Mock du service
        when(moduleService.getAllModules()).thenReturn(modules);

        // Simuler une session avec un admin
        mockMvc.perform(get("/admin/accueil-admin")
                    .sessionAttr("admin", new Object()))  // Remplace par un vrai Admin si nécessaire
               .andExpect(status().isOk())
               .andExpect(view().name("admin/accueil-admin"))
               .andExpect(model().attributeExists("admin"))
               .andExpect(model().attribute("modules", modules))
               .andExpect(model().attribute("estConnecte", true));
    }
    
    @Test
    public void testAccueilAdmin_NoAdminNoSousModules_ShouldRedirectToIndex() throws Exception {
        mockMvc.perform(get("/admin/accueil-admin"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));
    }
    
    /*
     * 
     * @Test
    public void testAccueilAdmin_AsEleveAvecSousModules_ShouldReturnSousModules() throws Exception {
        // Préparer des SousModules fictifs
        SousModule sm1 = new SousModule(1L, "Phishing", "Lien1", null);
        SousModule sm2 = new SousModule(2L, "Malware", "Lien2", null);
        List<SousModule> sousModules = List.of(sm1, sm2);

        // Simuler la réponse du service
        when(sousModuleService.findSousModulesByIds(List.of(1L, 2L))).thenReturn(sousModules);

        // Requête avec paramètres sousModules (comme via QR code)
        mockMvc.perform(get("/admin/accueil-admin")
                .param("sousModules", "1", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/accueil-admin"))
                .andExpect(model().attribute("estConnecte", false))
                .andExpect(model().attribute("modules", sousModules))
                .andExpect(model().attribute("isSessionMode", true));
    }
     * 
     */

}
