package gui;
import gui.AddBook;
import gui.ViewBooks;
import gui.IssueBook;
import gui.AddStudent;
import gui.SearchBook;
import gui.DeleteBook;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Smart Library Dashboard");

        setSize(600,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBackground(new Color(220,230,250));

        JLabel title = new JLabel("SMART LIBRARY SYSTEM");

        title.setBounds(140,20,400,40);

        title.setFont(new Font("Arial",Font.BOLD,24));

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.addActionListener(e -> new AddBook().setVisible(true));
        btnAddBook.setBounds(200,100,200,40);

        JButton btnViewBook = new JButton("View Books");
        btnViewBook.addActionListener(e -> new ViewBooks().setVisible(true));
        btnViewBook.setBounds(200,160,200,40);

        JButton btnIssueBook = new JButton("Issue Book");
        btnIssueBook.addActionListener(e -> new IssueBook().setVisible(true));
        btnIssueBook.setBounds(200,220,200,40);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(e -> new ReturnBook().setVisible(true));
        btnReturnBook.setBounds(200,270,200,40);

        JButton btnStudent = new JButton("Add Student");
        btnStudent.addActionListener(e -> new AddStudent().setVisible(true));

        btnStudent.setBounds(200,330,200,40);

        panel.add(btnStudent);

        panel.add(btnReturnBook);

        JButton btnSearch = new JButton("Search Book");
        btnSearch.addActionListener(e -> new SearchBook().setVisible(true));

        btnSearch.setBounds(200,390,200,40);

        panel.add(btnSearch);

        JButton btnDelete = new JButton("Delete Book");
        btnDelete.addActionListener(e -> new DeleteBook().setVisible(true));

        btnDelete.setBounds(200,450,200,40);

        panel.add(btnDelete);





        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(200,510,200,40);
        btnLogout.addActionListener(e -> {

            new LoginForm().setVisible(true);

            dispose();
        });
        panel.add(title);
        panel.add(btnAddBook);
        panel.add(btnViewBook);
        panel.add(btnIssueBook);
        panel.add(btnLogout);

        add(panel);
    }
}