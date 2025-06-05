package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Challenge;
import com.cadettesdelacyber.CyberChall.repositories.ChallengeRepository;

@Service
public class ChallengeService {
	@Autowired
	private ChallengeRepository challengeRepository;
	
	public Challenge findBySousModuleId(Long id) {
        return challengeRepository.findBySousModuleId(id);
    }

	public List<Challenge> getAllChallenges() {
		return challengeRepository.findAll();
	}

	public Challenge getChallenge(Long id) {
		return challengeRepository.findById(id).orElse(null);
	}

	public Challenge saveChallenge(Challenge challenge) {
		return challengeRepository.save(challenge);
	}

	public void deleteChallenge(Long id) {
		challengeRepository.deleteById(id);
	}
}
