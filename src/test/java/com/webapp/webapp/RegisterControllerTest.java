package com.webapp.webapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.webapp.controller.RegisterController;

@SpringBootTest
public class RegisterControllerTest {
	RegisterController rc = new RegisterController();
	
    @Test
    public void testValidateName() {
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
}
