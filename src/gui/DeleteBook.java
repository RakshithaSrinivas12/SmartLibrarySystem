package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteBook extends JFrame {

    JTextField txtBookId;
    JButton btnDelete;

    public DeleteBook() {

        setTitle("Delete Book");

        setSize(400,250);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(255,240,240));

        JLabel heading = new JLabel("DELETE BOOK");

        heading.setBounds(90,20,250,30);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        JLabel lblBookId = new JLabel("Book ID:");

        lblBookId.setBounds(50,90,100,25);

        txtBookId = new JTextField();

        txtBookId.setBounds(160,90,150,25);

        btnDelete = new JButton("Delete");

        btnDelete.setBounds(130,150,120,35);

        panel.add(heading);

        panel.add(lblBookId);

        panel.add(txtBookId);

        panel.add(btnDelete);

        add(panel);

        btnDelete.addActionListener(this::deleteBook);
    }

    private void deleteBook(ActionEvent evt) {

        int bookId = Integer.parseInt(txtBookId.getText());

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM books WHERE book_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, bookId);

            int rows = pst.executeUpdate();

            if(rows > 0) {

                JOptionPane.showMessageDialog(this,"Book Deleted Successfully");

            } else {

                JOptionPane.showMessageDialog(this,"Book ID Not Found");
            }

            txtBookId.setText("");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}
