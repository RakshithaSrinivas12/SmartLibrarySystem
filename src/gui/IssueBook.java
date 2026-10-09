package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class IssueBook extends JFrame {

    JTextField txtStudentId, txtBookId, txtIssueDate;
    JButton btnIssue;

    public IssueBook() {

        setTitle("Issue Book");

        setSize(400,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(240,250,240));

        JLabel heading = new JLabel("ISSUE BOOK");

        heading.setBounds(110,20,250,30);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        JLabel lblStudent = new JLabel("Student ID:");

        lblStudent.setBounds(50,80,100,25);

        txtStudentId = new JTextField();

        txtStudentId.setBounds(160,80,150,25);

        JLabel lblBook = new JLabel("Book ID:");

        lblBook.setBounds(50,130,100,25);

        txtBookId = new JTextField();

        txtBookId.setBounds(160,130,150,25);

        JLabel lblDate = new JLabel("Issue Date:");

        lblDate.setBounds(50,180,100,25);

        txtIssueDate = new JTextField();

        txtIssueDate.setBounds(160,180,150,25);

        btnIssue = new JButton("Issue");

        btnIssue.setBounds(130,240,120,35);

        panel.add(heading);
        panel.add(lblStudent);
        panel.add(txtStudentId);
        panel.add(lblBook);
        panel.add(txtBookId);
        panel.add(lblDate);
        panel.add(txtIssueDate);
        panel.add(btnIssue);

        add(panel);

        btnIssue.addActionListener(this::issueBook);
    }

    private void issueBook(ActionEvent evt) {

        int studentId = Integer.parseInt(txtStudentId.getText());

        int bookId = Integer.parseInt(txtBookId.getText());

        String issueDate = txtIssueDate.getText();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO issue_book(student_id,book_id,issue_date) VALUES(?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, studentId);

            pst.setInt(2, bookId);

            pst.setString(3, issueDate);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,"Book Issued Successfully");

            txtStudentId.setText("");

            txtBookId.setText("");

            txtIssueDate.setText("");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}
