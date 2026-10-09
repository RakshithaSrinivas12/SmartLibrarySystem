package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginForm() {

        setTitle("Smart Library Login");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230,240,255));

        JLabel lblTitle = new JLabel("SMART LIBRARY");
        lblTitle.setBounds(100,20,250,30);
        lblTitle.setFont(new Font("Arial",Font.BOLD,22));

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50,80,100,25);

        txtUser = new JTextField();
        txtUser.setBounds(150,80,150,25);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50,130,100,25);

        txtPass = new JPasswordField();
        txtPass.setBounds(150,130,150,25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(140,190,100,30);

        panel.add(lblTitle);
        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(this::loginAction);
    }

    private void loginAction(ActionEvent evt) {

        String username = txtUser.getText();
        String password = txtPass.getText();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(this,"Login Successful");

                new Dashboard().setVisible(true);

                dispose();

            }

             else {

                JOptionPane.showMessageDialog(this,"Invalid Username or Password");
            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }

    public static void main(String[] args) {

        new LoginForm().setVisible(true);
    }
}
