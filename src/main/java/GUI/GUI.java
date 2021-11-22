package GUI;

import GUI.SaveToFileMethods.SaveToFileMethods;
import Library.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static GUI.HttpMethods.HttpConnectionMethods.GetBooks;

import static GUI.HttpMethods.HttpConnectionMethods.Wypozycz;



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
        // test zapytania o zwrot wszystkich książek
        books = GetBooks("http://localhost:8000/getBooks");

        System.out.println(books.get(0));

        Wypozycz("http://localhost:8000/borrow", 0);

        // zapisywanie do pliku
        try {
            SaveToFileMethods.saveJsonToFile(books);
            SaveToFileMethods.saveXMLToFile(books);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

