package com.cadettesdelacyber.CyberChall.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Session> sessions;
     

    public Admin() {
	
	}

	public Admin(Long id, String username, String password, List<Session> sessions) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sessions = sessions;
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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}