/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.chatapp.chatapp.ChatAppAuth;
import com.chatapp.chatapp.User;

public class RegisterForm {
    
    JFrame frame;
    JLabel messageLabel; // Label for error/success messages

      private ChatAppAuth auth; // Single shared instance
        
    public RegisterForm(ChatAppAuth auth) {
        this.auth = auth;
        frame = new JFrame("Register");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
JLabel userLabel = new JLabel("Username:");
userLabel.setBounds(10, 20, 80, 25);
panel.add(userLabel);

JTextField userText = new JTextField(20);
userText.setBounds(150, 20, 165, 25);
panel.add(userText);

JLabel passwordLabel = new JLabel("Password:");
passwordLabel.setBounds(10, 50, 80, 25);
panel.add(passwordLabel);

JPasswordField passwordText = new JPasswordField(20);
passwordText.setBounds(150, 50, 165, 25);
panel.add(passwordText);

JLabel phoneLabel = new JLabel("Phone:");
phoneLabel.setBounds(10, 80, 80, 25);
panel.add(phoneLabel);

JTextField phoneText = new JTextField(20);
phoneText.setBounds(150, 80, 165, 25);
panel.add(phoneText);

JLabel firstNameLabel = new JLabel("First Name:");
firstNameLabel.setBounds(10, 110, 80, 25);
panel.add(firstNameLabel);

JTextField firstNameText = new JTextField(20);
firstNameText.setBounds(150, 110, 165, 25);
panel.add(firstNameText);

JLabel lastNameLabel = new JLabel("Last Name:");
lastNameLabel.setBounds(10, 140, 80, 25);
panel.add(lastNameLabel);

JTextField lastNameText = new JTextField(20);
lastNameText.setBounds(150, 140, 165, 25);
panel.add(lastNameText);

// Register button under all fields
JButton registerButton = new JButton("Register");
registerButton.setBounds(120, 180, 100, 25);
panel.add(registerButton);

// Message label under the button
messageLabel = new JLabel("");
messageLabel.setBounds(10, 220, 350, 25);
messageLabel.setForeground(Color.RED);
panel.add(messageLabel);

// Login link below the message label
JLabel loginLink = new JLabel("<HTML><U>Already have an account? Login here</U></HTML>");
loginLink.setForeground(Color.BLUE);
loginLink.setBounds(90, 260, 250, 25);
loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
panel.add(loginLink);


        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new LoginForm(auth);
            }
        });

        // Handle registration
        registerButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            String firstname = firstNameText.getText();
            String lastname = lastNameText.getText();
            String phone = phoneText.getText();

            if (!checkUsername(username)) {
                setMessage("Username must contain an underscore and be no longer than 5 characters.", false);
                return;
            }

            if (!checkPasswordComplexity(password)) {
                setMessage("Password must contain at least 8 characters, one uppercase letter, one number, and one special character.", false);
                return;
            }

           if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()) {
           setMessage("Please fill in all the fields.", false);
          return;
           }
            // If everything is valid, proceed with registration
            registerUser(username,password,firstname,lastname, phone);
            setMessage("Registration Successful!", true);  // Success message
        });
    }

    // Method to check the username
    private boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true; // Username is valid
        }
        return false; // Username is invalid
    }

    // Method to check the password complexity
    private boolean checkPasswordComplexity(String password) {
        // Check if password contains at least 8 characters, one uppercase, one number, and one special character
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") && 
               password.matches(".*\\d.*") && 
               password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    // Method to check if the phone number is valid (South African format with country code +27)
    private boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+27[6-9]\\d{8}"); // Validates the South African phone number
    }

    private void registerUser(String username, String password,String firstname,String lastname, String phone) {
    auth.registerUser(username,password,firstname,lastname, phone); 

    System.out.println("User registered with username: " + username);
    System.out.println("Password: " + password);
    System.out.println("Phone: " + phone);
}

    private void setMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        if (isSuccess) {
            messageLabel.setForeground(Color.GREEN);  // Green for success
        } else {
            messageLabel.setForeground(Color.RED);    // Red for error
        }
    }
}
