package com.cadettesdelacyber.CyberChall.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;

@Entity
@Table(name = "session")
public class SessionTemporaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private int duree;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToMany
    @JoinTable(name = "session_temporaire_module", 
        joinColumns = @JoinColumn(name = "session_temporaire_id"), 
        inverseJoinColumns = @JoinColumn(name = "module_id"))
    private List<Module> modules;

    @Transient
    private String qrCodeBase64;

    @Transient
    private String urlModules;

    @Transient
    private String modulesParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Transient
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.getDateExpiration());
    }

    @Transient
    public LocalDateTime getDateExpiration() {
        return this.dateCreation.plusDays(this.duree);
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }

    public String getUrlModules() {
        return urlModules;
    }

    public void setUrlModules(String urlModules) {
        this.urlModules = urlModules;
    }

    public String getModulesParams() {
        return modulesParams;
    }

    public void setModulesParams(String modulesParams) {
        this.modulesParams = modulesParams;
    }
}