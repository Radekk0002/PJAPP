package GUI.SaveToFileMethods;

import Library.Book;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SaveToFileMethods {
    public static void saveXMLToFile(List<Book> books){
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File("testXML.xml"), books);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveJsonToFile(List<Book> books) throws IOException {
        Writer writer = null;
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            writer = new FileWriter("testJson.txt");
            gson.toJson(books, writer);
            writer.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if(writer != null)
                writer.close();
        }
    }
}
