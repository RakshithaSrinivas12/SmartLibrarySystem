package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchBook extends JFrame {

    JTextField txtSearch;
    JButton btnSearch;
    JTable table;

    public SearchBook() {

        setTitle("Search Book");

        setSize(600,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();

        JLabel lblSearch = new JLabel("Book Title:");

        txtSearch = new JTextField(20);

        btnSearch = new JButton("Search");

        topPanel.add(lblSearch);

        topPanel.add(txtSearch);

        topPanel.add(btnSearch);

        String[] columns = {"Book ID","Title","Author","Quantity"};

        DefaultTableModel model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);

        add(pane, BorderLayout.CENTER);

        btnSearch.addActionListener(e -> searchBook(model));
    }

    private void searchBook(DefaultTableModel model) {

        model.setRowCount(0);

        String title = txtSearch.getText();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM books WHERE title LIKE ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + title + "%");

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("book_id"),

                        rs.getString("title"),

                        rs.getString("author"),

                        rs.getInt("quantity")
                });
            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}