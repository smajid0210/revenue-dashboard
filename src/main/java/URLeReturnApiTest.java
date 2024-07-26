import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLeReturnApiTest {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String TOKEN ;
    private static final String GET_URL = "http://103.92.84.139:8000/managementservice/v2/api/eReturn/1.0/NBR-IT/report/return-summary";
    private static final String POST_URL = "http://103.92.84.139:8000/authenticationservice/v2/api/authenticate/management";
    private static final String POST_PARAMS = "{\"username\": \"NBR_IT\" ,  \"password\":\"NBR_IT@eReturn2023\", \"grantType\":\"password\"}";
    //, 'refreshToken':'', 'rememberMe':'true' }";


    private static final String GET_PARAMS = " ";

    public static void main(String[] args) throws IOException {

        sendPOST();
        System.out.println("POST DONE");
        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {

        TOKEN = TOKEN.substring(1,TOKEN.length()-1);
        System.out.println("This" + TOKEN);

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization", "Bearer " + TOKEN );

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

            String jsonstring = response.toString();
            JsonParser parser = new JsonParser();

            JsonElement tradeElement = parser.parse(jsonstring);
            JsonObject trade = tradeElement.getAsJsonObject();

            System.out.println(trade.toString());
            jsonstring = trade.get("replyMessage").toString();

            jsonstring = parser.parse(jsonstring).getAsJsonObject().get("summary").toString();
            String totalsubm = (parser.parse(jsonstring).getAsJsonObject().get("totalSubmission").toString());
            String totalPayment = (parser.parse(jsonstring).getAsJsonObject().get("totalPayment").toString());

            System.out.println(jsonstring+"sub="+totalsubm+" paym="+totalPayment);

        } else {
            System.out.println("GET request did not work.");
        }

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

            System.out.println(trade.toString());
            jsonstring = trade.get("replyMessage").toString();

            JsonElement tradeElement2 = parser.parse(jsonstring);
            JsonObject trade2 = tradeElement2.getAsJsonObject();
            jsonstring = trade2.get("body").toString();

            JsonElement tradeElement3 = parser.parse(jsonstring);
            JsonObject trade3 = tradeElement3.getAsJsonObject();
            TOKEN = trade3.get("id_token").toString();


            System.out.println(TOKEN);
        } else {
            System.out.println("POST request did not work.");
        }
    }
}