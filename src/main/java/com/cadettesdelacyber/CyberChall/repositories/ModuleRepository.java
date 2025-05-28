package com.cadettesdelacyber.CyberChall.repositories;

import com.cadettesdelacyber.CyberChall.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
	List<Module> findByNomContainingIgnoreCase(String nom);

	Optional<Module> findByNom(String nom);
}