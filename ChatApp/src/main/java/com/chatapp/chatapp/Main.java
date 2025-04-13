
package com.chatapp.chatapp;


import javax.swing.*;

public class Main {

    private ChatAppAuth auth = new ChatAppAuth(); // ✅ Make it a field!

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("ChatApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeMainComponents(panel, frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeMainComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 40, 150, 30);
        panel.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 90, 150, 30);
        panel.add(loginButton);

        registerButton.addActionListener(e -> {
            frame.dispose();
            new RegisterForm(auth); // ✅ Now works!
        });

        loginButton.addActionListener(e -> {
            frame.dispose();
            new LoginForm(auth); // ✅ Now works!
        });
    }
}
