package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sous_module")
public class SousModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titre;

	private String imageUrl;

	// Foreign Key to Module
	@ManyToOne
	@JoinColumn(name = "module_id")
	private Module module;

	// lien de redirection vers le sous-module en particulier (page de détails :
	// base + quizz)
	private String link;

	// Foreign Key to Cours
	@OneToOne
	@JoinColumn(name = "cours_id")
	private Cours cours;

	// Foreign Key to Quizz
	@OneToOne
	@JoinColumn(name = "quizz_id")
	private Quiz quizz;

	// Foreign Key to Challenge
	@OneToOne
	@JoinColumn(name = "challenge_id")
	private Challenge challenge;

	public SousModule() {

	}

	public SousModule(Long id, String titre, String imageUrl, Module module, String link, Cours cours, Quiz quizz,
			Challenge challenge) {
		super();
		this.id = id;
		this.titre = titre;
		this.imageUrl = imageUrl;
		this.module = module;
		this.link = link;
		this.cours = cours;
		this.quizz = quizz;
		this.challenge = challenge;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Quiz getQuizz() {
		return quizz;
	}

	public void setQuizz(Quiz quizz) {
		this.quizz = quizz;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

}
