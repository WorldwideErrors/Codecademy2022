/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import People.Cursist;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ordinary
 */
public class PostalCodeTest {


    /*
     * @desc Formats the input postal code to a uniform output in the form
     * nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital
     * letters. Spaces before and after the input string are trimmed.
     */

 /* @subcontract null postalCode {
     * @requires postalCode == null;
     * @signals (NullPointerException) postalCode == null; }
     */
    @Test
    public void testPostalCodeInputNull() {
        try {
            Cursist.formatPostalCode(null);
            fail("Exception not reached");
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("postalCode cannot be null"));
        }
    }

    /* @subcontract valid postalCode {
     * @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     * Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     * postalCode.trim().substring(4).trim().length == 2 && 'A' <=
     * postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     * 'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <=
     * 'Z';
     *   @
     * ensures \result = postalCode.trim().substring(0, 4) + " " +
     * postalCode.trim().substring(4).trim().toUpperCase() }
     */
    @Test
    public void testValidPostalCode() {
        String postalCode = "1234aB";
        String expected = "1234 AB";
        String result = Cursist.formatPostalCode(postalCode);
        assertEquals(expected, result);
    }

    @Test
    public void testValidPostalCode2() {
        String postalCode = "1291 SE";
        String expected = "1291 SE";
        String result = Cursist.formatPostalCode(postalCode);
        assertEquals(expected, result);
    }

    /* @subcontract invalid postalCode {
     * @requires no other valid precondition;
     * @signals (IllegalArgumentException); }
     */
    @Test
    public void testInvalidPostalCodeOnlyNumbers() {
        try {
            Cursist.formatPostalCode("123241");
            fail("Exception not reached");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid postal code"));
        }

    }

    @Test
    public void testInvalidPostalCode3Numbers3Letters() {
        try {
            Cursist.formatPostalCode("123ABC");
            fail("Exception not reached");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid postal code"));
        }
    }

    @Test
    public void testInvalidPostalCodeOnlyLetters() {
        try {
            Cursist.formatPostalCode("ABCDEF");
            fail("Exception not reached");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid postal code"));
        }
    }
}
