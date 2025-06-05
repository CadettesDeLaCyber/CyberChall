package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String questions;
	
	private String reponses;

	@OneToOne
	@JoinColumn(name = "sousmodule_id", unique = true)
	private SousModule sousModule;

	public Quiz() {

	}

	public Quiz(Long id, String questions, String reponses, SousModule sousModule) {
		super();
		this.id = id;
		this.questions = questions;
		this.reponses = reponses;
		this.sousModule = sousModule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}
	

	public String getReponses() {
		return reponses;
	}

	public void setReponses(String reponses) {
		this.reponses = reponses;
	}

	public SousModule getSousModule() {
		return sousModule;
	}

	public void setSousModule(SousModule sousModule) {
		this.sousModule = sousModule;
	}

}
