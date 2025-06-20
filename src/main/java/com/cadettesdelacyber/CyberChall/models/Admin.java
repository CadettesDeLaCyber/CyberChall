package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<SessionTemporaire> sessionsTemporaire;
     

    public Admin() {
	
	}

	public Admin(Long id, String username, String password, List<SessionTemporaire> sessionsTemporaire) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sessionsTemporaire = sessionsTemporaire;
	}

	// Getters / setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SessionTemporaire> getSessions() {
        return sessionsTemporaire;
    }

    public void setSessions(List<SessionTemporaire> sessionsTemporaire) {
        this.sessionsTemporaire = sessionsTemporaire;
    }
}