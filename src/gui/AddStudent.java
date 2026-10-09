package gui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudent extends JFrame {

    JTextField txtName, txtCourse;
    JButton btnSave;

    public AddStudent() {

        setTitle("Add Student");

        setSize(400,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(240,255,240));

        JLabel heading = new JLabel("ADD STUDENT");

        heading.setBounds(90,20,250,30);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        JLabel lblName = new JLabel("Student Name:");

        lblName.setBounds(40,90,120,25);

        txtName = new JTextField();

        txtName.setBounds(170,90,150,25);

        JLabel lblCourse = new JLabel("Course:");

        lblCourse.setBounds(40,140,120,25);

        txtCourse = new JTextField();

        txtCourse.setBounds(170,140,150,25);

        btnSave = new JButton("Save");

        btnSave.setBounds(130,200,120,35);

        panel.add(heading);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblCourse);
        panel.add(txtCourse);
        panel.add(btnSave);

        add(panel);

        btnSave.addActionListener(this::saveStudent);
    }

    private void saveStudent(ActionEvent evt) {

        String name = txtName.getText();

        String course = txtCourse.getText();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students(name,course) VALUES(?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);

            pst.setString(2, course);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,"Student Added Successfully");

            txtName.setText("");

            txtCourse.setText("");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}
