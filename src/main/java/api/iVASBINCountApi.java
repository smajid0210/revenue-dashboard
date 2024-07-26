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
import java.text.DecimalFormat;

public class iVASBINCountApi {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String TOKEN ;
    private static final String GET_URL = "http://vat.gov.bd/sap/opu/odata/sap/ZOD_ERP_INTERGRATION_SRV/GetTotalRevenueSet(TIMESTAMP='13.02.2023%2015%3A38%3A06')?$format=json";

    private static final String POST_URL = "http://103.92.84.243/api/Auth/token";

    private static final String POST_PARAMS = "{  'UserName':'Revenue', 'Password':'123456a@2023#$' }";
    private static final String GET_PARAMS = "{  'UserName':'Revenue', 'Password':'123456a@2023#$' }";

    //private static final String GET_PARAMS = " ";

    private static int binCountTillToday;
    private static int returnCount;
    private static int binCountRange;
    private static int ivasCollectionMon;
    private static int ivasCollectionFY;
    private static int ivasIBASCollection;
    private static int TOTAL_BIN_VAT;
    private static int TOTAL_BIN_TOT;
    private static int TOTAL_RETURN_9_1;
    private static int TOTAL_RETURN_9_2;
    private static double TOTAL_AMOUNT_SONALI;
    private static double TOTAL_AMOUNT_BANGLADESH;
    private static double TOTAL_AMOUNT_A_CHALLAN;


//    //public int getETinCountTillDate(){
//        return eTinCountTillToday;
//    }

    public iVASBINCountApi(){

    }
//    public static void main(String[] args) throws IOException {
//
//        sendPOST();
//        System.out.println("POST DONE");
//        sendGET();
//        System.out.println("GET DONE");
//    }

    public static int getBinCountTillToday() throws IOException {
        //sendPOST();
        System.out.println("POST DONE");
        sendGET();
        System.out.println("GET DONE");
        System.out.println("ivas BIN API Get calllll %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*******");
        return (TOTAL_BIN_VAT+TOTAL_BIN_TOT);//binCountTillToday;
    }
    public static int getBinCountRange() throws IOException {
        return 0;//binCountRange;
    }
    public static int getReturnCount() throws IOException {
        return   (TOTAL_RETURN_9_1+TOTAL_RETURN_9_2);
    }
    public static String getIvasCollectionMon() throws IOException {
        DecimalFormat decfor = new DecimalFormat("0.00");
        double amount = (TOTAL_AMOUNT_SONALI+ TOTAL_AMOUNT_BANGLADESH + TOTAL_AMOUNT_A_CHALLAN)/1000000000;
        return decfor.format(amount);//(TOTAL_AMOUNT_SONALI+ TOTAL_AMOUNT_BANGLADESH + TOTAL_AMOUNT_A_CHALLAN);

    }
    public static int getIvasCollectionFY() throws IOException {
        return 0;//ivasCollectionFY;

    }
    public static double getIvasIBASCollection() throws IOException {
        return 0;
    }

    public static void sendGET() throws IOException {

        //TOKEN = TOKEN.substring(1,TOKEN.length()-1);
        //System.out.println("This" + TOKEN);

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization", "Basic UmV2ZW51ZToxMjM0NTZhQDIwMjMjJA==" );

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

            int stPos = response.indexOf("TOTAL_BIN_VAT\":")+16; //BIN Parsing
            int endPos = response.indexOf("TOTAL_BIN_TOT")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char binCountCh[] = new char[8];
            String binCountStr = new String();
            response.getChars(stPos,endPos, binCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                binCountStr +=binCountCh[i];
            }
            System.out.println("Bin Count str "+binCountStr);
            TOTAL_BIN_VAT=Integer.parseInt(binCountStr);
            System.out.println("\n"+TOTAL_BIN_VAT);

            stPos = response.indexOf("TOTAL_BIN_TOT")+16;  //TOT Parsing
            endPos = response.indexOf("TOTAL_RETURN_9_1")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char totCountCh[] = new char[8];
            String totCountStr = new String();
            response.getChars(stPos,endPos, totCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                totCountStr +=totCountCh[i];
            }
            System.out.println("TOT Count str "+totCountStr);
            TOTAL_BIN_TOT=Integer.parseInt(totCountStr);
            System.out.println("\n"+TOTAL_BIN_TOT);


            stPos = response.indexOf("TOTAL_RETURN_9_1")+19; //Return submission Parsing 9.1
            endPos = response.indexOf("TOTAL_RETURN_9_2")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char ret91CountCh[] = new char[8];
            String ret91CountStr = new String();
            response.getChars(stPos,endPos, ret91CountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                ret91CountStr +=ret91CountCh[i];
            }
            System.out.println("Ret count 9.1 str "+ret91CountStr);
            TOTAL_RETURN_9_1=Integer.parseInt(ret91CountStr);
            System.out.println("\n"+TOTAL_RETURN_9_1);

            stPos = response.indexOf("TOTAL_RETURN_9_2")+19; //Return submission Parsing 9.2
            endPos = response.indexOf("TOTAL_AMOUNT_SONALI")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char ret92CountCh[] = new char[8];
            String ret92CountStr = new String();
            response.getChars(stPos,endPos, ret92CountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                ret92CountStr +=ret92CountCh[i];
            }
            System.out.println("Ret count 9.1 str "+ret92CountStr);
            TOTAL_RETURN_9_2=Integer.parseInt(ret92CountStr);
            System.out.println("\n"+TOTAL_RETURN_9_2);

            stPos = response.indexOf("TOTAL_AMOUNT_SONALI")+22; //Payment collection by sonali bank
            endPos = response.indexOf("TOTAL_AMOUNT_BANGLADESH")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char sonaliCountCh[] = new char[14];
            String sonaliCountStr = new String();
            response.getChars(stPos,endPos, sonaliCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                sonaliCountStr +=sonaliCountCh[i];
            }
            System.out.println("collection sonali str "+sonaliCountStr);
            TOTAL_AMOUNT_SONALI=Double.parseDouble(sonaliCountStr);
            System.out.println("\n"+TOTAL_AMOUNT_SONALI);

            stPos = response.indexOf("TOTAL_AMOUNT_BANGLADESH")+26; //Payment collection by Bangladesh bank
            endPos = response.indexOf("TOTAL_AMOUNT_A_CHALLAN")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char BDbankCountCh[] = new char[14];
            String BDbankCountStr = new String();
            response.getChars(stPos,endPos, BDbankCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                BDbankCountStr +=BDbankCountCh[i];
            }
            System.out.println("collection sonali str "+BDbankCountStr);
            TOTAL_AMOUNT_BANGLADESH= Double.parseDouble(BDbankCountStr);
            System.out.println("\n"+TOTAL_AMOUNT_BANGLADESH);


            stPos = response.indexOf("TOTAL_AMOUNT_A_CHALLAN")+25; //Payment collection by Bangladesh bank
            endPos = response.indexOf("TIMESTAMP")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char aChallanCountCh[] = new char[14];
            String aChallanCountStr = new String();
            response.getChars(stPos,endPos, aChallanCountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                aChallanCountStr +=aChallanCountCh[i];
            }
            System.out.println("collection sonali str "+aChallanCountStr);
            TOTAL_AMOUNT_A_CHALLAN= Double.parseDouble(aChallanCountStr);
            System.out.println("\n"+TOTAL_AMOUNT_A_CHALLAN);

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