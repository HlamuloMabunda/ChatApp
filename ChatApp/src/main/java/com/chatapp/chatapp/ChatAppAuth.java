/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;



import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChatAppAuth {

    private Map<String, User> users = new HashMap<>();
    private boolean loggedIn = false;

    // Hashing password method
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public String checkUsernameMessage(String username, String firstName, String lastName) {
        if (username.contains("_") && username.length() <= 5) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    public String checkPasswordComplexityMessage(String password) {
        boolean valid = password.length() >= 8 &&
                        password.matches(".*[A-Z].*") &&
                        password.matches(".*\\d.*") &&
                        password.matches(".*[!@#$%^&*()].*");

        return valid ? 
            "Password successfully captured." : 
            "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String checkCellPhoneMessage(String number) {
        if (number.startsWith("+27") && number.length() == 12) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
    }


    public static boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    public static boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+27") && number.length() == 12;
    }

    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }

        String hashedPassword = hashPassword(password);
        users.put(username, new User(username, hashedPassword, cellNumber, firstName, lastName));
        return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
    }
   public boolean loginUser(String username, String password) {
    if (users.containsKey(username)) {
        User user = users.get(username);
        String hashedInputPassword = hashPassword(password);
        if (user.getPassword().equals(hashedInputPassword)) {
            loggedIn = true;
            return true;
        }
    }
    loggedIn = false;  // Ensure it resets if login fails
    return false;
}
    public String returnLoginStatus() {
        return loggedIn ? "Login successful! Redirecting to chat..." : "Login failed: Incorrect credentials.";
    }
}
