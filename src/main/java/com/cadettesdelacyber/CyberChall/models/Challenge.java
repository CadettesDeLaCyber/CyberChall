package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge")
public class Challenge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titre;
	private String description;

	@OneToOne
	@JoinColumn(name = "sousmodule_id", unique = true)
	private SousModule sousModule;

	public Challenge() {

	}

	public Challenge(Long id, String titre, String description, SousModule sousModule) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.sousModule = sousModule;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SousModule getSousModule() {
		return sousModule;
	}

	public void setSousModule(SousModule sousModule) {
		this.sousModule = sousModule;
	}

}
