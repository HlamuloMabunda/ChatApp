/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.chatapp.chatapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderTest {

    @Test
    public void testValidateMessageLength_Success() {
        MessageSender sender = new MessageSender("testUser");
        String result = sender.validateMessageLength("This is a valid message.");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testValidateMessageLength_Failure() {
        MessageSender sender = new MessageSender("testUser");
        String result = sender.validateMessageLength("A".repeat(251)); // 251 characters
        assertEquals("Message exceeds 250 characters by 1, please reduce size.", result);
    }

    @Test
    public void testValidateRecipientNumber_Success() {
        MessageSender sender = new MessageSender("testUser");
        String result = sender.validateRecipientNumber("+27711234567");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testValidateRecipientNumber_Failure() {
        MessageSender sender = new MessageSender("testUser");
        String result = sender.validateRecipientNumber("0711234567");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testProcessMessageAction_Send() {
        MessageSender sender = new MessageSender("testUser");
        Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String result = sender.processMessageAction(1, msg);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testProcessMessageAction_Disregard() {
        MessageSender sender = new MessageSender("testUser");
        Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String result = sender.processMessageAction(2, msg);
        assertEquals("Press 0 to delete message.", result);
    }

    @Test
    public void testProcessMessageAction_Store() {
        MessageSender sender = new MessageSender("testUser");
        Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String result = sender.processMessageAction(3, msg);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testProcessMessageAction_Invalid() {
        MessageSender sender = new MessageSender("testUser");
        Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String result = sender.processMessageAction(999, msg);
        assertEquals("Invalid selection.", result);
    }

    @Test
    public void testMessageHashGeneration() {
        MessageSender sender = new MessageSender("testUser");
       Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String messageHash = msg.createMessageHash("testUser", 1, "testUser");
        assertEquals("00:0:HITONIGHT", messageHash);
    }

    @Test
    public void testMessageIDGeneration() {
        MessageSender sender = new MessageSender("testUser");
        Message msg = new Message("Hello", "+27711234567", "testUser", "2025-04-13");
        String messageID = msg.generateMessageID();
        assertNotNull(messageID);
        System.out.println("Message ID generated: " + messageID);
    }

}
