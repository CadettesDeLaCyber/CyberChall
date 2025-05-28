package com.cadettesdelacyber.CyberChall.services;

import com.cadettesdelacyber.CyberChall.models.Module;
import com.cadettesdelacyber.CyberChall.models.SousModule;
import com.cadettesdelacyber.CyberChall.repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	public void removeSousModuleByIndex(Long moduleId, int index) {
		Optional<Module> optionalModule = moduleRepository.findById(moduleId);
		optionalModule.ifPresent(module -> {
			List<SousModule> sousModules = module.getSousModules();
			if (index >= 0 && index < sousModules.size()) {
				sousModules.remove(index);
				moduleRepository.save(module);
			}
		});
	}

	public void addSousModule(Long moduleId, String nomSousModule) {
		Optional<Module> optionalModule = moduleRepository.findById(moduleId);
		optionalModule.ifPresent(module -> {
			SousModule nouveau = new SousModule();
			nouveau.setTitre(nomSousModule);
			nouveau.setModule(module); // très important pour la liaison
			module.getSousModules().add(nouveau);
			moduleRepository.save(module);
		});
	}

	// 🔹 Récupérer tous les modules
	public List<Module> getAllModules() {
		return moduleRepository.findAll();
	}

	// 🔹 Récupérer une liste de modules par leurs IDs
	public List<Module> getModuleByIds(List<Long> ids) {
		return moduleRepository.findAllById(ids);
	}

	public List<Module> findAllWithSousModules() {
		return moduleRepository.findAll();
	}

	// 🔹 Récupérer un module par son ID
	public Optional<Module> getModuleById(Long id) {
		return moduleRepository.findById(id);
	}

	// 🔹 Ajouter un nouveau module
	public Module createModule(Module module) {
		return moduleRepository.save(module);
	}

	// 🔹 Modifier un module existant
	public Module updateModule(Long id, Module moduleDetails) {
		return moduleRepository.findById(id).map(module -> {
			module.setNom(moduleDetails.getNom());
			module.setDescription(moduleDetails.getDescription());
			module.setSousModules(moduleDetails.getSousModules());
			return moduleRepository.save(module);
		}).orElseThrow(() -> new RuntimeException("Module non trouvé"));
	}

	// 🔹 Supprimer un module
	public void deleteModule(Long id) {
		moduleRepository.deleteById(id);
	}

	public Module findByNom(String nom) {
		return moduleRepository.findByNom(nom).orElse(null);
	}

}