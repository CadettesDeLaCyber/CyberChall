package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadettesdelacyber.CyberChall.models.Quiz;
import com.cadettesdelacyber.CyberChall.repositories.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepository;
	
	public Quiz findById(Long id) {
	    return quizRepository.findById(id).orElse(null);
	}
	
	public Quiz findBySousModuleId(Long id) {
        return quizRepository.findBySousModuleId(id);
    }

	public List<Quiz> getAllQuiz() {
		return quizRepository.findAll();
	}

	public Quiz getQuiz(Long id) {
		return quizRepository.findById(id).orElse(null);
	}

	public Quiz saveChallenge(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	public void deleteQuiz(Long id) {
		quizRepository.deleteById(id);
	}

}
