package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quizz")
public class Quizz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String questions;

	@OneToOne
	@JoinColumn(name = "sousmodule_id", unique = true)
	private SousModule sousModule;

	public Quizz() {

	}

	public Quizz(Long id, String questions, SousModule sousModule) {
		super();
		this.id = id;
		this.questions = questions;
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

	public SousModule getSousModule() {
		return sousModule;
	}

	public void setSousModule(SousModule sousModule) {
		this.sousModule = sousModule;
	}

}
