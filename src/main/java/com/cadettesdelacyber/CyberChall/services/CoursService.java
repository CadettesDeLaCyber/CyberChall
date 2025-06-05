package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Cours;
import com.cadettesdelacyber.CyberChall.repositories.CoursRepository;

@Service
public class CoursService {
	
	@Autowired
	private CoursRepository coursRepository;
	
	public Cours findById(Long id) {
	    return coursRepository.findById(id).orElse(null);
	}


    public Cours findBySousModuleId(Long id) {
        return coursRepository.findBySousModuleId(id);
    }

	public List<Cours> getAllCours() {
		return coursRepository.findAll();
	}

	public Cours getCours(Long id) {
		return coursRepository.findById(id).orElse(null);
	}

	public Cours saveCours(Cours cours) {
		return coursRepository.save(cours);
	}

	public void deleteCours(Long id) {
		coursRepository.deleteById(id);
	}
}
