package DataBase;
import Library.*;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        H2Database.createDatabase();
        Book[] books =
                new Book[]{new Book(0,"Test1"),
                new Book(1,"Tęstowy2"),
                new Book(2,"Test3 i Tęst4 oraz Test 5")};

        //H2Database.add_book(books[0]); wyjebane
        //H2Database.add_book(books[1]);
        //H2Database.add_book(books[2]);

        H2Database.booksInLibrary();
        System.out.println("\n");

        H2Database.borrow_book(new Book(0,"Podróba_Test1").getId());

        H2Database.booksInLibrary();
        System.out.println("\n");

        H2Database.borrow_book(books[0].getId());

        H2Database.booksInLibrary();
        System.out.println("\n");

        H2Database.return_book(books[0].getId());

        H2Database.booksInLibrary();

        Scanner skaner = new Scanner(System.in);
        String t = skaner.nextLine();

        H2Database.closeDatabase();
    }
}
