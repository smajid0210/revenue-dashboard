package api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class eTINCountApi {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String TOKEN ;
    private static final String GET_URL = "http://103.92.84.243/api/NbrDashboard/1.0/count-tin-reg-till-date";

    private static final String POST_URL = "http://103.92.84.243/api/Auth/token";

    private static final String POST_PARAMS = "{  'UserName':'admin@nbrdashboard.com', 'Password':'1234' }";

    private static final String GET_PARAMS = " ";

    private static int eTinCountTillToday;
    private static int tinReturnCount;
    private static int tinCountRange;
    private static int tinCollectionMon;
    private static int tinCollectionFY;
    private static int tinIBASCollection;

//    //public int getETinCountTillDate(){
//        return eTinCountTillToday;
//    }

    public eTINCountApi(){

    }
//    public static void main(String[] args) throws IOException {
//
//        sendPOST();
//        System.out.println("POST DONE");
//        sendGET();
//        System.out.println("GET DONE");
//    }

    public static int getETinCountTillDate() throws IOException {
        sendPOST();
        System.out.println("POST DONE");
        sendGET();
        System.out.println("GET DONE");
        return eTinCountTillToday;
    }

    public static int getTinCountRange() throws IOException {
        return 200;//TinCountRange;
    }
    public static int getTinReturnCount() throws IOException {
        return 490;//TinreturnCount;
    }
    public static int getTinCollectionMon() throws IOException {
        return 65000;//TinCollectionMon;

    }
    public static int getTinCollectionFY() throws IOException {
        return 770023;//TinCollectionFY;

    }
    public static int getTinIBASCollection() throws IOException {
        return 66009;//TinIBASCollection;
    }

    public static void sendGET() throws IOException {

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
            int stPos = response.indexOf(":")+1;
            System.out.println("Start point: "+stPos);
            int endPos = response.indexOf("}");
            System.out.println("Start point: "+endPos);
            char eTinCountCh[] = new char[5];
            String eTinCountStr = new String();
            response.getChars(stPos,endPos, eTinCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                eTinCountStr +=eTinCountCh[i];
            }
            eTinCountTillToday=Integer.parseInt(eTinCountStr);
            System.out.println("\n"+eTinCountTillToday);
        } else {
            System.out.println("GET request did not work.");
        }

    }

    static void sendPOST() throws IOException {
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