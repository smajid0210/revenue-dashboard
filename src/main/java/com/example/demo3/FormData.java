package com.example.demo3;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet implementation class FormDataHandle

// Annotation to map the Servlet URL
@WebServlet("/FormData")
public class FormData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String TOKEN ;
    private static String GET_URL = "http://103.92.84.243/api/Verification/1.0/getTinInfo";

    private static final String POST_URL = "http://103.92.84.243/api/Auth/token";

    private static final String POST_PARAMS = "{  'UserName':'verify@nbr.gov.bd', 'Password':'1234' }";

    private static final String GET_PARAMS = " ";
    // Auto-generated constructor stub

    public void init()
    {
        try
        {
            sendPOST();
            System.out.println("Post Done");
        }
        catch(Exception e)
        {

        }

    }

    // HttpServlet doPost(HttpServletRequest request, HttpServletResponse response) method
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the values from the request using 'getParameter'
        String tinNo = request.getParameter("tin");
        GET_URL += "/" + tinNo;
        String responseString = sendGET();

        JsonParser parser = new JsonParser();

        JsonElement tinElement = parser.parse(responseString);
        JsonObject tin = tinElement.getAsJsonObject();


        //String phNum = request.getParameter("phone");
        //String gender = request.getParameter("gender");

        // To get all the values selected for
        // programming language, use 'getParameterValues'
        //String progLang[] = request.getParameterValues("language");

        // Iterate through the String array to
        // store the selected values in form of String
        String langSelect = "";
        /*if(progLang!=null){
            for(int i=0;i<progLang.length;i++){
                langSelect= langSelect + progLang[i]+ ", ";
            }
        }*/

        //String courseDur = request.getParameter("duration");
        //String comment = request.getParameter("comment");

        // set the content type of response to 'text/html'
        response.setContentType("text/html");

        // Get the PrintWriter object to write
        // the response to the text-output stream
        PrintWriter out = response.getWriter();

        // Print the data
        out.print("<html><body>");
        out.print("<h3>Details Entered</h3><br/>");


        out.print("NID: "+ tin.get("assesName").toString() + "<br/>");
        out.print("NID: "+ tin.get("nid").toString() + "<br/>");
        out.print("Smart ID:" + tin.get("smartId").toString() + "<br/>");
        //out.print("Phone Number: "+ phNum +"<br/>");
        //out.print("Gender: "+ gender +"<br/>");
        //out.print("Programming languages selected: "+ langSelect +"<br/>");
        //out.print("Duration of course: "+ courseDur+"<br/>");
        //out.print("Comments: "+ comment);

        out.print("</body></html>");

    }
    private static String sendGET() throws IOException {

        TOKEN = TOKEN.substring(1,TOKEN.length()-1);
        System.out.println("This" + TOKEN);

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization", "Bearer " + TOKEN );
        String resString = " ";
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());
            resString = response.toString();
        } else {
            System.out.println("GET request did not work.");
        }

        return resString;
    }

    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        //ystem.out.println(con.getInputStream());

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonstring = response.toString();
            JsonParser parser = new JsonParser();

            JsonElement tradeElement = parser.parse(jsonstring);
            JsonObject trade = tradeElement.getAsJsonObject();

            TOKEN = trade.get("token").toString();

            System.out.println(TOKEN);
        } else {
            System.out.println("POST request did not work.");
        }
    }

}