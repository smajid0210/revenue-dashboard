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
import java.time.LocalDate;
import java.util.Calendar;

public class iVASByDateRangeApi {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static String TOKEN ;
    private static final String GET_URL = "http://vat.gov.bd/sap/opu/odata/sap/ZOD_ERP_INTERGRATION_SRV/GetTotalRevenue_ParaSet(TIMESTAMP='',START_DATE='20220101',END_DATE='20221231')?$format=json";

    private static final String POST_URL = "http://103.92.84.243/api/Auth/token";

    private static final String POST_PARAMS = "{  'UserName':'Revenue', 'Password':'123456a@2023#$' }";
    private static final String GET_PARAMS = "{  'UserName':'Revenue', 'Password':'123456a@2023#$' }";

    private static int TOTAL_BIN_VAT;
    private static int TOTAL_BIN_TOT;
    private static int TOTAL_RETURN_9_1;
    private static int TOTAL_RETURN_9_2;
    private static int TOTAL_RETURN_9_1LM;
    private static int TOTAL_RETURN_9_2LM;
    private static double TOTAL_AMOUNT_SONALI;
    private static double TOTAL_AMOUNT_BANGLADESH;
    private static double TOTAL_AMOUNT_A_CHALLAN;


//    //public int getETinCountTillDate(){
//        return eTinCountTillToday;
//    }

    public iVASByDateRangeApi(){

    }

    public static int getIvasRegistrationFY() throws IOException {
        //sendPOST();
        System.out.println("POST NOO Need here for Basic Auth");
        sendGET();
        System.out.println("GET DONE");
        System.out.println("ivas BIN API Get calllll for Fiscal Year");
        return (TOTAL_BIN_VAT+TOTAL_BIN_TOT);//binCountTillToday;
    }
    public static int getBinCountRange() throws IOException {
        return 0;//binCountRange;
    }

    public static int getReturnCountLastMon()throws IOException {
        sendGetLastMon();

        return   (TOTAL_RETURN_9_1LM+TOTAL_RETURN_9_2LM);
    }
    public static void sendGetLastMon() throws IOException {

        LocalDate date = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        int month = Calendar.MONTH;
        String mm[] = {"", "01","02","03","04","05","06","07","08","09","10","11","12"};
        String yr1 = String.valueOf(date.getYear());
        System.out.println(date.getYear() +"year "+yr1);
        String yr2 = yr1;
        if(month==1) { yr1 = String.valueOf((Calendar.YEAR)-1)+"1201";yr2 = String.valueOf((Calendar.YEAR))+"1231";}
        else {
            month--;
            yr1 = yr1+mm[month]+"01";
            if(month==2) yr2 = yr2+mm[month]+"28";
            else yr2 = yr2+mm[month]+"30";
        }

        String GET_URLLMon = "http://vat.gov.bd/sap/opu/odata/sap/ZOD_ERP_INTERGRATION_SRV/GetTotalRevenue_ParaSet(TIMESTAMP='',START_DATE='"+yr1+"',END_DATE='"+yr2+"')?$format=json";

        System.out.println("\nInside sendGetLastMon: \n Year1="+yr1+"Year 2: "+yr2 + "\n URL= "+GET_URLLMon);

        URL obj = new URL(GET_URLLMon);
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

            System.out.println(response.toString());

            int stPos = response.indexOf("TOTAL_RETURN_9_1")+19; //Return submission Parsing 9.1
            int endPos = response.indexOf("TOTAL_RETURN_9_2")-3;
            System.out.println("Start point: "+stPos +", end point: "+endPos);
            char ret91CountCh[] = new char[8];
            String ret91CountStr = new String();
            response.getChars(stPos,endPos, ret91CountCh,0);
            for (int i = 0; i < endPos-stPos; i++) {
                ret91CountStr +=ret91CountCh[i];
            }
            System.out.println("Ret count 9.1 str "+ret91CountStr);
            TOTAL_RETURN_9_1LM=Integer.parseInt(ret91CountStr);
            System.out.println("\n"+TOTAL_RETURN_9_1LM);

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
            TOTAL_RETURN_9_2LM=Integer.parseInt(ret92CountStr);
            System.out.println("\n"+TOTAL_RETURN_9_2LM);
        } else {
            System.out.println("returnCount Last Month : GET request did not work.");
        }

    }


    public static String getIvasCollectionFY() throws IOException {
        DecimalFormat decfor = new DecimalFormat("0.00");
        double amount = (TOTAL_AMOUNT_SONALI+ TOTAL_AMOUNT_BANGLADESH + TOTAL_AMOUNT_A_CHALLAN)/1000000000;
        return decfor.format(amount);//(TOTAL_AMOUNT_SONALI+ TOTAL_AMOUNT_BANGLADESH + TOTAL_AMOUNT_A_CHALLAN);

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
            endPos = response.indexOf("TIMESTAMP", stPos)-3;
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