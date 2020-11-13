import org.apache.log4j.BasicConfigurator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import static spark.Spark.*;

public class SparkAppMain {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        JSONObject book1Detailes = new JSONObject();
        book1Detailes.put("cost", "10");
        book1Detailes.put("number", "1");
        JSONObject book1 = new JSONObject();
        book1.put("topic", "distributed systems");
        book1.put("title", "RPCs for Dummies");
        book1.put("id", "1000");
        book1.put("details" , book1Detailes);



        JSONObject book2Detailes = new JSONObject();
        book2Detailes.put("cost", "20");
        book2Detailes.put("number", "2");
        JSONObject book2 = new JSONObject();
        book2.put("topic", "distributed systems");
        book2.put("title", "How to get a good grade in DOS in 20 minutes a day");
        book2.put("id", "2000");
        book2.put("details" , book1Detailes);



        JSONObject book3Detailes = new JSONObject();
        book3Detailes.put("cost", "30");
        book3Detailes.put("number", "3");
        JSONObject book3 = new JSONObject();
        book3.put("topic", "graduate school");
        book3.put("title", "Xen and the Art of Surviving Graduate School");
        book3.put("id", "3000");
        book3.put("details" , book1Detailes);



        JSONObject book4Detailes = new JSONObject();
        book4Detailes.put("cost", "40");
        book4Detailes.put("number", "4");
        JSONObject book4 = new JSONObject();
        book4.put("topic", "graduate school");
        book4.put("title", "Cooking for the Impatient Graduate Student");
        book4.put("id", "4000");
        book4.put("details" , book1Detailes);


        JSONArray booksList = new JSONArray();
        booksList.put(book1);
        booksList.put(book2);
        booksList.put(book3);
        booksList.put(book4);


        try (FileWriter file = new FileWriter("books.json")) {

            file.write(booksList.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //
       // get("/hello", (req, res) -> "nice job !");
        get("/search/:value", (request, response) -> {
            //return "Hello: " + request.params(":value");
            String value = request.params(":value");
            String foundBooks ="";
            int index=1;
            for (int i = 0; i < booksList.length(); i++)
                if (booksList.getJSONObject(i).get("topic").equals(value)) {

                    foundBooks += "<pre>Book "+index + " - "+ booksList.getJSONObject(i).get("title")+".     ID: "+booksList.getJSONObject(i).get("id")+ ".</pre>";
                    index++;
                }

            return foundBooks;
        });






    }
}