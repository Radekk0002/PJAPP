package httpServer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import Library.*;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/borrow", new BorrowHandler("asdasdsa"));
        server.createContext("/return", new ReturnHandler("asdasdsa"));
        server.createContext("/getBooks", new GetBooksHandler("asdasdsa"));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Serwer uruchomiony");
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
        String connToDB;
        public BorrowHandler(String connToDB){
            this.connToDB= connToDB;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            int id = Integer.parseInt(queryToMap(t.getRequestURI().getQuery()).get("bookId"));
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", 200);
            resp.put("msg", "Odp zwrotna");

            Gson gson = new Gson();
            String json = gson.toJson(resp);

            t.sendResponseHeaders(200, json.length());
            OutputStream os = t.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }

    static class ReturnHandler implements HttpHandler {
        String connToDB;
        public ReturnHandler(String connToDB){
            this.connToDB = connToDB;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {

            int id = Integer.parseInt(queryToMap(t.getRequestURI().getQuery()).get("bookId"));

            Map<String, Object> resp = new HashMap<>();
            resp.put("status", 200);
            resp.put("msg", "Odp zwrotna");

            Gson gson = new Gson();
            String json = gson.toJson(resp);
            System.out.println(json);

            t.sendResponseHeaders(200,json.length());
            OutputStream os = t.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }

    static class GetBooksHandler implements HttpHandler {
        String connToDB;
        public GetBooksHandler(String connToDB){
            this.connToDB= connToDB;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            Book[] books = new Book[]{new Book(0, "asdasdas"),
                    new Book(1, "asdasdas2"),
                    new Book(2, "asdasdas3")};

            Gson gson = new Gson();
            String json = gson.toJson(books);
            System.out.println(json);

            t.sendResponseHeaders(200, json.length());
            OutputStream os = t.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }
}
