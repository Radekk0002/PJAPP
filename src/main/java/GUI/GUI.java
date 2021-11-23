package GUI;

import GUI.SaveToFileMethods.SaveToFileMethods;
import Library.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static GUI.HttpMethods.HttpConnectionMethods.*;


public class GUI implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JButton button_wypozycz = new JButton("WYPOŻYCZ");
    JButton button_oddaj = new JButton("ODDAJ");
    JButton button_stan = new JButton("STAN BIBLIOTEKI");
    JButton button_back = new JButton("POWRÓT");
    JButton button_xml = new JButton("XML");
    JButton button_json = new JButton("Json");


    JLabel lwypo = new JLabel();
    JPanel pwypo = new JPanel();
    JSplitPane spwypo = new JSplitPane();
    JButton button_wypozycz2 = new JButton("WYPOŻYCZ");

    JLabel lzwroc1 = new JLabel("ID:");
    JTextField tfzwroc1 = new JTextField();
    JLabel lzwroc2 = new JLabel("Tytuł:");
    JTextField tfzwroc2 = new JTextField();
    JButton button_zwroc = new JButton("ZWRÓĆ");
    JLabel komunikatzwroc = new JLabel("Zwrócono ksiażkę do biblioteki");
    JLabel komunikatzwroc3 = new JLabel("Zwrócono ksiażkę do biblioteki");
    JLabel komunikatzwroc2 = new JLabel("Uzupełnij ID");


    Book book1 = new Book(1, "Ksiazka1");
    Book book2 = new Book(2, "Ksiazka2");
    Book book3 = new Book(3, "Ksiazka3");
    SaveToFileMethods STF = new SaveToFileMethods();


    static List<Book> books = new ArrayList();
    JList<Book> books2 = new JList<>();
    DefaultListModel<Book> model = new DefaultListModel<>();
    DefaultListModel<Book> model2 = new DefaultListModel<>();

    JTextArea notepad = new JTextArea(5, 10);

    Library library;

    private String URL = "http://localhost:8000/";

    public GUI(){
        books = GetBooks(URL + "getBooks", true);

        library = new Library(books);
        ///     TWORZENIE LAYOUTU   ///

        frame.setPreferredSize(new Dimension(700,480));
        panel.setLayout(new BorderLayout( ));

        panel2.setPreferredSize(new Dimension(480,480));


        frame.add(panel,BorderLayout.SOUTH);




        panel2.add(lzwroc1);
        panel2.add(lzwroc2);
        panel.add(button_wypozycz);
        panel.add(button_oddaj);
        panel.add(button_stan);
        panel.add(button_back);
        panel.add(notepad);
        panel.add(books2);
        panel.add(spwypo);
        panel.add(button_wypozycz2);
        panel.add(lzwroc1);
        panel.add(tfzwroc1);
        panel.add(lzwroc2);
        panel.add(tfzwroc2);
        panel.add(button_zwroc);
        panel.add(komunikatzwroc2);
        panel.add(komunikatzwroc3);

        panel.add(button_xml);
        panel.add(button_json);


        panel.add(komunikatzwroc);
        panel.setBackground(new Color(40,40,40));



        //region            WIDOK WYPOŻYCZENIA

        spwypo.setBounds(100,20,500,180);
        button_wypozycz2.setBounds(250,220,200,80);
        button_back.setBounds(250,320,200,80);
        //endregion

        //region WIDOK ODDAWANIA
        tfzwroc1.setBounds(125,80,417,40);
        lzwroc1.setBounds(125,30,200,40);



        tfzwroc2.setBounds(375,80,200,180);
        lzwroc2.setBounds(375,30,200,40);


        lzwroc1.setForeground(Color.white);
        lzwroc1.setBackground(Color.red);
        lzwroc2.setForeground(Color.white);
        button_zwroc.setBounds(250,220,200,80);

        komunikatzwroc.setForeground(Color.white);
        komunikatzwroc2.setForeground(Color.white);
        komunikatzwroc3.setForeground(Color.white);

        //endregion

        //region WIDOK STANU BIBLIOTEKI
        notepad.setBackground(new Color(40,40,40));
        notepad.setBounds(100,20,500,180);


        Border border2 = BorderFactory.createLineBorder(new Color(50,50,50), 10);

        notepad.setBorder(border2);
        notepad.setForeground(Color.white);
        notepad.setPreferredSize(new Dimension(500,180));
        notepad.setBackground(new Color(50,50,50));

        //endregion

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(60,60,60));
        frame.setTitle("Biblioteka");
        frame.pack();
        frame.setVisible(true);

        lzwroc1.setVisible(false);
        lzwroc2.setVisible(false);
        tfzwroc1.setVisible(false);
        tfzwroc2.setVisible(false);
        button_zwroc.setVisible(false);

        button_wypozycz.addActionListener(this);
        button_oddaj.addActionListener(this);
        button_stan.addActionListener(this);
        button_back.addActionListener(this);
        button_wypozycz2.addActionListener(this);
        button_zwroc.addActionListener(this);
        button_xml.addActionListener(this);
        button_json.addActionListener(this);

        //region button style
        button_wypozycz.setForeground(new Color(50,50,50));
        button_wypozycz.setBackground(new Color(195,195,190));
        button_wypozycz.setBorderPainted(false);
        button_wypozycz.setFocusPainted(false);
        button_wypozycz.setContentAreaFilled(true);

        button_oddaj.setForeground(new Color(50,50,50));
        button_oddaj.setBackground(new Color(195,195,190));
        button_oddaj.setBorderPainted(false);
        button_oddaj.setFocusPainted(false);
        button_oddaj.setContentAreaFilled(true);

        button_stan.setForeground(new Color(50,50,50));
        button_stan.setBackground(new Color(195,195,190));
        button_stan.setBorderPainted(false);
        button_stan.setFocusPainted(false);
        button_stan.setContentAreaFilled(true);

        button_back.setForeground(new Color(50,50,50));
        button_back.setBackground(new Color(195,195,190));
        button_back.setBorderPainted(false);
        button_back.setFocusPainted(false);
        button_back.setContentAreaFilled(true);

        button_zwroc.setForeground(new Color(50,50,50));
        button_zwroc.setBackground(new Color(195,195,190));
        button_zwroc.setBorderPainted(false);
        button_zwroc.setFocusPainted(false);
        button_zwroc.setContentAreaFilled(true);

        button_wypozycz2.setForeground(new Color(50,50,50));
        button_wypozycz2.setBackground(new Color(195,195,190));
        button_wypozycz2.setBorderPainted(false);
        button_wypozycz2.setFocusPainted(false);
        button_wypozycz2.setContentAreaFilled(true);

        button_xml.setForeground(new Color(50,50,50));
        button_xml.setBackground(new Color(185,185,180));
        button_xml.setBorderPainted(false);
        button_xml.setFocusPainted(false);
        button_xml.setContentAreaFilled(true);


        button_json.setForeground(new Color(50,50,50));
        button_json.setBackground(new Color(185,185,180));
        button_json.setBorderPainted(false);
        button_json.setFocusPainted(false);
        button_json.setContentAreaFilled(true);

        Border border = BorderFactory.createLineBorder(new Color(0,0,0),3);

        button_wypozycz.setBounds(250,120,200,80);
        button_oddaj.setBounds(250,220,200,80);
        button_stan.setBounds(250,320,200,80);
        button_back.setBounds(250,320,200,80);

        button_xml.setBounds(250,50,90,50);
        button_json.setBounds(360,50,90,50);

        //endregion

        /*library.zwroc(book1);
        library.zwroc(book2);
        library.zwroc(book3);*/

        button_back.setVisible(false);
        notepad.setVisible(false);
        books2.setModel(model);

        for(int i = 0; i<books.size(); i++) {
            model.addElement(books.get(i));
        }


        spwypo.setLeftComponent(new JScrollPane(books2));
        pwypo.add(lwypo);
        spwypo.setRightComponent(pwypo);

        spwypo.setVisible(false);
        button_wypozycz2.setVisible(false);
        komunikatzwroc.setVisible(false);
        button_xml.setVisible(true);
        button_json.setVisible(true);

    }
    public static void main(String[] args){
        new GUI();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == button_wypozycz) {
            button_oddaj.setVisible(false);
            button_stan.setVisible(false);
            button_back.setVisible(true);
            notepad.setVisible(false);
            button_wypozycz.setVisible(false);

            List<Book> books = GetBooks(URL+"getBooks",false);

            model.clear();

            for(int i = 0; i<books.size(); i++) {
                model.addElement(books.get(i));
            }
            spwypo.setLeftComponent(new JScrollPane(books2));


            spwypo.setVisible(true);
            button_wypozycz2.setVisible(true);

            lzwroc1.setVisible(false);
            lzwroc2.setVisible(false);
            tfzwroc1.setVisible(false);
            tfzwroc2.setVisible(false);
            button_zwroc.setVisible(false);
            komunikatzwroc.setVisible(false);
            button_json.setVisible(false);
            button_xml.setVisible(false);




        }
        if(source == button_oddaj) {

            button_back.setVisible(true);
            lzwroc1.setVisible(true);
            lzwroc2.setVisible(false);
            tfzwroc1.setVisible(true);
            tfzwroc2.setVisible(false);
            button_zwroc.setVisible(true);

            button_json.setVisible(false);
            button_xml.setVisible(false);
            button_oddaj.setVisible(false);
            button_stan.setVisible(false);
            notepad.setVisible(false);
            button_wypozycz.setVisible(false);
            spwypo.setVisible(false);
            button_wypozycz2.setVisible(false);
            komunikatzwroc.setVisible(false);
            komunikatzwroc2.setVisible(false);
            komunikatzwroc3.setVisible(false);



        }
        if(source == button_stan) {
            button_back.setVisible(true);
            notepad.setText(library.booksInLibrary());
            notepad.setVisible(false);

            List<Book> books = GetBooks(URL+"getBooks",true);

            model.clear();

            for(int i = 0; i<books.size(); i++) {
                model.addElement(books.get(i));
            }
            spwypo.setLeftComponent(new JScrollPane(books2));
            spwypo.setVisible(true);

            button_json.setVisible(false);
            button_xml.setVisible(false);
            button_wypozycz.setVisible(false);
            button_oddaj.setVisible(false);
            button_stan.setVisible(false);
            lzwroc1.setVisible(false);
            lzwroc2.setVisible(false);
            tfzwroc1.setVisible(false);
            tfzwroc2.setVisible(false);
            button_zwroc.setVisible(false);
            komunikatzwroc.setVisible(false);
            komunikatzwroc2.setVisible(false);
            komunikatzwroc3.setVisible(false);


        }
        if(source == button_back) {
            frame.setVisible(true);
            button_wypozycz.setVisible(true);
            button_oddaj.setVisible(true);
            button_stan.setVisible(true);
            button_json.setVisible(true);
            button_xml.setVisible(true);


            button_back.setVisible(false);


            notepad.setVisible(false);
            spwypo.setVisible(false);
            button_wypozycz2.setVisible(false);

            lzwroc1.setVisible(false);
            lzwroc2.setVisible(false);
            tfzwroc1.setVisible(false);
            tfzwroc2.setVisible(false);
            button_zwroc.setVisible(false);
            komunikatzwroc.setVisible(false);
            komunikatzwroc2.setVisible(false);
            komunikatzwroc3.setVisible(false);


        }

        books2.getSelectionModel().addListSelectionListener(e1 -> {
            Book chosen = books2.getSelectedValue();
            if(chosen != null)
                lwypo.setText("Tytuł: " + chosen.getTitle() + " ID: " + chosen.getId());

        });

        if(source == button_wypozycz2)
        {
            int id = books2.getSelectedValue().id;
            Map<String, Object> resp = Wypozycz(URL + "borrow", id);
            System.out.println(resp.get("msg"));

            library.wypozycz(books2.getSelectedValue());

            List<Book> books = GetBooks(URL+"getBooks",false);

            model.clear();

            for(int i = 0; i<books.size(); i++) {
                model.addElement(books.get(i));
            }

            spwypo.setLeftComponent(new JScrollPane(books2));
            pwypo.add(lwypo);
            spwypo.setRightComponent(pwypo);
            komunikatzwroc.setVisible(false);

        }

        if(source == button_zwroc)
        {
            komunikatzwroc2.setVisible(false);
            komunikatzwroc3.setVisible(false);
            komunikatzwroc.setVisible(false);
                if(tfzwroc1.getText().isEmpty()==false) {
                    int id = Integer.parseInt(tfzwroc1.getText());
                    //Book nowa = new Book(id, tfzwroc2.getText());

                    Map<String, Object> resp = Zwroc(URL + "return", id);
                    System.out.println(resp.get("msg"));

                    //library.zwroc(nowa);
                    //System.out.print(books.size());

//                    model.clear();
//
//                    for (int i = 0; i < books.size(); i++) {
//                        model.addElement(books.get(i));
//                    }


                    komunikatzwroc3.setHorizontalAlignment(SwingConstants.CENTER);
                    komunikatzwroc3.setBounds(250,150,200,40);

                    if((Double)resp.get("status") == 200){
                        tfzwroc1.setText("");
                        tfzwroc2.setText("");
                        komunikatzwroc3.setText("Zwrócono ksiażkę do biblioteki");
                    }else{
                        komunikatzwroc3.setText(resp.get("msg").toString());
                    }

                    komunikatzwroc3.setVisible(true);
                }
                else{
                    komunikatzwroc2.setVisible(true);
                    komunikatzwroc2.setBounds(125, 270, 200, 20);

                }


        }
        if(source == button_xml)
        {
            List<Book> books = GetBooks(URL+"getBooks",true);
            STF.saveXMLToFile(books);

        }
        if(source == button_json)
        {
            try {
                List<Book> books = GetBooks(URL+"getBooks",true);
                STF.saveJsonToFile(books);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }



    }

}

