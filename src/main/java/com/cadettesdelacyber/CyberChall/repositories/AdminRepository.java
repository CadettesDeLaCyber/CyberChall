package com.cadettesdelacyber.CyberChall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadettesdelacyber.CyberChall.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsernameAndPassword(String username, String password);

	Admin findByUsername(String username);
}
