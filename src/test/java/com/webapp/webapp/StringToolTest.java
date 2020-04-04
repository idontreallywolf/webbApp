package com.webapp.webapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.webapp.tools.StringTool;

@SpringBootTest
public class StringToolTest {

    @Test
    public void testStringTool() {
    	assertEquals(StringTool.ucFirst("test"), "Test");
     	assertEquals(StringTool.ucFirst("tEst"), "Test");
     	assertEquals(StringTool.ucFirst("TESt"), "Test");
     	assertEquals(StringTool.ucFirst("LORDPEPE"), "Lordpepe");
     	assertEquals(StringTool.ucFirst("REEEeeeEeEeE"), "Reeeeeeeeeee");
     	assertEquals(StringTool.ucFirst("plebian"), "Plebian");
    }
}
