/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;

import java.util.Random;



public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(String messageID, String recipient, String message, String messageHash) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = messageHash;
    }

    public static boolean checkRecipientCell(String number) {
        return number != null && number.startsWith("+") && number.length() <= 13;
    }

    public static String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public static String createMessageHash(String messageID, int msgNum, String messageText) {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        return messageID.substring(0, 2) + ":" + msgNum + ":" + (firstWord + lastWord).toUpperCase();
    }

    public String printMessageInfo() {
        return "Message Sent!\n\n"
                + "MessageID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + message;
    }
}
