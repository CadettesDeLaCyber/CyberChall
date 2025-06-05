package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadettesdelacyber.CyberChall.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	Quiz findBySousModuleId(Long sousModuleId);
}
