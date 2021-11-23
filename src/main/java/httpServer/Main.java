package httpServer;

import DataBase.H2Database;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Library.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Baza otwarta");
        H2Database base = new H2Database();
        base.createDatabase();
        Book[] books =
                new Book[]{
                        new Book(0,"W pustyni i puszczy"),
                        new Book(1,"Balladyna"),
                        new Book(2,"Kamienie na Szaniec"),
                        new Book(3,"Pani Bovary"),
                        new Book(4,"Hamlet"),
                        new Book(5,"Wesele"),
                        new Book(6,"Mistrz i Małgorzata"),
                        new Book(7,"Lalka"),
                        new Book(8,"Zbrodnia i kara"),
                        new Book(9,"Chłopi"),
                        new Book(10,"Quo Vadis")
                };
        for (Book book: books) {
            base.add_book(book);
        }
        System.out.println("Baza utworzona");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/borrow", new BorrowHandler(base));
        server.createContext("/return", new ReturnHandler(base));
        server.createContext("/getBooks", new GetBooksHandler(base));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Serwer uruchomiony");
        Scanner scan = new Scanner(System.in);
        while(true){
            if(scan.nextLine().equals("exit")){
                base.closeDatabase();
                System.out.println("Baza zamknięta");
                server.stop(0);
                System.exit(0);
            }
        }
    }

    private static Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
    static class BorrowHandler implements HttpHandler {
        H2Database connToDB;
        public BorrowHandler(H2Database connToDB){
            this.connToDB = connToDB;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            int id = Integer.parseInt(queryToMap(t.getRequestURI().getQuery()).get("bookId"));
            int rows = connToDB.borrow_book(id);
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", 200);
            if(rows > 0)
                resp.put("msg", "Wypożyczono");
            else
                resp.put("msg", "Nie ma takiej książki");

            System.out.println(resp.get("msg"));
            Gson gson = new Gson();
            String json = gson.toJson(resp);
            byte[] utf8JsonString = json.getBytes("UTF8");

            t.sendResponseHeaders(200, utf8JsonString.length);
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF8");;
            OutputStream os = t.getResponseBody();
            os.write(utf8JsonString);
            os.close();
        }
    }
    static class ReturnHandler implements HttpHandler {
        H2Database connToDB;
        public ReturnHandler(H2Database connToDB){
            this.connToDB = connToDB;
        }


        @Override
        public void handle(HttpExchange t) throws IOException {

            int id = Integer.parseInt(queryToMap(t.getRequestURI().getQuery()).get("bookId"));
            int rows = connToDB.return_book(id);
            Map<String, Object> resp = new HashMap<>();

            if(rows > 0) {
                resp.put("status", 200);
                resp.put("msg", "Zwrócono");
            }
            else {
                resp.put("status", 400);
                resp.put("msg", "Nie ma takiej książki");
            }
            System.out.println(resp.get("msg"));
            Gson gson = new Gson();
            String json = gson.toJson(resp);
            byte[] utf8JsonString = json.getBytes("UTF8");

            t.sendResponseHeaders(200, utf8JsonString.length);
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF8");;
            OutputStream os = t.getResponseBody();
            os.write(utf8JsonString);
            os.close();
        }
    }

    static class GetBooksHandler implements HttpHandler {
        H2Database connToDB;
        public GetBooksHandler(H2Database connToDB){
            this.connToDB = connToDB;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            boolean allbooks = Boolean.parseBoolean(queryToMap(t.getRequestURI().getQuery()).get("allbooks"));
            List<Book> books = connToDB.booksInLibrary(allbooks);

            Gson gson = new Gson();
            String json = gson.toJson(books);
            System.out.println(json);
            byte[] utf8JsonString = json.getBytes("UTF8");

            t.sendResponseHeaders(200, utf8JsonString.length);
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF8");;
            OutputStream os = t.getResponseBody();
            os.write(utf8JsonString);
            os.close();
        }
    }
}
