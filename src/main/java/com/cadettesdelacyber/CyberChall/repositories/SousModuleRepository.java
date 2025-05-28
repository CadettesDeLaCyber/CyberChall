package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.SousModule;

@Repository
public interface SousModuleRepository extends JpaRepository<SousModule, Long> {
	// Tu peux ajouter des méthodes personnalisées ici si besoin
	boolean existsByTitreAndModule_Id(String titre, Long moduleId);

}
