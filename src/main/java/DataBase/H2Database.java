package DataBase;

import Library.Book;
import java.sql.*;
import resources.*;

public class H2Database {
    static final String DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:D:/data/Database";

    static final String USER = "";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;


    public static void createDatabase() {
        Connection p_conn = null;
        Statement p_stmt = null;
        try {
            Class.forName(DRIVER);
            p_conn = DriverManager.getConnection(DB_URL, USER, PASS);
            p_stmt = p_conn.createStatement();

            String sql = "CREATE TABLE TBL_LIBRARY " +
                    "(id INTEGER not NULL, " +
                    "title VARCHAR(100), " +
                    "is_borrowed BOOLEAN, " +
                    "PRIMARY KEY ( id ))";

            p_stmt.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();

        } catch (Exception se) {
            se.printStackTrace();
        }

        stmt = p_stmt;
        conn = p_conn;
    }

    public static void closeDatabase()
    {
        try {
            stmt.execute("drop all objects delete files");
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void borrow_book(int id)
    {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE TBL_LIBRARY " + "SET is_borrowed = " + true + " WHERE id is " + id;
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void return_book(int id)
    {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE TBL_LIBRARY " + "SET is_borrowed = " + false + " WHERE id is " + id;
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void booksInLibrary()
    {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT id,title,is_borrowed FROM TBL_LIBRARY";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id  = rs.getInt("id");
                boolean is_borrowed = rs.getBoolean("is_borrowed");
                String title = rs.getString("title");
                System.out.print("ID: " + id + ", Title: " + title + ", Is Borrowed: " + is_borrowed + '\n');
            }
            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}