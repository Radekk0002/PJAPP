package GUI.HttpMethods;

import Library.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class HttpConnectionMethods {

    public static String Input(String urlString) {
        Map<String, String> emptyMap = new HashMap<>();
        return Input(urlString, emptyMap);
    }

    public static String Input(String urlString, Map<String, String> queryParams)
    {
        HttpURLConnection connection = null;
        try {
            //Create connection

            String queryString = getParamsString(queryParams);
            if (queryString.length() > 0) {
                urlString += "?" + queryString;
            }

            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
    public static List<Book> GetBooks(String urlString) {
        String response = Input(urlString);
        if (response == null) {
            return null;
        }

        Type listOfMyClassObject = new TypeToken<ArrayList<Book>>() {}.getType();

        Gson gson = new Gson();
        return gson.fromJson(response, listOfMyClassObject);
    }
    public static void Zwroc(String urlString, int bookId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("bookId", String.valueOf(bookId));

        String response = Input(urlString, parameters);
    }
    public static void Wypozycz(String urlString, int bookId){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("bookId", String.valueOf(bookId));

        String response = Input(urlString, parameters);
    }

    private static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}