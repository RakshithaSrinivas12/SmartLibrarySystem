package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBook extends JFrame {

    JTextField txtTitle, txtAuthor, txtQuantity;
    JButton btnSave;

    public AddBook() {

        setTitle("Add Book");

        setSize(400,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(240,240,255));

        JLabel heading = new JLabel("ADD BOOK");

        heading.setBounds(120,20,200,30);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        JLabel lblTitle = new JLabel("Title:");

        lblTitle.setBounds(50,80,100,25);

        txtTitle = new JTextField();

        txtTitle.setBounds(150,80,180,25);

        JLabel lblAuthor = new JLabel("Author:");

        lblAuthor.setBounds(50,130,100,25);

        txtAuthor = new JTextField();

        txtAuthor.setBounds(150,130,180,25);

        JLabel lblQuantity = new JLabel("Quantity:");

        lblQuantity.setBounds(50,180,100,25);

        txtQuantity = new JTextField();

        txtQuantity.setBounds(150,180,180,25);

        btnSave = new JButton("Save");

        btnSave.setBounds(140,240,100,35);

        panel.add(heading);
        panel.add(lblTitle);
        panel.add(txtTitle);
        panel.add(lblAuthor);
        panel.add(txtAuthor);
        panel.add(lblQuantity);
        panel.add(txtQuantity);
        panel.add(btnSave);

        add(panel);

        btnSave.addActionListener(this::saveBook);
    }

    private void saveBook(ActionEvent evt) {

        String title = txtTitle.getText();

        String author = txtAuthor.getText();

        int quantity = Integer.parseInt(txtQuantity.getText());

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO books(title,author,quantity) VALUES(?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, title);

            pst.setString(2, author);

            pst.setInt(3, quantity);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,"Book Added Successfully");

            txtTitle.setText("");

            txtAuthor.setText("");

            txtQuantity.setText("");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}