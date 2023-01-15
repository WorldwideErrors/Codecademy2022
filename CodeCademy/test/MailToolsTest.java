/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import People.Cursist;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ordinary
 */
public class MailToolsTest {

    public MailToolsTest() {
    }


    /*
      @desc Validates if mailAddress is valid. It should be in the form of:
      <at least 1 character>@<at least 1 character>.<at least 1 character>
     */
 /*
      @subcontract no mailbox part {
      @requires !mailAddress.contains("@") || mailAddress.split("@")[0].length
      < 1;
      @ensures \result = false; } /
     */
    @Test
    public void testValidateEmail_noMailboxPart() {
        String email = "@example.com.";
        Boolean result = Cursist.formatEmail(email);
        assertEquals(false, result);

    }

    /*
      @subcontract subdomain-tld delimiter {
      @requires !mailAddress.contains("@") ||
      mailAddress.split("@")[1].split(".").length > 2;
      @ensures \result = false; } /
     */
    @Test
    public void testValidateEmail_noSubdomainDelimiter() {
        String email = "someonehotmail.com";
        Boolean result = Cursist.formatEmail(email);
        assertEquals(false, result);
    }

    /*
    
      @subcontract no subdomain part {
      @requires !mailAddress.contains("@") ||
      mailAddress.split("@")[1].split(".")[0].length < 1;
      @ensures \result = false; } /
     */
    @Test
    public void testValidateEmail_NoSubdomainPart() {
        String email = "someone@.com";
        Boolean result =Cursist.formatEmail(email);
        assertEquals(false, result);
    }
      @Test
    public void testValidateEmail_NoSubdomainPart2() {
        String email = "jeremy@.nl";
        Boolean result = Cursist.formatEmail(email);
        assertEquals(false, result);
    }

    /*   
      @subcontract no tld part {
      @requires !mailAddress.contains("@") ||
      mailAddress.split("@")[1].split(".")[1].length < 1;
      @ensures \result = false; } /
     */
    @Test
    public void testValidateEmail_NoTLDPart() {
        String email = "example@hotmail.";
        Boolean result =Cursist.formatEmail(email);
        assertEquals(false, result);
    }

    @Test
    public void testValidateEmail_NoTLDPart2() {
        String email = "one@gmail.";
        Boolean result = Cursist.formatEmail(email);
        assertEquals(false, result);
    }

    /*   
      @subcontract valid email {
      @requires no other precondition
      @ensures \result = true; }
     */
    @Test
    public void testValidateEmail_True() {
        assertTrue(Cursist.formatEmail("user@subdomain.com"));
    }
    
    @Test
    public void testValidateEmail_True2() {
        assertTrue(Cursist.formatEmail("test2@gmail.nl"));
    }
    

}
