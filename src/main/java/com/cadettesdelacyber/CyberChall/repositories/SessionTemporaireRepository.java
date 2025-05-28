package com.cadettesdelacyber.CyberChall.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.models.SessionTemporaire;

public interface SessionTemporaireRepository extends JpaRepository<SessionTemporaire, Long> {
	Optional<SessionTemporaire> findByToken(String token);

	// Permet de récupérer les sessions créées par un administrateur spécifique
	List<SessionTemporaire> findByAdmin(Admin admin);

}
