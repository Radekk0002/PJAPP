package GUI;

import Library.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GUI implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button1 = new JButton("WYPOŻYCZ");
    JButton button2 = new JButton("ODDAJ");
    JButton button3 = new JButton("STAN BIBLIOTEKI");

    JLabel label = new JLabel("testowa: 0");
    JLabel label2 = new JLabel();
    Book book1 = new Book(1, "skjwi");
    Book book2 = new Book(3, "Kjedwkj");
    static List<Book> books = new ArrayList();
    JList<Book> books2 = new JList<>();
    DefaultListModel<Book> model = new DefaultListModel<>();

    JTextArea notepad = new JTextArea(5, 10);


    Library library = new Library(books);





    int count = 0;


    public GUI(){
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(label);
        panel.add(notepad);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Biblioteka");
        frame.pack();
        frame.setVisible(true);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        library.zwroc(book1);
        library.zwroc(book2);

        button1.setBounds(200,100,100,80);
        notepad.setVisible(false);


    }
    public static void main(String[] args){
        new GUI();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == button1) {
            //testowa funkcja
            count++;
            label.setText("testowa: " + count);
        }
        if(source == button2) {
            //testowa funkcja
            count--;
            label.setText("testowa: " + count);
        }
        if(source == button3) {
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
            label.setVisible(false);



            //String text = "testowy";
            //label2.setText(text);
           // panel.add(label2);

            notepad.setText(library.booksInLibrary());
            panel.add(notepad);
            notepad.setVisible(true);



        }
    }
}

