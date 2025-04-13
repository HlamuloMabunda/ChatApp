/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Inside your MessageSender.java

public class MessageSender {

    private String username;
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private JSONArray storedMessages = new JSONArray();

    public MessageSender(String username) {
        this.username = username;
    }

    public String validateMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra + ", please reduce size.";
        }
    }

    public String validateRecipientNumber(String number) {
        if (Message.checkRecipientCell(number)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String processMessageAction(int action, Message msg) {
        switch (action) {
            case 1:
                sentMessages.add(msg);
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete message.";
            case 3:
                JSONObject jsonMessage = new JSONObject();
                jsonMessage.put("MessageID", msg.generateMessageID());
                jsonMessage.put("Recipient", msg.checkRecipientCell(username));
                jsonMessage.put("Message", msg.printMessageInfo());
                jsonMessage.put("MessageHash", msg.createMessageHash(username, action, username));
                storedMessages.add(jsonMessage);
                return "Message successfully stored.";
            default:
                return "Invalid selection.";
        }
    }

    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    public JSONArray getStoredMessages() {
        return storedMessages;
    }
}
