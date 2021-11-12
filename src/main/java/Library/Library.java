package Library;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public static List<Book> books = new ArrayList();

    public Library(List books) {
        this.books = books;
    }
    public static String zwroc(Book book)
    {
        books.add(book);
        return "Książka została zwrócona do biblioteki";

    }
    public static String wypozycz(Book book)
    {
        books.remove(book);
        return "Wypożyczono";
    }

    public static String booksInLibrary()
    {
        System.out.println("Zbiór książek w bibliotece:");
        StringBuilder result = new StringBuilder();
        for(int i = 0; i<books.size(); i++)
        {
            result.append(String.format((Integer)books.get(i).id + " " + (String)books.get(i).title + "\n"));
        }
        return result.toString();

    }


}
