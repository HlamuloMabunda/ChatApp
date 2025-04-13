/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.chatapp.chatapp;

import com.chatapp.chatapp.ChatAppAuth;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatAppAuthTest {

    private ChatAppAuth chatApp;

    @Before
    public void setUp() {
        chatApp = new ChatAppAuth();
    }

    @Test
    public void testCorrectUsernameFormat() {
        String message = chatApp.checkUsernameMessage("kyl_1", "Kyle", "Smith");
        assertEquals("Welcome Kyle, Smith it is great to see you.", message);
    }

    @Test
    public void testIncorrectUsernameFormat() {
        String message = chatApp.checkUsernameMessage("kyle!!!!!!!", "Kyle", "Smith");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", message);
    }

    @Test
    public void testCorrectPassword() {
        String result = chatApp.checkPasswordComplexityMessage("Ch&&sec@ke99!");
        assertEquals("Password successfully captured.", result);
    }

    @Test
    public void testIncorrectPassword() {
        String result = chatApp.checkPasswordComplexityMessage("password");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testCorrectCellNumber() {
        String result = chatApp.checkCellPhoneMessage("+27838968976");
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testIncorrectCellNumber() {
        String result = chatApp.checkCellPhoneMessage("08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.", result);
    }

    @Test
    public void testLoginSuccess() {
        chatApp.registerUser("kyle_", "kyle@1234", "+27831234567", "kyle", "smith");
        boolean success = chatApp.loginUser("kyle_", "kyle@1234");
        assertTrue(success);
    }

    @Test
    public void testLoginFail() {
        chatApp.registerUser("kyle_", "kyle@1234", "+27831234567", "kyle", "smith");
        boolean fail = chatApp.loginUser("kyle_", "wrongpass");
        assertFalse(fail);
    }

    @Test
    public void testUsernameIsValid() {
        assertTrue(ChatAppAuth.checkUsername("abc_1"));
    }

    @Test
    public void testUsernameIsInvalid() {
        assertFalse(ChatAppAuth.checkUsername("longusername"));
    }
}
