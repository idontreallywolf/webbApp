package com.webapp.webapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.webapp.controller.RegisterController;
import com.webapp.dao.AccountDao;
import com.webapp.model.Account;
import com.webapp.Config;

@SpringBootTest
public class RegisterControllerTest {

	
	private Config config;
	private AccountDao accDao;

	private RegisterController rc;
	
	@BeforeEach
	public void init() {
		config = new Config();
		rc = new RegisterController(this.accDao = Mockito.mock(AccountDao.class));
	}
	
    @Test
    public void testValidateName() {
    	System.out.println(rc.validateName("pepe"));
    	assertEquals("[]", rc.validateName("pepe"));
    	assertEquals("[]", rc.validateName("Weeeee"));
    	assertEquals("[]", rc.validateName("ZzzZzZZzzzZZzZ"));
    	assertEquals("[]", rc.validateName("Firstname"));
    	assertEquals("[]", rc.validateName("Lastname"));
    	assertEquals("[]", rc.validateName("lastname"));

    	assertNotEquals("[]", rc.validateName("P3p3"));
    	assertNotEquals("[]", rc.validateName("P3 p3"));
    	assertNotEquals("[]", rc.validateName("loooooooooooooooooooongname"));
    	assertNotEquals("[]", rc.validateName("Sym!bols#"));
    	assertNotEquals("[]", rc.validateName("spaces here"));
    }

    @Test
    public void testValidateUsername() {
        //System.out.println("usernameValidation: " + rc.validateUsername("pepe"));
        Mockito.when(accDao.getAccountByUsername("Username123")).thenReturn(null);
        assertEquals("[]", rc.validateUsername("Username123"));
        assertEquals("[]", rc.validateUsername("PepeMad010203"));
        assertEquals("[]", rc.validateUsername("0000011111Madman0000"));

        Mockito.when(accDao.getAccountByUsername("pepe")).thenReturn(new Account("a","b","pepe","pepe123","abc@mail.com"));
        assertEquals("[err#taken]", rc.validateUsername("pepe"));
        assertEquals("[err#sym]", rc.validateUsername("PepeMad spaces"));
        assertEquals("[err#sym]", rc.validateUsername("PepeMad#symbols"));
        assertEquals("[err#maxLen]", rc.validateUsername("PepeMadLongUserNameMoreThanLimit"));
        assertEquals("[err#minLen]", rc.validateUsername("unm"));
    }
    
    @Test
    public void testValidatePassword() {
    	assertEquals("[]", rc.validatePassword("Password1", "Password1"));
    	assertEquals("[]", rc.validatePassword("R4ND0MW0Rd1", "R4ND0MW0Rd1"));
    	
    	assertEquals("[err#badSec]", rc.validatePassword("password", "password"));
    	assertEquals("[err#cnfrm, err#minLen, err#badSec]", rc.validatePassword("pass", "passd"));
    }
    
    @Test
    public void testValidateEmail() {
        Mockito.when(accDao.getAccountByEmail("pepe@pepe.pepe")).thenReturn(null);
    	assertEquals("[]", rc.validateEmail("pepe@pepe.pepe"));
    	
    	assertEquals("[err#len]", rc.validateEmail("p@e.p"));
    	assertEquals("[err#inv]", rc.validateEmail("reeeee--!###\"e.p"));
    	
    	Mockito.when(accDao.getAccountByEmail("pepe@ree.brrk")).thenReturn(new Account("a","b","pepe","pepe123","abc@mail.com"));
    	assertEquals("[err#taken]", rc.validateEmail("pepe@ree.brrk"));
    }
}
