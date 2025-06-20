package com.cadettesdelacyber.CyberChall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cadettesdelacyber.CyberChall.models.Admin;
import com.cadettesdelacyber.CyberChall.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Creation compte admin
	@Transactional
	public Admin saveAdmin(Admin admin) {
		System.out.println(">>> Dans saveAdmin() - password brut: " + admin.getPassword());
		Admin savedAdmin = null;
		try {
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			savedAdmin = adminRepository.save(admin);
			System.out.println(">>> Admin sauvegardé avec id: " + savedAdmin.getId());
		} catch (Exception e) {
			System.out.println(">>> Exception dans saveAdmin()");
			e.printStackTrace();
		}
		return savedAdmin;
	}

	public Admin findById(Long id) {
		return adminRepository.findById(id).orElse(null);
	}

	// recherche par nom
	public Admin findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	// Obtenir la liste de tous les admin
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	// Obtenir un admin par son id
	public Admin getAdmin(Long id) {
		return adminRepository.findById(id).orElse(null);
	}

	// suppression compte admin
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

	public boolean checkPassword(Admin admin, String rawPassword) {
		return passwordEncoder.matches(rawPassword, admin.getPassword());
	}

}
