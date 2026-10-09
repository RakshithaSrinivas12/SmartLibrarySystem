package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReturnBook extends JFrame {

    JTextField txtIssueId, txtReturnDate;
    JButton btnReturn;

    public ReturnBook() {

        setTitle("Return Book");

        setSize(400,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(255,240,240));

        JLabel heading = new JLabel("RETURN BOOK");

        heading.setBounds(90,20,250,30);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        JLabel lblIssue = new JLabel("Issue ID:");

        lblIssue.setBounds(50,90,100,25);

        txtIssueId = new JTextField();

        txtIssueId.setBounds(160,90,150,25);

        JLabel lblDate = new JLabel("Return Date:");

        lblDate.setBounds(50,140,100,25);

        txtReturnDate = new JTextField();

        txtReturnDate.setBounds(160,140,150,25);

        btnReturn = new JButton("Return");

        btnReturn.setBounds(130,200,120,35);

        panel.add(heading);
        panel.add(lblIssue);
        panel.add(txtIssueId);
        panel.add(lblDate);
        panel.add(txtReturnDate);
        panel.add(btnReturn);

        add(panel);

        btnReturn.addActionListener(this::returnBook);
    }

    private void returnBook(ActionEvent evt) {

        int issueId = Integer.parseInt(txtIssueId.getText());

        String returnDate = txtReturnDate.getText();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE issue_book SET return_date=? WHERE issue_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, returnDate);

            pst.setInt(2, issueId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,"Book Returned Successfully");

            txtIssueId.setText("");

            txtReturnDate.setText("");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}
