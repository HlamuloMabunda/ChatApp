/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;


import javax.swing.*;
import java.awt.event.*;

public class HomePage {
    private String username;

    public HomePage(String username) {
        this.username = username;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat " + username + "!");

        showMenu();
    }

    private void showMenu() {
        String[] options = {"Send Message", "Show Recently Sent Messages", "Quit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null,
                    "Select an option:",
                    "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 0) {
                new MessageSender(username);  // sends messages
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Coming Soon.");
            } else {
                JOptionPane.showMessageDialog(null, "Goodbye " + username + "!");
                break;
            }
        }
    }
}
