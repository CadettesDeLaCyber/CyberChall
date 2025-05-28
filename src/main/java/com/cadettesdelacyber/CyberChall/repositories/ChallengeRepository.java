package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
