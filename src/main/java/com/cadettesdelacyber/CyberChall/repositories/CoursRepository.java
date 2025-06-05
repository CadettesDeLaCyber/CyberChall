package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.Cours;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
	Cours findBySousModuleId(Long sousModuleId);
}
