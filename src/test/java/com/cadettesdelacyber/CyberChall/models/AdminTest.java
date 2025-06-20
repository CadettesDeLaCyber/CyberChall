package com.cadettesdelacyber.CyberChall.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AdminTest {

	@Test
    void testConstructeurEtGetters() {
        SessionTemporaire session1 = new SessionTemporaire();
        SessionTemporaire session2 = new SessionTemporaire();
        List<SessionTemporaire> sessions = List.of(session1, session2);

        Admin admin = new Admin(1L, "adminUser", "securePass", sessions);

        assertEquals(1L, admin.getId());
        assertEquals("adminUser", admin.getUsername());
        assertEquals("securePass", admin.getPassword());
        assertEquals(2, admin.getSessions().size());
    }
	
	@Test
    void testSetters() {
        Admin admin = new Admin();
        admin.setUsername("newUser");
        admin.setPassword("newPass");

        assertEquals("newUser", admin.getUsername());
        assertEquals("newPass", admin.getPassword());
    }

}
