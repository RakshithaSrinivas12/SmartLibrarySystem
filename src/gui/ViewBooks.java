package gui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewBooks extends JFrame {

    JTable table;

    public ViewBooks() {

        setTitle("View Books");

        setSize(600,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Book ID","Title","Author","Quantity"};

        DefaultTableModel model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        add(pane);

        loadBooks(model);
    }

    private void loadBooks(DefaultTableModel model) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM books";

            PreparedStatement pst = con.prepareStatement(sql);

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
